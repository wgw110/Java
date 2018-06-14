package com.wangguowei.demo;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {
	public static void main(String[] args) {
		SoftReference<ReferenceTest> softReference=new SoftReference<ReferenceTest>(new ReferenceTest());
		// get方法返回软引用引用的对象的值
		System.out.println(softReference.get()); //返回值：引用的对象
		System.gc();  // 通知JVM进行一次GC，但不一定会执行
		System.out.println(softReference.get());  // 返回值：引用的对象 只有在内存不足时才会回收软引用关联的对象
		
		WeakReference<ReferenceTest> weakReference=new WeakReference<ReferenceTest>(new ReferenceTest());
		System.out.println(weakReference.get()); //返回值:引用的对象
		System.gc();
		System.out.println(weakReference.get()); //返回值：NULL 只要进行GC操作，弱引用引用的对象就会被回收
		
		ReferenceQueue<ReferenceTest> queue=new ReferenceQueue<>();
		// 虚引用必须和引用队列一起使用
		PhantomReference<ReferenceTest> phantomReference=new PhantomReference<ReferenceTest>(new ReferenceTest(), queue);
		System.out.println(phantomReference.get());  //返回值：NULL  虚引用的get()方法返回值总是null
	}
	@Override
	protected void finalize() throws Throwable {
		System.out.println("gc 执行前执行该方法");
		super.finalize();

	}
}
