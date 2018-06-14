package com.wangguowei.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest1 {
	public static void main(String[] args) {
		String filePath = "D://" + File.separator + "out2.txt";
		File file=new File(filePath);
		String srcPath= "D://" + File.separator + "out3.txt";
		File file2=new File(srcPath);
		System.out.println(filePath.length());
		try(RandomAccessFile randomAccessFile=new RandomAccessFile(file, "rw");
				FileChannel fileChannel=randomAccessFile.getChannel();
				RandomAccessFile randomAccessFile2=new RandomAccessFile(file2, "rw");
				FileChannel fileChannel2=randomAccessFile2.getChannel()){
			ByteBuffer headBuffer=ByteBuffer.allocate(30);
			ByteBuffer bodyBuffer=ByteBuffer.allocate(50);
			ByteBuffer[] array= {headBuffer,bodyBuffer};
			fileChannel.read(array);
			headBuffer.flip();
			bodyBuffer.flip();
			while(headBuffer.hasRemaining()) {
				System.out.println((char)headBuffer.get());
			}
			System.out.println("-----------------------");
			while(bodyBuffer.hasRemaining()) {
				System.out.println((char)bodyBuffer.get());
			}
			headBuffer.flip();
			bodyBuffer.flip();
			fileChannel2.write(array);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
