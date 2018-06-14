package com.wangguowei.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOTest1 {
	public static void main(String[] args) throws IOException {
		File f = new File("D:" + File.separator + "test.txt");
		InputStream in = new FileInputStream(f);
		byte[] b = new byte[(int) f.length()];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) in.read();
			// 通过输出b[i]的值可知在字节数组b中存储的是一个个ASCII码，以十进制的形式存储
			// 即通过read()读取文件中的内容时，每次读取的其实是文件中一个个字母或者标点符号的十进制的ASCII码
			// 这里读取的是一个字节的ASCII码，范围为0~255
			System.out.println(b[i]);
		}
		
		System.out.println(new String(b));
		
		
		InputStream in2 = new FileInputStream(f);
		byte[] b2 = new byte[(int) f.length()];
		// read(byte[])是将读取的文件中的内容放入到一个字节数组byte[]中，返回值则是这个字节数组的长度，注意带参数的read方法与不带参数的read方法的返回值区别
		int length=in2.read(b2);
		System.out.println(length);
		System.out.println(new String(b));
		in2.close();
	}
}
