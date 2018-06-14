package com.wangguowei.demo;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class SortTask extends RecursiveAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long[] array;
    private final int lo;
    private final int hi;
    // THRESHOLD�޶����������ģ��Сʱ��ʹ��ֱ�����򣬶������ٽ���ָ��Ϊ��С�����񣬵��������д�����������鳤��С��8��ʱ��ֱ�ӽ�������
    private int THRESHOLD =8; //For demo only  
    public SortTask(long[] array) {
        this.array = array;
        this.lo = 0;
        this.hi = array.length - 1;
    }
    public SortTask(long[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }
	@Override
	protected void compute() {
		if (hi - lo < THRESHOLD)
            sequentiallySort(array, lo, hi);
        else {
            int pivot = partition(array, lo, hi);
            System.out.println("\npivot = " + pivot + ", low = " + lo + ", high = " + hi);
            System.out.println("array" + Arrays.toString(array));
            // RecursiveAction �ṩ�ķ��� invokeAll()������ʾ���������е����񣬲��������������������󷵻ء��������һ����������쳣�����������е�����ȡ����invokeAll() �Ĳ�������������������顣
            invokeAll(new SortTask(array, lo, pivot - 1), new SortTask(array,
                    pivot + 1, hi));
        }
	}
    // partition() ����������ֳ���������  ��array[lo]��array[hi]���������У��ѱ�array[hi]С�ķ����������ǰ��
	private int partition(long[] array, int lo, int hi) {
		long x = array[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (array[j] <= x) {
                i++;
                swap(array, i, j);
            }
        }
        System.out.println("partition array="+Arrays.toString(array));
        swap(array, i + 1, hi);
        return i + 1;
	}
	private void swap(long[] array, int i, int j) {
		if (i != j) {
            long temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
	}
	private void sequentiallySort(long[] array, int lo, int hi) {
		System.out.println("method sequentiallySort");
		 Arrays.sort(array, lo, hi + 1);
	}
   public static void main(String[] args) throws InterruptedException {
	   long[] array = new long[16];
	   Random rand = new Random();
	   for (int i = 0; i < array.length; i++) {
           array[i] = rand.nextLong()%100; //For demo only
       }
	   System.out.println("Initial Array: " + Arrays.toString(array));
	   SortTask sort = new SortTask(array);
       ForkJoinPool fjpool = new ForkJoinPool();
       // �� ForkJoinTask ��Ķ����ύ�� ForkJoinPool��ForkJoinPool �����̿�ʼִ�� ForkJoinTask�������������̳߳�ִ��
       fjpool.submit(sort);
       // ִ�д˷���֮��ForkJoinPool ���ٽ����µ����񣬵����Ѿ��ύ��������Լ���ִ�С����ϣ������ֹͣ���е����񣬿��Գ��� shutdownNow() ������
       fjpool.shutdown();
       // awaitTermination()��������ǰ�߳�ֱ�� ForkJoinPool �����е�����ִ�н�����
       fjpool.awaitTermination(30, TimeUnit.SECONDS);
       System.out.println("end Array:"+Arrays.toString(array));
   }
}
