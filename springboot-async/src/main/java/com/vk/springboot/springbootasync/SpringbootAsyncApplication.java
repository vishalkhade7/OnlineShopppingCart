package com.vk.springboot.springbootasync;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.vk.springboot.springbootasync.service.MultithreadService;

@SpringBootApplication
@EnableAsync
public class SpringbootAsyncApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SpringbootAsyncApplication.class);

	@Autowired
	private MultithreadService multithreadService;

	@Bean("threadPoolTaskExecutor")
	public TaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(20);
		executor.setMaxPoolSize(1000);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("AsyncThread-");
		return executor;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAsyncApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		List<String> list=new ArrayList<String>();
		for(int i=0;i<50;i++) {
			list.add(""+i);
		}
		
		System.out.println("list Before multiThread: "+list.toString());
		long start = System.currentTimeMillis();
		list.parallelStream()
			.forEach(e -> {
				try {
					multithreadService.process(e);
				} catch (InterruptedException e1) {					
					e1.printStackTrace();
				}
			});
		    
		System.out.println("Elapsed time: " +
				  (System.currentTimeMillis() - start));
	}
}
