package com.wangguowei.demo;


import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public class WeakHashMapTest {
	public static void main(String[] args) {
		WeakHashMap<String, String>  weakHashMap=new WeakHashMap<>();
		String aString=new String("aa");
		String bString=new String("bb");
		String cString=new String("cc");
		weakHashMap.put(aString, "aa");
		weakHashMap.put(bString, "bb");
		weakHashMap.put(cString, "cc");
		Iterator<Entry<String, String>> iterator=weakHashMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, String> entry=iterator.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		aString=null;
		bString=null;
		System.gc();
		System.out.println("garbage collection.......");
		Iterator<Entry<String, String>> iterator2=weakHashMap.entrySet().iterator();
		while(iterator2.hasNext()) {
			Entry<String, String> entry=iterator2.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}

}
