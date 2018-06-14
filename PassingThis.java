package com.wangguowei.demo;

class Person {
	public void eat(Apples apple) {
		Apples peeled = apple.getPeeled();
		System.out.println("yummy");
	}
}
class Peelear {
	static Apples peel(Apples apple) {
		return apple;
	}
}

class Apples {
	Apples getPeeled() {
		return Peelear.peel(this);
	}
}

public class PassingThis {
	public static void main(String[] args) {
		new Person().eat(new Apples());
	}
}
