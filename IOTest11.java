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
			// ���������ֽ��������͵�InputStream��
			pushbackInputStream.unread(array);
			// ������Ȼ֮ǰ�Ѿ���ȡ���ļ������ݣ��������ڰѶ�ȡ���������������͵����У������������ɻ���ļ���ʼ��ȡ�ļ�
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
