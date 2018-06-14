package com.wangguowei.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
	public static void main(String[] args) {
		CyclicBarrier barrier=new CyclicBarrier(4, new ThreadTest());
		System.out.println("main thread is running......");
		for(int i=0;i<3;i++) {
			Thread thread=new Thread(new ThreadT(barrier));
			thread.start();
		}
		try {
			Thread.sleep(1000);
			System.out.println("main thread is blocked......");
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("main thread continue running");
	}
	static class ThreadT implements Runnable{
		private CyclicBarrier barrier;
		public ThreadT(CyclicBarrier barrier) {
			this.barrier=barrier;
		}
		@Override
		public void run() {
			System.out.println("Thrad "+Thread.currentThread().getName()+" is running......");
			doWork();
			System.out.println("thread "+Thread.currentThread().getName()+" is blocked......");
			try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println("Thrad "+Thread.currentThread().getName()+" continue running");
		}
		private void doWork() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	static class ThreadTest implements Runnable{

		@Override
		public void run() {
			System.out.println("all thread is ready,start execute");
			
		}
		
	}

}
