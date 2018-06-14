package com.wangguowei.demo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	public static void main(String[] args) {
		CountDownLatch latch=new CountDownLatch(5);
		System.out.println("main thread is running......");
		for(int i=0;i<5;i++) {
			ThreadA threadA=new ThreadA(latch);
			Thread thread=new Thread(threadA);
			thread.start();
		}
		System.out.println("main thread block");
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main thread continue");
	}
	static class ThreadA implements Runnable{
		private CountDownLatch latch;
		public ThreadA(CountDownLatch latch) {
			this.latch=latch;
		}
		@Override
		public void run() {
			System.out.println("thread "+Thread.currentThread().getName()+" doWork");
			dowork();
			System.out.println("thread "+Thread.currentThread().getName()+" endWork");
			latch.countDown();
		}
		private void dowork() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
