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
		System.out.println("123");  // ��ʱ�����ֵ��������ʾ�ڿ���̨�����ǰѡ�123��д���ļ�out.txt��
	}

}
