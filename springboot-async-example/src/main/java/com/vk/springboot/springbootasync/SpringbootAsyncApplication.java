package com.vk.springboot.springbootasync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.vk.springboot.springbootasync.service.MultithreadService;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SpringbootAsyncApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SpringbootAsyncApplication.class);

	@Autowired
	private MultithreadService multithreadService;

	@Bean("threadPoolTaskExecutor")
	public TaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(5);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("AsyncThread-");
		return executor;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAsyncApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		          }
}
