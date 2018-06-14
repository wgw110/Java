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
    // THRESHOLD限定在子任务规模较小时，使用直接排序，而不是再将其分割成为更小的任务，当子任务中待排序的子数组长度小于8的时候，直接进行排序
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
            // RecursiveAction 提供的方法 invokeAll()。它表示：启动所有的任务，并在所有任务都正常结束后返回。如果其中一个任务出现异常，则其它所有的任务都取消。invokeAll() 的参数还可以是任务的数组。
            invokeAll(new SortTask(array, lo, pivot - 1), new SortTask(array,
                    pivot + 1, hi));
        }
	}
    // partition() 方法将数组分成两个部分  从array[lo]到array[hi]的子数组中，把比array[hi]小的放在子数组的前面
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
       // 将 ForkJoinTask 类的对象提交给 ForkJoinPool，ForkJoinPool 将立刻开始执行 ForkJoinTask。即把任务交由线程池执行
       fjpool.submit(sort);
       // 执行此方法之后，ForkJoinPool 不再接受新的任务，但是已经提交的任务可以继续执行。如果希望立刻停止所有的任务，可以尝试 shutdownNow() 方法。
       fjpool.shutdown();
       // awaitTermination()：阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束。
       fjpool.awaitTermination(30, TimeUnit.SECONDS);
       System.out.println("end Array:"+Arrays.toString(array));
   }
}
