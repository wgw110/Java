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
			    	buffer.flip();  // 从写模式转化为读模式
			    	channel2.write(buffer);  // 把buffer缓冲区的内容写入通道channel2
			    	buffer.rewind(); // 由于上面已经对缓冲区的数据读过一次了,所以如果想要再次读取缓冲区的内容务必要调用buffer的rewind方法表示从新开始读取这个buffer中的内容
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
