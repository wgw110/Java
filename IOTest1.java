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
			// ͨ�����b[i]��ֵ��֪���ֽ�����b�д洢����һ����ASCII�룬��ʮ���Ƶ���ʽ�洢
			// ��ͨ��read()��ȡ�ļ��е�����ʱ��ÿ�ζ�ȡ����ʵ���ļ���һ������ĸ���߱����ŵ�ʮ���Ƶ�ASCII��
			// �����ȡ����һ���ֽڵ�ASCII�룬��ΧΪ0~255
			System.out.println(b[i]);
		}
		
		System.out.println(new String(b));
		
		
		InputStream in2 = new FileInputStream(f);
		byte[] b2 = new byte[(int) f.length()];
		// read(byte[])�ǽ���ȡ���ļ��е����ݷ��뵽һ���ֽ�����byte[]�У�����ֵ��������ֽ�����ĳ��ȣ�ע���������read�����벻��������read�����ķ���ֵ����
		int length=in2.read(b2);
		System.out.println(length);
		System.out.println(new String(b));
		in2.close();
	}
}
