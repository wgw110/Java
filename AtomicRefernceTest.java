package com.wangguowei.demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicRefernceTest {
	private static AtomicInteger integer=new AtomicInteger(0);
	private static AtomicReference<Student> atomicReference=new AtomicReference<Student>();
	private static Student student2=new Student();
	private static Student student=new Student();
	static {
		student2.setAge(0);
		student2.setGrade("five");
		student2.setName("wgw");
		student.setAge(0);
	}
	public static void main(String[] args) {
		for(int i=0;i<1000;i++) {
			new Thread(new AtomicRefernceTest().new ThreadTest()).start();
		}
		while(Thread.activeCount()>1) {
			Thread.yield();
		}
	//	System.out.println("atomic:"+atomicReference.get());
		System.out.println("common:"+student2);
		System.out.println("atomic:"+atomicReference.get());
	}
	class ThreadTest implements Runnable{
       
		@Override
		public void run() {
			// 每次取上一个值并把age加1作为age
			student2.setAge(student2.getAge()+1);
			student.setAge(student.getAge()+1);
			atomicReference.set(student);
		}
	}

}
