package com.wangguowei.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class Sum extends RecursiveTask<Long>
{

    /**
    * {����˵��}
    */
    private static final long serialVersionUID = 1L;
    private static final Long THRESHOLD = 100L;  // ��ֵ
    private Long start;
    private Long end;

    public Sum(Long start, Long end)
    {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute()
    {
        Long sum = 0L;
       // ��������㹻С��ִ�м��������
        if ((end - start) < THRESHOLD)
        {
            for (Long i = start; i <= end; i++)
            {
                sum += i;
            }
        } else
        {
        	// ������������ֵ���ͷָ�Ϊ��������ִ��
            Long middle = (start + end) / 2;
            Sum left = new Sum(start, middle);
            Sum right = new Sum(middle + 1, end);
           // ִ��������
            left.fork();
            right.fork();
           // �ϲ�������ִ�еĽ��
            sum = left.join() + right.join();
        }
        return sum;
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	 // ForkJoinPool�������̳߳� �����������߳�,���ṩ�����״̬��Ϣ,�Լ������ִ����Ϣ 
    	 ForkJoinPool pool = new ForkJoinPool();
    	 // �����ύ���̳߳�ִ�У�ͬʱ����һ��Future�������ڻ�ȡ�̵߳�ִ�н��
         Future<Long> result = pool.submit(new Sum(0L, 10000L));    
         System.out.println(result.get());
	}

}
