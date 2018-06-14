package com.wangguowei.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class Sum extends RecursiveTask<Long>
{

    /**
    * {变量说明}
    */
    private static final long serialVersionUID = 1L;
    private static final Long THRESHOLD = 100L;  // 阈值
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
       // 如果任务足够小就执行计算的任务
        if ((end - start) < THRESHOLD)
        {
            for (Long i = start; i <= end; i++)
            {
                sum += i;
            }
        } else
        {
        	// 如果任务大于阈值，就分割为两个任务执行
            Long middle = (start + end) / 2;
            Sum left = new Sum(start, middle);
            Sum right = new Sum(middle + 1, end);
           // 执行子任务
            left.fork();
            right.fork();
           // 合并子任务执行的结果
            sum = left.join() + right.join();
        }
        return sum;
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	 // ForkJoinPool类似于线程池 ，管理工作者线程,并提供任务的状态信息,以及任务的执行信息 
    	 ForkJoinPool pool = new ForkJoinPool();
    	 // 任务提交到线程池执行，同时返回一个Future对象用于获取线程的执行结果
         Future<Long> result = pool.submit(new Sum(0L, 10000L));    
         System.out.println(result.get());
	}

}
