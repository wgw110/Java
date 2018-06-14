package com.wangguowei.demo;

public class ExceptionTest {
	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			try {
				if (i % 2 == 0) {
					i = i / 0;
				} else {
					System.out.println("i=" + i);
				}
			} catch (Exception e) {
				System.out.println("exception !!!");
			}
		}
		System.out.println("haha");
	}

}
