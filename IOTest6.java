package com.wangguowei.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOTest6 {
	public static void main(String[] args) {
		String pathname = "D://" + File.separator + "out.txt";
		File file = new File(pathname);
		try (FileInputStream inputStream = new FileInputStream(file);
				InputStreamReader reader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(reader)) {
			String string = "";
			while ((string = bufferedReader.readLine()) != null) {
				System.out.println(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
