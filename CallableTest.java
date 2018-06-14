package com.wangguowei.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableTest {
	public static void main(String[] args) {
		ExecutorService service=Executors.newCachedThreadPool();
		ThreadA threadA=new ThreadA();
		Future<String> sFuture=service.submit(threadA);
		try {
			System.out.println(sFuture.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	static class ThreadA implements Callable<String>{

		@Override
		public String call() throws Exception {
			System.out.println("callable thread is running......");
			Thread.sleep(1000);
			return "callable";
		}
		
	}

}
