package com.wangguowei.demo;

import java.io.Serializable;
import java.util.Map;

public class CatalogResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Student student;
	private Apple apple;
	private Map<String, String> tt;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Apple getApple() {
		return apple;
	}
	public void setApple(Apple apple) {
		this.apple = apple;
	}
	public Map<String, String> getTt() {
		return tt;
	}
	public void setTt(Map<String, String> tt) {
		this.tt = tt;
	}
	

}
