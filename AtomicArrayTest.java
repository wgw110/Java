package com.wangguowei.demo;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArrayTest {
	private static AtomicIntegerArray integerArray=new AtomicIntegerArray(10);
	private static int array[]=new int[10];
	public static void main(String[] args) {
		AtomicArrayTest test=new AtomicArrayTest();
		System.out.println("Atomic:"+integerArray.toString());
		System.out.println("common:"+Arrays.toString(array) );
		for(int i=0;i<5000;i++) {
			new Thread(test.new ThreadTest(integerArray,array)).start();
		}
		 while(Thread.activeCount()>1)
	            Thread.yield();
		System.out.println("Atomic:"+integerArray.toString());
		System.out.println("common:"+Arrays.toString(array));
	}
	class ThreadTest implements Runnable {
		private  AtomicIntegerArray array;
		private int[] array2;
        public ThreadTest(AtomicIntegerArray array,int[] array2) {
			this.array=array;
			this.array2=array2;
		}
		@Override
		public void run() {
			for(int i=0;i<10;i++) {
				// getAndIncrement(i)数组i处的值加1
				array.getAndIncrement(i);
				array2[i]=array2[i]+1;
			}
			
		}
	}

}
