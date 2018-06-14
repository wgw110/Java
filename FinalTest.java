package com.wangguowei.properties;

import java.util.Arrays;

public class FinalTest {
	public static void main(String[] args) {
		String[] allowItems= {"uppercase","lowercase","number","other"};
		System.out.println(Arrays.asList(allowItems).contains("upper"));
		System.out.println(Arrays.asList(allowItems).contains("other"));
		
	}

}
