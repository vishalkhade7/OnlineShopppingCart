package com.vk.springboot.springbootasync.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MultithreadService {

	int count = 0;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> asyncProcess(String s) throws InterruptedException {
		
    	
    	System.out.println("Ruuning Thread: "+Thread.currentThread().getName()+" for number :"+s);
    	Thread.sleep(3000);
    	return CompletableFuture.completedFuture(s);
    }
    
    public List<String> getList(){
    	count ++;
    	
    	if (count>=3) {
    		return new ArrayList<String>();
    	}
    	List<String> list=new ArrayList<String>();
		for(int i=0;i<10;i++) {
			list.add(""+ count+"iterator" +i);
			
		}
		
		System.out.println("list Before multiThread: "+list.toString());
		return list;
    	
    }
}
