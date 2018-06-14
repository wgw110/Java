package com.wangguowei.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IOTest8 {
	public static void main(String[] args) {
		String filePath = "D://" + File.separator + "out2.txt";
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw");) {
			// 通过seek(index)方法把文件指针指向文件的index位置
			randomAccessFile.seek(100);
			// 通过调用getFilePointer()获得当前文件指针的位置
			long pointer = randomAccessFile.getFilePointer();
			System.out.println(pointer);
			// 读取RandomAccessFile
			int data = 0;
			// read()方法在读取完一个字节之后，会自动把指针移动到下一个可读字节,不需要手动移动文件指针。下面的代码从文件中index=100处开始读，直至读到最后
			while ((data = randomAccessFile.read()) != -1) {
				System.out.println((char) data);
			}

			// 再次获取当前文件指针的位置 此时由于读到了文件的末尾，所以得到的值应该是指向文件末尾的
			System.out.println(randomAccessFile.getFilePointer());
			// 写入RandomAccessFile 与read()方法类似，write()方法在调用结束之后自动移动文件指针
			String string = "hello world";
			randomAccessFile.write(string.getBytes());
			// 虽然向文件中添加了内容，但是此刻文件指针依然指向文件末尾
			System.out.println(randomAccessFile.getFilePointer());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
