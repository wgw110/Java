package com.wangguowei.properties;

// ����Outer.class�ļ�
public class Outer {
	// ����Outer$VariableClass.class�ļ�
	private class VariableClass{}
   
	// ����Outer$StaticClass.class�ļ�
	static class StaticClass{}
	
	public void f1() {
		// ����Outer$1MethodClass.class�ļ�
		class MethodClass{}
    }
	public void f2() {
		// ����Outer$2MethodClass.class�ļ�
		class MethodClass{}
	}
	public static void main(String[] args) {
		Outer outer=new Outer();
		// ����Outer$1.class�ļ�
		new Thread() {}.start();
		// ����Outer$2.class�ļ�
		new Thread() {}.start();
	}

}
