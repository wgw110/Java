package com.wangguowei.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Demo1 {
	public static void main(String[] args) {
		List<String> list=new ArrayList<>();
		for(int i=0;i<1000;i++) {
			list.add(i+"");
		}
		Demo1 demo1=new Demo1();
		ThreadA threadA=demo1.new ThreadA(list);
		ThreadB threadB=demo1.new ThreadB(list);
		threadB.start();
		threadA.start();
		
	}
	class ThreadA extends Thread{
		private List<String> list;
		public ThreadA(List<String> list) {
			this.list=list;
		}
		@Override
		public void run() {
			Iterator<String> iterator=list.iterator();
			while(iterator.hasNext()) {
				String string=iterator.next();
				if(string.equals("5")) {
					list.remove(string);
				}
				System.out.println(this.getName()+": "+string);
			}	
		}
	}
	class ThreadB extends Thread{
		private List<String> list;
		public ThreadB(List<String> list) {
			this.list=list;
		}
		@Override
		public void run() {
			Iterator<String> iterator=list.iterator();
			while (iterator.hasNext()) {
				String type = iterator.next();
				System.out.println(this.getName()+": "+type);
			}
		}
	}

}
