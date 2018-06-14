package com.wangguowei.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOTest2 {
	public static void main(String[] args) throws IOException {
		File f = new File("D:" + File.separator + "test.txt");
		InputStream in = new FileInputStream(f);
		byte[] b = new byte[(int) f.length()];
		int i;
		int len=0;
		while((i=in.read())!=-1) {
			b[len]=(byte)i;
			System.out.println(i);
		//	System.out.println(b[len]);
			len++;
		}
		in.close();
		System.out.println(new String(b));
	}

}
