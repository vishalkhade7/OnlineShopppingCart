package com.vk.springboot.springbootasync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class ScheduledTasks {

	@Autowired
	MultithreadService multithreadService;
	
	int count=0;

	@Scheduled(fixedRate = 2000)
	public void reportCurrentTime() {
	 System.out.println("Scheduler Thread: "+Thread.currentThread());
	 
	
	 
	
		long start;
		/*
		 * start = System.currentTimeMillis(); list.forEach(e ->{ try {
		 * multithreadService.asyncProcess(e); } catch (InterruptedException e1) { //
		 * TODO Auto-generated catch block e1.printStackTrace(); } });
		 * System.out.println("Elapsed time without parallel stream: " +
		 * (System.currentTimeMillis() - start)
		 * +" ActiveThreads: "+Thread.activeCount());
		 */
		start = System.currentTimeMillis();
		multithreadService.getList().parallelStream()
			.forEach(e -> {
				try {
					multithreadService.asyncProcess(e);
				} catch (InterruptedException e1) {					
					e1.printStackTrace();
				}
			});
		    
		System.out.println("Elapsed time with parallel Stream: " +
				  (System.currentTimeMillis() - start)+" ActiveThreads: "+Thread.activeCount());
	
	 
	}
	
}
