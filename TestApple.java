package com.wangguowei.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TestApple {
	public static void main(String[] args) {
		List<Apple> apples=new ArrayList<>();
		Random random = new Random();
		for(int i=0;i<10;i++) {
			Apple apple=new Apple();
			apple.setPrice(random.nextInt(50));
			apple.setWeight(1000);
			apple.setType("type"+i);
			apples.add(apple);
		}
		System.out.println("sort before......");
		for(Apple apple:apples) {
			System.out.println(apple);
		}
		Collections.sort(apples, (o1,o2)->(o2.getWeight()-o1.getWeight()));
//		Collections.sort(apples, new Comparator<Apple>() {
//			@Override
//			public int compare(Apple o1,Apple o2) {
//				if(o1.getPrice()>o2.getPrice()) {
//					return 1;
//				}
//				if(o1.getPrice()<o2.getPrice()) {
//					return -1;
//				}
//				if(o1.getWeight()>o2.getWeight()) {
//					return -1;
//				}
//				if(o1.getWeight()<o2.getWeight()) {
//					return 1;
//				}
//				return 0;
//			}
//		});
		System.out.println("sort after......");
		for(Apple apple:apples) {
			System.out.println(apple);
		}
	}

}
