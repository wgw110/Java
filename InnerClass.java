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
		// �ֲ��ڲ���
		class MethodInner{
			private int i=0;
			private String string="www";
			private void f1() {
				System.out.println("methodInner class"+i);
				System.out.println("outer class i"+Outer.this.i);
				System.out.println(s+g);
				System.out.println(zz); // �ֲ��ڲ�����Ȼ���Է��ʾֲ������з�final�ı��������ǲ��ܸı���ֵ(ʵ���϶��ھֲ��ڲ�����˵���ֲ������еı�������final�ģ�
				System.out.println(ss);
			}
		}
		new MethodInner().f1();
		System.out.println(new MethodInner().string);
		return new Outer().new Inner();
	}
	// ��Ա�ڲ���
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
	// ��̬�ڲ���
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
		
		// ������Ա�ڲ���
		Outer.Inner inner=outer.getInner(5, "3");
		Outer.Inner inner2=new Outer().new Inner();
		
		// ������̬�ڲ���
		Outer.staticClass sClass=new Outer.staticClass();
		sClass.f1();
		
		new Thread() {
			@Override
			public void run() {
				System.out.println("�����ڲ��࣬��ʽ�ļ̳���Thread�ಢ��д�����е�run����");
			};
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return super.toString();
			}
		}.start();
	}

}
