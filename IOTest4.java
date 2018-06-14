package com.wangguowei.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class IOTest4 {
	public static void main(String[] args) {
		File file=new File("D://"+File.separator+"out.txt");
		try {
			OutputStream  outputStream=new FileOutputStream(file);
			PrintStream printStream=new PrintStream(outputStream);
			System.setOut(printStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("123");  // 此时输出的值不再是显示在控制台，而是把“123”写到文件out.txt中
	}

}
