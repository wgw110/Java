package com.wangguowei.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TestException {
	public static void main(String[] args) {
		System.out.println(method2());
	}
	// throws�����ڷ����������У���ʾ�÷������ܻ��׳����쳣��Ȼ�󽻸��ϲ�������ķ�������������throws������Ŷ���쳣���ͣ�
	private static  void method() throws FileNotFoundException {
		File dFile=new File("");
		FileInputStream inputStream=new FileInputStream(dFile);
	}
	
	private static void method1(int index) {
		if(index<0) {
			throw new ArrayIndexOutOfBoundsException("�����±�Խ��");
		}
	}
	
	private static String method2() {
		try {
			FileInputStream inputStream=new FileInputStream("");
			return "456";
		}catch (Exception e) {
			System.out.println("exception e"+e);
			return "123";
		}finally {
			System.out.println("finally");
			return "489";
		}
	}
}
