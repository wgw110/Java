package com.wangguowei.demo;

import java.util.HashMap;
import java.util.Map;

public class Apple implements Comparable<Apple>{
	private int weight;
	private int price;
	private String type;
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "[type:"+type+","+"price:"+price+","+"weight:"+weight+"]";
	}
	@Override
	public int compareTo(Apple o) {
		if(this.getPrice()>o.getPrice()) {
			return -1;
		}
		if(this.getPrice()<o.getPrice()) {
			return 1;
		}
//		if(this.getWeight()>o.getWeight()) {
//			return 1;
//		}
//		if(this.getWeight()<o.getWeight()) {
//			return -1;
//		}
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		Apple apple=(Apple)obj;
		return this.weight==apple.weight && this.price==apple.price && this.type==apple.type;
	}
	
	public static void main(String[] args) {
		Apple apple0=new Apple();
		apple0.setPrice(20);
		apple0.setWeight(20);
		apple0.setType("20");
		Apple apple2=apple0;  // 这两个引用指向同一个内存中的对象，存储的值均为对象在堆的地址
		Apple apple=new Apple();
		apple.setPrice(20);
		apple.setWeight(20);
		apple.setType("20"); // 尽管新建的对象的各个成员变量值与之前的对象相同，但是任然是不同的两个对象
		System.out.println(apple2==apple0);
		System.out.println(apple.equals(apple0));
		Map<Apple, Integer> map=new HashMap<>();
		map.put(apple0, 1);
		System.out.println(map.get(apple2));
	}
}
