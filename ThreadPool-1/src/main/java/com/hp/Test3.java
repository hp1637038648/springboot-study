package com.hp;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * Java线程：线程池-自定义线程池
 */
public class Test3 {
	
	public static void main(String[] args) {
		// 创建等待队列
		BlockingQueue bqueue = new ArrayBlockingQueue(20);
		// 创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。  
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2,3,10000,TimeUnit.MILLISECONDS,bqueue);
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口  
		Thread t1 = new MyThread();  
		Thread t2 = new MyThread();  
		Thread t3 = new MyThread();  
		Thread t4 = new MyThread();  
		Thread t5 = new MyThread();  
		Thread t6 = new MyThread();  
		Thread t7 = new MyThread();
		// 将线程放入池中进行执行  
		pool.execute(t1);  
		pool.execute(t2);  
		pool.execute(t3);  
		pool.execute(t4);  
		pool.execute(t5);  
		pool.execute(t6);  
		pool.execute(t7); 
		// 关闭线程池
		pool.shutdown();
	}
	
	public static class MyThread extends Thread {
		public void run() {
			System.out.println(Thread.currentThread().getName() + "正在执行。。。");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
