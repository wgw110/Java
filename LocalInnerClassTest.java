package com.wangguowei.properties;

class LocalInner
{
    int a = 1;

    public void doSomething()
    {
        int b = 2;
        final int c = 3;
        // ����һ���ֲ��ڲ���
        class Inner3
        {
            public void test()
            {
                System.out.println("Hello World");
                System.out.println(a);

                // �����Է��ʷ�final�ľֲ�����
                // error: Cannot refer to a non-final variable b inside an inner
                // class defined in a different method
                 System.out.println(b);

                // ���Է���final����
                System.out.println(c);
            }
        }

        // �����ֲ��ڲ����ʵ�������÷���
        new Inner3().test();
    }
}

public class LocalInnerClassTest
{
    public static void main(String[] args)
    {
        // �����ⲿ�����
    	LocalInner inner = new LocalInner();
        // �����ⲿ��ķ���
        inner.doSomething();
    }

}