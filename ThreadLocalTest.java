package com.wangguowei.demo;

public class ThreadLocalTest {
	private static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;};
	};
	public static void main(String[] args) {
		for(int i=0;i<5;i++) {
			new Thread(new ThreadA()).start();
		}
	}
	static class ThreadA implements Runnable{
		@Override
		public void run() {
			System.out.println("Thread "+Thread.currentThread().getName()+" init value is:"+threadLocal.get());
			for(int i=0;i<10;i++) {
				threadLocal.set(i+threadLocal.get());
			}
			System.out.println("Thread "+Thread.currentThread().getName()+" count is:"+threadLocal.get());
			
		}
	}

}
