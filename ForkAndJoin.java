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
			// ��������㹻С��ִ�м��������
			if(end-start<=THRESHOLD) {
				for(int i=start;i<=end;i++) {
					sum=sum+i;
				}
			}else {
				// ������������ֵ���ͷָ�Ϊ��������ִ��
				int middle=(end+start)/2;
				CountTask left=new CountTask(start,middle);
				CountTask right=new CountTask(middle+1, end);
				System.out.println("fork");
				// ִ��������
				left.fork();
				right.fork();
				System.out.println("join");
				// �ȴ�������ִ���겢�õ�����
				int resultLeft=left.join();
				System.out.println("resultLeft="+resultLeft);
				int resultRight=right.join();
				System.out.println("resultRight="+resultRight);
				// �ϲ�������
				sum= resultLeft+resultRight;
			}
			return sum;
		}
	}
}
