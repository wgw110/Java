package com.wangguowei.demo;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class NIOTest4 {
	public static void main(String[] args) {
		String path="C:\\Users\\User\\Desktop\\time.txt";
		File file=new File(path);
		try(RandomAccessFile randomAccessFile=new RandomAccessFile(file, "rw");
				FileChannel channel=randomAccessFile.getChannel();){
			ByteBuffer buffer=ByteBuffer.allocate(256);
			int length=channel.read(buffer);
	//		System.out.println(length);
			System.out.println(buffer.limit());
			System.out.println(buffer.position());
			System.out.println(buffer.capacity());
			buffer.flip();
			System.out.println(buffer.limit());
			System.out.println(buffer.position());
			System.out.println(buffer.capacity());
			String context=""+StandardCharsets.UTF_8.decode(buffer);
//			System.out.println(context);
			buffer.rewind();
			System.out.println(buffer.limit());
			System.out.println(buffer.position());
			System.out.println(buffer.capacity());
			ByteBuffer buffer2=ByteBuffer.allocate(48);
			int length2=channel.read(buffer2);
			System.out.println(length2);
			String context2=""+StandardCharsets.UTF_8.decode(buffer2);
			System.out.println(context2);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
