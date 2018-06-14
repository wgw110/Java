package com.wangguowei.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class IOTest5 {
	public static void main(String[] args) {
		String pathname="D://"+File.separator+"out.txt";
		File file=new File(pathname);
		try {
			InputStream inputStream=new FileInputStream(file);
			InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
			Reader reader=new BufferedReader(inputStreamReader);
			int data=0;
			while ((data=reader.read())!=-1) {
				System.out.println((char)data);	
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
