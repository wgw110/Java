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
			// 创建buffer缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(30);
			// 读取channel通道中的数据并写到buffer缓冲区中，返回值为从channel中读取的字节的长度
			int length = fileChannel.read(buffer);
			System.out.println(length);
			while (length != -1) {
				buffer.flip(); // 转化到读模式，调用flip之后，position会指向buffer开头，而limit则指向buffer缓冲区所有可读数据区的下一个位置
				while (buffer.hasRemaining()) {
					System.out.println((char) buffer.get());
				}
				buffer.clear(); // 清空buffer缓冲区的内容，开始下次的写读
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
