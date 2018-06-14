package com.wangguowei.properties;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestProperties {
	private static final String FILE_NAME="config.properties";
	public static void main(String[] args) {
		 InputStream inputStream=null;
		 System.out.println(Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME));
//		inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME);
//		Properties properties=new Properties();
//		try {
//			properties.load(inputStream);
//			System.out.println(properties.getProperty("restful.baseurl"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}

}
