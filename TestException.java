package com.wangguowei.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TestException {
	public static void main(String[] args) {
		System.out.println(method2());
	}
	// throws出现在方法的声明中，表示该方法可能会抛出的异常，然后交给上层调用它的方法程序处理，允许throws后面跟着多个异常类型；
	private static  void method() throws FileNotFoundException {
		File dFile=new File("");
		FileInputStream inputStream=new FileInputStream(dFile);
	}
	
	private static void method1(int index) {
		if(index<0) {
			throw new ArrayIndexOutOfBoundsException("数组下标越界");
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
