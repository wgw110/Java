package com.wangguowei.demo;

public class VarargsTest {
	public static void main(String[] args) {
		fun(2);
		fun("33");
		fun(2,"3");
		fun(2,2);
		fun(2,3,4);
	}
    public static  void  fun(int a) {
    	System.out.println(a);
    }
    public static void fun(String a) {
    	System.out.println(a);
    }
    public static void fun(int b,Object ...objects) {
    	System.out.println('s');
    }
    public static  void fun(int c,Object d) {
    	System.out.println('d');
    }
}
