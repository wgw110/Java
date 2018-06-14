package com.wangguowei.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IOTest8 {
	public static void main(String[] args) {
		String filePath = "D://" + File.separator + "out2.txt";
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw");) {
			// ͨ��seek(index)�������ļ�ָ��ָ���ļ���indexλ��
			randomAccessFile.seek(100);
			// ͨ������getFilePointer()��õ�ǰ�ļ�ָ���λ��
			long pointer = randomAccessFile.getFilePointer();
			System.out.println(pointer);
			// ��ȡRandomAccessFile
			int data = 0;
			// read()�����ڶ�ȡ��һ���ֽ�֮�󣬻��Զ���ָ���ƶ�����һ���ɶ��ֽ�,����Ҫ�ֶ��ƶ��ļ�ָ�롣����Ĵ�����ļ���index=100����ʼ����ֱ���������
			while ((data = randomAccessFile.read()) != -1) {
				System.out.println((char) data);
			}

			// �ٴλ�ȡ��ǰ�ļ�ָ���λ�� ��ʱ���ڶ������ļ���ĩβ�����Եõ���ֵӦ����ָ���ļ�ĩβ��
			System.out.println(randomAccessFile.getFilePointer());
			// д��RandomAccessFile ��read()�������ƣ�write()�����ڵ��ý���֮���Զ��ƶ��ļ�ָ��
			String string = "hello world";
			randomAccessFile.write(string.getBytes());
			// ��Ȼ���ļ�����������ݣ����Ǵ˿��ļ�ָ����Ȼָ���ļ�ĩβ
			System.out.println(randomAccessFile.getFilePointer());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
