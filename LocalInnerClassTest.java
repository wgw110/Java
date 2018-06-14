package com.wangguowei.properties;

class LocalInner
{
    int a = 1;

    public void doSomething()
    {
        int b = 2;
        final int c = 3;
        // 定义一个局部内部类
        class Inner3
        {
            public void test()
            {
                System.out.println("Hello World");
                System.out.println(a);

                // 不可以访问非final的局部变量
                // error: Cannot refer to a non-final variable b inside an inner
                // class defined in a different method
                 System.out.println(b);

                // 可以访问final变量
                System.out.println(c);
            }
        }

        // 创建局部内部类的实例并调用方法
        new Inner3().test();
    }
}

public class LocalInnerClassTest
{
    public static void main(String[] args)
    {
        // 创建外部类对象
    	LocalInner inner = new LocalInner();
        // 调用外部类的方法
        inner.doSomething();
    }

}