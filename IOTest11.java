package com.wangguowei.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class IOTest11 {
	public static void main(String[] args) {
		String pathname = "D:" + File.separator + "out.txt";
		File file = new File(pathname);
		try(InputStream inputStream=new FileInputStream(file);
				PushbackInputStream pushbackInputStream=new PushbackInputStream(inputStream,10)){
			byte [] array=new byte[10];
			int length=pushbackInputStream.read(array);
			System.out.println(length);
			System.out.println(new String(array, 0, length));
			// 将读到的字节重新推送到InputStream流
			pushbackInputStream.unread(array);
			// 这里虽然之前已经读取过文件的内容，但是由于把读取的内容又重新推送到流中，所以这里依旧会从文件开始读取文件
			while((length=pushbackInputStream.read(array))!=-1) {
				System.out.println(new String(array, 0, length));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
