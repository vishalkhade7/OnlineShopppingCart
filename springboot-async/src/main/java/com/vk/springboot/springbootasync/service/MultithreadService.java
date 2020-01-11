package com.vk.springboot.springbootasync.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MultithreadService {

	private static final Logger logger = LoggerFactory.getLogger(MultithreadService.class);

    

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> process(String s) throws InterruptedException {
		
    	
    	System.out.println("Ruuning Thread: "+Thread.currentThread().getName()+" for number :"+s);
    	Thread.sleep(1000);
    	return CompletableFuture.completedFuture(s);
    }
}
