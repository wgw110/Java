package com.wangguowei.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class NIOTest2 {
	public static void main(String[] args) {
		String src = "D:" + File.separator + "src.txt";
		String des = "D:" + File.separator + "des.txt";
		File srcFile = new File(src);
		File desFile = new File(des);
		System.out.println(srcFile.length());
		try (FileInputStream fromAccessFile = new FileInputStream(srcFile);
				FileOutputStream toFile = new FileOutputStream(desFile);
				FileChannel fromChannel=fromAccessFile.getChannel();
				FileChannel toChannel=toFile.getChannel()) {
                int postion=0;
                long count=fromChannel.size();
                long length=  fromChannel.transferTo(postion, count, toChannel);
                System.out.println(length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
