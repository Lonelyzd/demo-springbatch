package cn.blsz.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ExecutorConfiguration {

  @Bean
  public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setCorePoolSize(5);
    threadPoolTaskExecutor.setMaxPoolSize(10);
    threadPoolTaskExecutor.setQueueCapacity(50);
    threadPoolTaskExecutor.setThreadNamePrefix("Data-Job");
    return threadPoolTaskExecutor;
   }
}