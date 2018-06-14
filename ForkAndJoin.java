package com.wangguowei.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkAndJoin {
	public static void main(String[] args) {
		ForkAndJoin tt=new ForkAndJoin();
		
		CountTask countTask=tt.new CountTask(0, 8);
		if(countTask.isCompletedAbnormally())
		{
		    System.out.println(countTask.getException());
		}
		ForkJoinPool pool=new ForkJoinPool();
		Future<Integer> result=pool.submit(countTask);
		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class CountTask extends RecursiveTask<Integer>{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static final int THRESHOLD=2;
		private int start;
		private int end;
		public CountTask(int start,int end) {
			this.start=start;
			this.end=end;
		}
		@Override
		protected Integer compute() {
			System.out.println("compute thread :  "+Thread.currentThread().getName());
			int sum=0;
			// 如果任务足够小就执行计算的任务
			if(end-start<=THRESHOLD) {
				for(int i=start;i<=end;i++) {
					sum=sum+i;
				}
			}else {
				// 如果任务大于阈值，就分割为两个任务执行
				int middle=(end+start)/2;
				CountTask left=new CountTask(start,middle);
				CountTask right=new CountTask(middle+1, end);
				System.out.println("fork");
				// 执行子任务
				left.fork();
				right.fork();
				System.out.println("join");
				// 等待子任务执行完并得到其结果
				int resultLeft=left.join();
				System.out.println("resultLeft="+resultLeft);
				int resultRight=right.join();
				System.out.println("resultRight="+resultRight);
				// 合并子任务
				sum= resultLeft+resultRight;
			}
			return sum;
		}
	}
}
