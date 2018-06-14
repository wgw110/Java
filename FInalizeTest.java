package com.wangguowei.demo;

class Book{
	boolean checkOut=false;
	public Book(boolean checkOut) {
		this.checkOut=checkOut;
	}
	void checkIn() {
		checkOut=true;
	}
	@Override
	protected void finalize() throws Throwable {
		if(checkOut) {
			System.out.println("error:check out");
		}
		super.finalize();
	}
}
public class FInalizeTest {
	public static void main(String[] args) {
		Book book=new Book(true);
		book.checkIn();
		new Book(true);
		System.gc();
	}

}
