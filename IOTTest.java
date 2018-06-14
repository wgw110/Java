package com.wangguowei.demo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class IOTTest {
	public static void main(String[] args) throws IOException {
		File f = new File("D:" + File.separator + "test.txt");
		Reader in = new FileReader(f);
		char[] b = new char[(int) f.length()];
		int i;
		int len=0;
		while((i=in.read())!=-1) {
			// 这里i是表示读取的一个字符的ASCII码，以十进制的形式存储，一个字符等于两个字节，所以这里i的范围为0~65535 
			//比如：如果读取的文件中有一个中文字符为“这”，那么当调用无参的read方法读取到“这”时，返回的是“这”的十进制ASCII码 36825
			System.out.println(i);
			b[len]=(char)i;
			System.out.println(b[len]);
			len++;
		}
		in.close();
		System.out.println(new String(b));
	}

}
