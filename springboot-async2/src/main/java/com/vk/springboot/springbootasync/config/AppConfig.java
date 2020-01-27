package com.vk.springboot.springbootasync.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.vk.springboot.springbootasync.executor.ReaderExecutor;

@Configuration
@EnableAsync
public class AppConfig implements AsyncConfigurer {

	

	@Override
	@Bean("threadPoolTaskExecutor")
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(10);
		//executor.setQueueCapacity(11);
		executor.setThreadNamePrefix("MyExecutor-");
		executor.initialize();
		return executor;

	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return (throwable, method, objects) -> System.out.println("-- exception handler -- " + throwable);

	}

}