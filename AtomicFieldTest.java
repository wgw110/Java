package com.wangguowei.demo;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicFieldTest {
	private static AtomicIntegerFieldUpdater<Student> atomicIntegerFieldUpdater=AtomicIntegerFieldUpdater.newUpdater(Student.class, "age");
	public static void main(String[] args) {
		Student student=new Student();
		student.setGrade("20");
		student.setAge(20);
		student.setName("wgw");
		System.out.println("update befaore:"+atomicIntegerFieldUpdater.getAndIncrement(student));
		System.out.println("update after:"+atomicIntegerFieldUpdater.get(student));
	}
}
