package com.wangguowei.demo;

import java.io.File;

public class IOTest10 {
	public static void main(String[] args) {
		String pathname = "D:" + File.separator + "out2.txt";
		File file = new File(pathname);
		// 检测文件是否存在 通过调用exists()方法，可以检测文件是否存在
		System.out.println(file.exists());
		// 文件长度 通过调用length()可以获得文件的字节长度
		System.out.println(file.length());
		// 重命名或移动文件 通过调用File类中的renameTo()方法可以重命名(或者移动)文件
		String deString = "D:" + File.separator + "out.txt";
		File dest=new File(deString);
		boolean success = file.renameTo(dest);
		System.out.println(success);
		// 检测某个路径是否是目录
		System.out.println(file.isDirectory());
		// 删除文件
		System.out.println(file.delete());
		// 读取目录中的文件列表
		File file2 = new File("D:");
		// list()方法返回当前File对象指向的目录中所有文件与子目录的字符串名称(但不会返回子目录下的文件及其子目录名称)
		String[] fileNames = file2.list();
		// listFiles()方法返回当前File对象指向的目录中所有文件与子目录相关联的File对象(与list()方法类似，不会返回子目录下的文件及其子目录)
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
