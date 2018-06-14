package com.wangguowei.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class NIOTest3 {
	public static void main(String[] args) {
		try(RandomAccessFile file=new RandomAccessFile( "D://" + File.separator + "out2.txt","rw");
				FileChannel channel=file.getChannel();
				RandomAccessFile file2=new RandomAccessFile("D://" + File.separator + "out3.txt", "rw");
				FileChannel channel2=file2.getChannel()){
			    System.out.println(file.length());
			    ByteBuffer buffer=ByteBuffer.allocate(64);
			    String context="";
			    long length=0;
			    while((length=channel.read(buffer))>0) {
			    	System.out.println(length);
			    	buffer.flip();  // ��дģʽת��Ϊ��ģʽ
			    	channel2.write(buffer);  // ��buffer������������д��ͨ��channel2
			    	buffer.rewind(); // ���������Ѿ��Ի����������ݶ���һ����,���������Ҫ�ٴζ�ȡ���������������Ҫ����buffer��rewind������ʾ���¿�ʼ��ȡ���buffer�е�����
			    	context=context+ StandardCharsets.UTF_8.decode(buffer);
			    	buffer.clear();
			    }
			    System.out.println("context="+context);
			    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
