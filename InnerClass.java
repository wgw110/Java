package com.wangguowei.properties;

class Outer{
	private int i=2;
	private String string="wgw";
	private static  String f="ITEM";
	private void f1() {
		System.out.println("outer f1");
	}
	private static void f2() {
		System.out.println("outer f2");
	}
	public Inner getInner(int s,final String g) {
		int zz=2;
		final int ss=5;
		// 局部内部类
		class MethodInner{
			private int i=0;
			private String string="www";
			private void f1() {
				System.out.println("methodInner class"+i);
				System.out.println("outer class i"+Outer.this.i);
				System.out.println(s+g);
				System.out.println(zz); // 局部内部类虽然可以访问局部方法中非final的变量，但是不能改变其值(实际上对于局部内部类来说，局部方法中的变量都是final的）
				System.out.println(ss);
			}
		}
		new MethodInner().f1();
		System.out.println(new MethodInner().string);
		return new Outer().new Inner();
	}
	// 成员内部类
	class Inner{
		int i=2;
		String string="lsq";
		public void f1() {
			System.out.println("outer class i"+Outer.this.i);
			System.out.println("inner class i"+i);
		}
		public void f2() {
			Outer.this.f2();
			System.out.println(f);
		}
	}
	// 静态内部类
	static class staticClass{
		int i;
		public void f1() {
			System.out.println(i);
			System.out.println(f);
			f2();
		}
	}
}
public class InnerClass {
	public static void main(String[] args) {
		Outer outer=new Outer();
		
		// 创建成员内部类
		Outer.Inner inner=outer.getInner(5, "3");
		Outer.Inner inner2=new Outer().new Inner();
		
		// 创建静态内部类
		Outer.staticClass sClass=new Outer.staticClass();
		sClass.f1();
		
		new Thread() {
			@Override
			public void run() {
				System.out.println("匿名内部类，隐式的继承了Thread类并重写了其中的run方法");
			};
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return super.toString();
			}
		}.start();
	}

}
