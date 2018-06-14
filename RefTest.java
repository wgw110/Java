package com.wangguowei.demo;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class RefTest {
	public static void main(String[] args) {
		ReferenceQueue <byte[]> referenceQueue=new ReferenceQueue<>();
		Object value = new Object();
		Map<Object, Object> map = new HashMap<>();
		for(int i = 0;i < 10000;i++) {
		    byte[] bytes = new byte[1024*1024*8];
		    WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes, referenceQueue);
		    map.put(weakReference, value);
		}
		Thread thread = new Thread(() -> {
		    try {
		        int cnt = 0;
		        WeakReference<byte[]> k;
		        while((k = (WeakReference) referenceQueue.remove()) != null) {
		            System.out.println((cnt++) + "回收了:" + k);
		        }
		    } catch(InterruptedException e) {
		        //结束循环
		    }
		});
		thread.setDaemon(true);
		thread.start();
		System.out.println("map.size->" + map.size());
	}

}
