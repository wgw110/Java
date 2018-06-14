package com.wangguowei.demo;

public class GC {  
	  
    public static GC SAVE_HOOK = null;  
  
    public static void main(String[] args) throws InterruptedException {  
        SAVE_HOOK = new GC();  
        SAVE_HOOK = null;  
        System.gc();    // ֪ͨ�������ϣ������һ���������գ������ܱ�֤��������һ�������
        Thread.sleep(500);  
        if (null != SAVE_HOOK) { //��ʱ����Ӧ�ô���(reachable, finalized)״̬  
            System.out.println("Yes , I am still alive"+SAVE_HOOK);  
        } else {  
            System.out.println("No , I am dead");  
        }  
        SAVE_HOOK = null;  
        System.gc();   // �ڶ��ε���gc()ʱ��finalize���������ٴα����ã�finalize����������GCִ��һ��
        Thread.sleep(500);  
        if (null != SAVE_HOOK) {  
            System.out.println("Yes , I am still alive");  
        } else {  
            System.out.println("No , I am dead"+SAVE_HOOK);  
        }  
    }  
  
    @Override  
    protected void finalize() throws Throwable {  
        super.finalize();  
        System.out.println("execute method finalize()");  
        SAVE_HOOK = this;   // finalize�����У��ɽ������ն���ֵ��GC Roots�ɴ�Ķ������ã��Ӷ��ﵽ����������Ŀ��
    }  
}  