package com.wangguowei.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * �ڴ����������
 * @author User
 *
 */
public class OOMTest {
	static Vector vector=new Vector<>();
	public static void main(String[] args) {
		for(int i=0;i<5;i++) {
			Object object=new Object();
			System.out.println(object);
			vector.add(object);
			object=null;  // ������Ϊnull���Ƕ������ɴ��ڣ�ֻ��ָ���������ò�������
		}
		System.out.println(vector.get(0));
	}
	
	private static  void heapOut() {
		List<Apple> list=new ArrayList<>();
		while(true) {
			Apple apple=new Apple();
			list.add(apple);
		}
	}
	
	private static void stackOverFlow(int i) {
		i++;
		stackOverFlow(i);
	}
	
	static class ThreadTest implements Runnable{
		private static List<Apple> list;
		@Override
		public void run() {
			while(true) {
//				Apple apple=new Apple();
//				list.add(apple);
			}
			
		}
	}
}
