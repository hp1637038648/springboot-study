package com.hp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JAVA线程：线程池
 * 
 * @author hp
 */
public class Test1 {

	public static void main(String[] args) {
		// 创建一个可重用固定线程池数的线程池（固定大小的线程池）
		//ExecutorService pool = Executors.newFixedThreadPool(2);
		
		// 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程（单任务线程池 ）
		//ExecutorService pool = Executors.newSingleThreadExecutor();
		
		// 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们（可变尺寸的线程池 ）
		ExecutorService pool = Executors.newCachedThreadPool();
		
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口 
		Thread t1 = new MyThread();  
		Thread t2 = new MyThread();  
		Thread t3 = new MyThread();  
		Thread t4 = new MyThread();  
		Thread t5 = new MyThread(); 
		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown();
	}

	public static class MyThread extends Thread {
		public void run() {
			System.out.println(Thread.currentThread().getName() + "正在执行。。。");
		}
	}
}
