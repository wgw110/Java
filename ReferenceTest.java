package com.wangguowei.demo;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {
	public static void main(String[] args) {
		SoftReference<ReferenceTest> softReference=new SoftReference<ReferenceTest>(new ReferenceTest());
		// get�����������������õĶ����ֵ
		System.out.println(softReference.get()); //����ֵ�����õĶ���
		System.gc();  // ֪ͨJVM����һ��GC������һ����ִ��
		System.out.println(softReference.get());  // ����ֵ�����õĶ��� ֻ�����ڴ治��ʱ�Ż���������ù����Ķ���
		
		WeakReference<ReferenceTest> weakReference=new WeakReference<ReferenceTest>(new ReferenceTest());
		System.out.println(weakReference.get()); //����ֵ:���õĶ���
		System.gc();
		System.out.println(weakReference.get()); //����ֵ��NULL ֻҪ����GC���������������õĶ���ͻᱻ����
		
		ReferenceQueue<ReferenceTest> queue=new ReferenceQueue<>();
		// �����ñ�������ö���һ��ʹ��
		PhantomReference<ReferenceTest> phantomReference=new PhantomReference<ReferenceTest>(new ReferenceTest(), queue);
		System.out.println(phantomReference.get());  //����ֵ��NULL  �����õ�get()��������ֵ����null
	}
	@Override
	protected void finalize() throws Throwable {
		System.out.println("gc ִ��ǰִ�и÷���");
		super.finalize();

	}
}
