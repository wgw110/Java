package com.wangguowei.demo;

public class GC {  
	  
    public static GC SAVE_HOOK = null;  
  
    public static void main(String[] args) throws InterruptedException {  
        SAVE_HOOK = new GC();  
        SAVE_HOOK = null;  
        System.gc();    // 通知虚拟机，希望进行一次垃圾回收，但不能保证垃圾回收一定会进行
        Thread.sleep(500);  
        if (null != SAVE_HOOK) { //此时对象应该处于(reachable, finalized)状态  
            System.out.println("Yes , I am still alive"+SAVE_HOOK);  
        } else {  
            System.out.println("No , I am dead");  
        }  
        SAVE_HOOK = null;  
        System.gc();   // 第二次调用gc()时，finalize方法不会再次被调用，finalize方法至多由GC执行一次
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
        SAVE_HOOK = this;   // finalize方法中，可将待回收对象赋值给GC Roots可达的对象引用，从而达到对象再生的目的
    }  
}  