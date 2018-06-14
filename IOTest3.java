package com.wangguowei.demo;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class IOTest3 {
	public static void main(String[] args) throws IOException {
		final  PipedOutputStream outputStream=new PipedOutputStream();
		final  PipedInputStream inputStream=new PipedInputStream(outputStream);
		IOTest3 test3=new IOTest3();
		ThreadA threadA=test3.new ThreadA(outputStream);
		ThreadB threadB=test3.new ThreadB(inputStream);
		threadA.start();
		threadB.start();

	}
	class ThreadA extends Thread{
		private PipedOutputStream outputStream;
		public ThreadA(PipedOutputStream outputStream) {
			this.outputStream=outputStream;
		}
		@Override
		public void run() {
			String string="wgw";
			int i=0;
			while(i<3) {
				byte[] b=string.getBytes();
			   try {
				outputStream.write(b, 0, b.length);
				i++;
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		}
	}
	
	class ThreadB extends Thread{
		private PipedInputStream inputStream;
		public ThreadB(PipedInputStream inputStream) {
			this.inputStream=inputStream;
		}
		@Override
		public void run() {
				try {
					int data=0;
					while((data=inputStream.read())!=-1) {
						System.out.println((char)data);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					try {
						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
	}
}
