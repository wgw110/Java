package com.wangguowei.demo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class IOTTest {
	public static void main(String[] args) throws IOException {
		File f = new File("D:" + File.separator + "test.txt");
		Reader in = new FileReader(f);
		char[] b = new char[(int) f.length()];
		int i;
		int len=0;
		while((i=in.read())!=-1) {
			// ����i�Ǳ�ʾ��ȡ��һ���ַ���ASCII�룬��ʮ���Ƶ���ʽ�洢��һ���ַ����������ֽڣ���������i�ķ�ΧΪ0~65535 
			//���磺�����ȡ���ļ�����һ�������ַ�Ϊ���⡱����ô�������޲ε�read������ȡ�����⡱ʱ�����ص��ǡ��⡱��ʮ����ASCII�� 36825
			System.out.println(i);
			b[len]=(char)i;
			System.out.println(b[len]);
			len++;
		}
		in.close();
		System.out.println(new String(b));
	}

}
