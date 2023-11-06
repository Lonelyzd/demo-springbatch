package cn.blsz.demo.task;

import cn.blsz.demo.task.job.DataBatchJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;


import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yingtao
 * @ClassName TimeTask
 * @description: TODO
 * @datetime 2022年 08月 12日 14:01
 * @version: 1.0
 */

@Slf4j
@Component
public class TimeTask {
  private final JobLauncher jobLauncher;
  private final DataBatchJob dataBatchJob;

  @Autowired
  public TimeTask(JobLauncher jobLauncher, DataBatchJob dataBatchJob) {
    this.jobLauncher = jobLauncher;
    this.dataBatchJob = dataBatchJob;
   }

  // 定时任务，每十秒执行一次
  @Scheduled(cron = "0/10 * * * * ?")
  public void runBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
      JobRestartException, JobInstanceAlreadyCompleteException {
    log.info("定时任务执行了...");
    // 在运行一个job的时候需要添加至少一个参数，这个参数最后会被写到batch_job_execution_params表中，
    // 不添加这个参数的话，job不会运行，并且这个参数在表中中不能重复，若设置的参数已存在表中，则会抛出异常，
    // 所以这里才使用时间戳作为参数
    JobParameters jobParameters = new JobParametersBuilder()
         .addLong("timestamp", System.currentTimeMillis())
         .toJobParameters();

    // 获取job并运行
    Job job = dataBatchJob.dataHandleJob();
    JobExecution execution = jobLauncher.run(job, jobParameters);
    log.info("定时任务结束. Exit Status : {}", execution.getStatus());
   }
}



作者：我是一个汉堡
链接：https://juejin.cn/post/7130911122055495717
来源：稀土掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。