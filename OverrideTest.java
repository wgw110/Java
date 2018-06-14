package com.wangguowei.demo;

class Parent2{
	Object ff() {
		return new Object();
	} 
}
public class OverrideTest extends Parent2{
	@Override
	public String ff() {
		return "2";
	}
	public static void main(String[] args) {
		Parent2 parent=new OverrideTest();
		System.out.println((parent.ff()));
	}
}
