package cn.blsz.demo.task.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author yingtao
 * @ClassName JobListener
 * @description: 一个作业的监听器，批处理作业在执行前后会调用监听器的方法；这样我们就可以根据实际的业务需求在作业执行的前后进行一些日志的打印或者逻辑处理等
 * @datetime 2022年 08月 12日 14:01
 * @version: 1.0
 */
@Slf4j
@Component
public class JobListener implements JobExecutionListener {

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private long startTime;

    @Autowired
    public JobListener(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    /**
     * 该方法会在job开始前执行
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        log.info("job before " + jobExecution.getJobParameters());
    }

    /**
     * 该方法会在job结束后执行
     */
    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("JOB STATUS : {}", jobExecution.getStatus());
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("JOB FINISHED");
            threadPoolTaskExecutor.destroy();
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            log.info("JOB FAILED");
        }
        log.info("Job Cost Time : {}/ms", (System.currentTimeMillis() - startTime));
    }
}
