package com.wangguowei.properties;

// 生成Outer.class文件
public class Outer {
	// 生成Outer$VariableClass.class文件
	private class VariableClass{}
   
	// 生成Outer$StaticClass.class文件
	static class StaticClass{}
	
	public void f1() {
		// 生成Outer$1MethodClass.class文件
		class MethodClass{}
    }
	public void f2() {
		// 生成Outer$2MethodClass.class文件
		class MethodClass{}
	}
	public static void main(String[] args) {
		Outer outer=new Outer();
		// 生成Outer$1.class文件
		new Thread() {}.start();
		// 生成Outer$2.class文件
		new Thread() {}.start();
	}

}
