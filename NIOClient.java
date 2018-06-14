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

public class NIOClient {
	public static void main(String[] args) {
		NIOClient client=new NIOClient();
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 30000);
		try(Selector selector=Selector.open();
				SocketChannel socketChannel=SocketChannel.open(isa);
				Scanner scanner=new Scanner(System.in);){
			socketChannel.configureBlocking(false);
			int ops=SelectionKey.OP_READ;
			socketChannel.register(selector, ops);
			client.new ClientThread(selector).start();
			while(scanner.hasNextLine()) {
				String line=scanner.nextLine();
				socketChannel.write(StandardCharsets.UTF_8.encode(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 class ClientThread extends Thread{
		 private Selector selector=null;
		 public ClientThread(Selector selector) {
			this.selector=selector;
		}
		@Override
		public void run() {
			try {
				while(selector.select()>0) {
					Set<SelectionKey> selectedKeys=selector.selectedKeys();
					Iterator<SelectionKey> iterator=selectedKeys.iterator();
					while(iterator.hasNext()) {
						SelectionKey selectionKey=iterator.next();
						if(selectionKey.isReadable()) {
							SocketChannel socketChannel=(SocketChannel) selectionKey.channel();
							ByteBuffer buffer=ByteBuffer.allocate(1024);
							String content = "";
							while(socketChannel.read(buffer)>0) {
								buffer.flip();
								 content += StandardCharsets.UTF_8.decode(buffer);
							}
							 System.out.println("¡ƒÃÏ–≈œ¢£∫" + content);
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
