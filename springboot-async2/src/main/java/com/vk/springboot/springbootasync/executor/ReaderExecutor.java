package com.vk.springboot.springbootasync.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ReaderExecutor {

	int count = 0;

	@Async("threadPoolTaskExecutor")
	public CompletableFuture<String> runTask(String s) throws InterruptedException {
//		System.out.printf("Running task  thread: %s%n", Thread.currentThread().getName());
		System.out.println("Ruuning Thread: "+Thread.currentThread().getName()+" for number :"+s);
		Thread.sleep(3000);
    	return CompletableFuture.completedFuture(s);
	}

	public List<String> getList() {
		count++;

		if (count >= 3) {
			return new ArrayList<String>();
		}
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("" + count + "iterator" + i);

		}

		System.out.println("list Before multiThread: " + list.toString());
		return list;

	}

}
