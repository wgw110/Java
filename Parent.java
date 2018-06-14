package com.wangguowei.demo;

public class Parent {
	private int i=2;
	public void show() {
		System.out.println("parent i="+i);
	}
	public static void main(String[] args) {
		Parent parent=new child();
		parent.show();
	}

}
class child extends Parent{
	private int i=3;
	public void show() {
		System.out.println("child i="+i);
	}
}