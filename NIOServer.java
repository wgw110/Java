package com.wangguowei.demo;

import java.io.IOException;
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

public class NIOServer {
	public static void main(String[] args) {
		try(Selector selector=Selector.open();
				ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();){
			InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 30000);
			serverSocketChannel.socket().bind(isa);
			serverSocketChannel.configureBlocking(false);
			int ops=SelectionKey.OP_ACCEPT;
			serverSocketChannel.register(selector, ops);
			while(selector.select()>0) {
				Set<SelectionKey> selectedKeys=selector.selectedKeys();
				Iterator<SelectionKey> iterator=selectedKeys.iterator();
				while(iterator.hasNext()) {
					SelectionKey selectionKey2=iterator.next();
					if(selectionKey2.isAcceptable()) {
						SocketChannel socketChannel=serverSocketChannel.accept();
						System.out.println(socketChannel.getRemoteAddress());
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
					}
					if(selectionKey2.isReadable()) {
						SocketChannel socketChannel=(SocketChannel) selectionKey2.channel();
						System.out.println("�пͻ��˷��͹������ݣ��ͻ��˵�ַΪ��"+socketChannel.getRemoteAddress());
						ByteBuffer buffer=ByteBuffer.allocate(64);
						String context="";
						while(socketChannel.read(buffer)>0) {
							buffer.flip();
							context=context+StandardCharsets.UTF_8.decode(buffer);
							buffer.clear();
						}
						System.out.println("������Ϣ:"+context);
						if(context.length()>0) {
							 // ������selector��ע�������SelectKey
	                        for (SelectionKey key : selector.keys()) {
	                            // ��ȡ��key��Ӧ��Channel
	                            Channel targetChannel = key.channel();
	                            // �����channel��SocketChannel����
	                            if (targetChannel instanceof SocketChannel) {
	                                // ������������д���Channel��
	                                SocketChannel dest = (SocketChannel) targetChannel;
	                                dest.write(StandardCharsets.UTF_8.encode(context));
	                            }
	                        }
						}
					}
					iterator.remove();
				}		
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
