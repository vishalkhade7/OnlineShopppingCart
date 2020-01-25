package com.vk.springboot.springbootasync.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class ScheduledTasks {

	@Autowired
	MultithreadService multithreadService;
	
	int count=0;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 2000)
	public void reportCurrentTime() {
	 System.out.println("Scheduler Thread: "+Thread.currentThread());
	 
	 count++;
	 List<String> list=new ArrayList<String>();
		for(int i=0;i<10;i++) {
			list.add(""+ count+"iterator" +i);
			
		}
		System.out.println("list Before multiThread: "+list.toString());
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
		list.parallelStream()
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
