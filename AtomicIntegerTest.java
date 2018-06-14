package com.wangguowei.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
	//����ԭ����
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static int count=0;
    public static void increase(){
        //ԭ�Ӳ�������
        atomicInteger.incrementAndGet();
    }       
    public static void main(String[] args){
        for (int i = 0; i < 5; i++){
            new Thread(new Runnable() {
                public void run() {
                    for(int j=0;j<1000;j++) {
                    	increase();
                    	count++;
                    }
                        
                }
            }).start();
        }
        while(Thread.activeCount()>1)
            Thread.yield();
        System.out.println(atomicInteger.get());
        System.out.println(AtomicIntegerTest.count);
    }
}
