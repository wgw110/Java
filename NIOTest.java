package com.wangguowei.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {
	public static void main(String[] args) {
		String filePath = "D://" + File.separator + "out2.txt";
		File file = new File(filePath);
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
				FileChannel fileChannel = randomAccessFile.getChannel()) {
			// ����buffer������
			ByteBuffer buffer = ByteBuffer.allocate(30);
			// ��ȡchannelͨ���е����ݲ�д��buffer�������У�����ֵΪ��channel�ж�ȡ���ֽڵĳ���
			int length = fileChannel.read(buffer);
			System.out.println(length);
			while (length != -1) {
				buffer.flip(); // ת������ģʽ������flip֮��position��ָ��buffer��ͷ����limit��ָ��buffer���������пɶ�����������һ��λ��
				while (buffer.hasRemaining()) {
					System.out.println((char) buffer.get());
				}
				buffer.clear(); // ���buffer�����������ݣ���ʼ�´ε�д��
				System.out.println("length=" + length);
				length = fileChannel.read(buffer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
