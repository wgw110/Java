package com.wangguowei.demo;

import java.io.File;

public class IOTest10 {
	public static void main(String[] args) {
		String pathname = "D:" + File.separator + "out2.txt";
		File file = new File(pathname);
		// ����ļ��Ƿ���� ͨ������exists()���������Լ���ļ��Ƿ����
		System.out.println(file.exists());
		// �ļ����� ͨ������length()���Ի���ļ����ֽڳ���
		System.out.println(file.length());
		// ���������ƶ��ļ� ͨ������File���е�renameTo()��������������(�����ƶ�)�ļ�
		String deString = "D:" + File.separator + "out.txt";
		File dest=new File(deString);
		boolean success = file.renameTo(dest);
		System.out.println(success);
		// ���ĳ��·���Ƿ���Ŀ¼
		System.out.println(file.isDirectory());
		// ɾ���ļ�
		System.out.println(file.delete());
		// ��ȡĿ¼�е��ļ��б�
		File file2 = new File("D:");
		// list()�������ص�ǰFile����ָ���Ŀ¼�������ļ�����Ŀ¼���ַ�������(�����᷵����Ŀ¼�µ��ļ�������Ŀ¼����)
		String[] fileNames = file2.list();
		// listFiles()�������ص�ǰFile����ָ���Ŀ¼�������ļ�����Ŀ¼�������File����(��list()�������ƣ����᷵����Ŀ¼�µ��ļ�������Ŀ¼)
		File[] files = file2.listFiles();
		for (String s : fileNames) {
			System.out.println(s);
		}
		System.out.println("----------");
		for (File file3 : files) {
			System.out.println(file3.getName());
		}
	}

}
