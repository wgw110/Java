package com.wangguowei.demo;

public class SS {
	public static void main(String[] args) {
		String string=",";
		String[] strings=string.split(",");
		for(int i=0;i<strings.length;i++) {
			System.out.println(i+strings[i]);
		}
	}

}
