package com.wangguowei.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IOTest7 {
	public static void main(String[] args) {
		String string="wgw w Ê¢´óµÄ 545";
		String pathname="D://"+File.separator+"out2.txt";
		File sFile=new File(pathname);
		try(FileWriter writer=new FileWriter(sFile)) {
			writer.write(string);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
