package com.wangguowei.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NIOClient1 {
	public static void main(String[] args) {
		try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8000));
				Selector selector = Selector.open();
				Scanner in = new Scanner(System.in);) {
			int interests = SelectionKey.OP_READ | SelectionKey.OP_CONNECT;
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, interests);	
			new Thread(new ThreadTask(selector)).start();
			while (in.hasNext()) {
				String line = in.nextLine();
				socketChannel.write(StandardCharsets.UTF_8.encode(line));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static class ThreadTask implements Runnable{
		private Selector selector;
		public ThreadTask(Selector selector) {
			this.selector=selector;
		}
		@Override
		public void run() {
			try {
				System.out.println("execute selector");
				while(selector.select()>0) {
					Set<SelectionKey> keys=selector.selectedKeys();
					Iterator<SelectionKey> iterator=keys.iterator();
					while(iterator.hasNext()) {
						SelectionKey key=iterator.next();
						if(key.isConnectable()) {
							System.out.println("连接就绪");
							SocketChannel socketChannel=(SocketChannel) key.channel();
							System.out.println(socketChannel.getRemoteAddress());
						}
						if(key.isReadable()) {
							System.out.println("收到来自服务器的信息");
							String context="";
							SocketChannel socketChannel=(SocketChannel) key.channel();
							ByteBuffer byteBuffer=ByteBuffer.allocate(48);
							while(socketChannel.read(byteBuffer)>0) {
								byteBuffer.flip();
								System.out.println(StandardCharsets.UTF_8.decode(byteBuffer));
								byteBuffer.clear();
							}
							System.out.println(context);
						}
						iterator.remove();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
