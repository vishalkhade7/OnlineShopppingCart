package com.vk.springboot.springbootasync.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vk.springboot.springbootasync.executor.ReaderExecutor;

@Component
public class ScheduledTasks {

	@Autowired
	MultithreadService multithreadService;

	@Autowired
	ReaderExecutor readerExecutor;
	int count = 0;

	@Scheduled(fixedRate = 500)
	public void reportCurrentTime() throws InterruptedException, ExecutionException {
		System.out.println("Scheduler Thread: " + Thread.currentThread());



		multithreadService.getList().parallelStream().forEach(e -> {
			try {
				CompletableFuture<String> r= readerExecutor.runTask(e);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}      
			//System.out.println("result from task:" + r.get());
		});

		

		long start;
		/*
		 * start = System.currentTimeMillis(); list.forEach(e ->{ try {
		 * multithreadService.asyncProcess(e); } catch (InterruptedException e1) { //
		 * TODO Auto-generated catch block e1.printStackTrace(); } });
		 * System.out.println("Elapsed time without parallel stream: " +
		 * (System.currentTimeMillis() - start)
		 * +" ActiveThreads: "+Thread.activeCount());
		 */
		/*
		 * start = System.currentTimeMillis();
		 * multithreadService.getList().parallelStream() .forEach(e -> { try {
		 * multithreadService.asyncProcess(e); } catch (InterruptedException e1) {
		 * e1.printStackTrace(); } });
		 * 
		 * System.out.println("Elapsed time with parallel Stream: " +
		 * (System.currentTimeMillis() -
		 * start)+" ActiveThreads: "+Thread.activeCount());
		 */

	}

}
