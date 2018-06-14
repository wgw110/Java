package com.wangguowei.demo;

public class Student {
	public volatile int age;
	private String name;
	private String grade;

	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGrade() {
		return grade;
	}
	@Override
	public String toString() {
		return age+","+name+","+grade;
	}
}
