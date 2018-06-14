package com.wangguowei.demo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NIOServer1 {
	public static void main(String[] args) {
		try(Selector selector=Selector.open();
				ServerSocketChannel server=ServerSocketChannel.open();){
			server.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
			int interests=SelectionKey.OP_ACCEPT;
			server.configureBlocking(false);
			server.register(selector, interests);
			while(selector.select()>0) {
				Set<SelectionKey> selectionKeys=selector.selectedKeys();
				Iterator<SelectionKey> iterator=selectionKeys.iterator();
				while(iterator.hasNext()) {
					SelectionKey selectionKey=iterator.next();
					if(selectionKey.isAcceptable()) {
						SocketChannel socket=server.accept();
						System.out.println("client address:"+socket.getRemoteAddress());
						int inter=SelectionKey.OP_READ;
						socket.configureBlocking(false);
						socket.register(selector, inter);
					}
					if(selectionKey.isReadable()) {
						SocketChannel channel=(SocketChannel) selectionKey.channel();
						ByteBuffer buffer=ByteBuffer.allocate(48);
						String context="";
						while(channel.read(buffer)>0) {
							buffer.flip();
							context+=StandardCharsets.UTF_8.decode(buffer);
							buffer.clear();
						}
						System.out.println("context="+context);
						if(context.length()>0) {
							for(SelectionKey channel2:selector.keys()) {
								if((channel2.channel()) instanceof SocketChannel) {
									SocketChannel socketChannel=(SocketChannel)channel2.channel();
									socketChannel.write(StandardCharsets.UTF_8.encode(context));
								}
							}
						}
					}
					iterator.remove();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
