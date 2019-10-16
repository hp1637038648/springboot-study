package com.hp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * java线程：延迟连接池
 * 
 * @author hp
 */
public class Test2 {

	public static void main(String[] args) {
		// 创建一个线程池，它可以安排在给定延迟后运行命令或者定期的执行
		//ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
		
		// 创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。(单任务延迟连接池) 
		ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();  

		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口  
		Thread t1 = new MyThread();  
		Thread t2 = new MyThread();  
		Thread t3 = new MyThread();  
		Thread t4 = new MyThread();  
		Thread t5 = new MyThread(); 
		//将线程放入池中进行执行  
		pool.execute(t1);  
		pool.execute(t2);  
		pool.execute(t3); 
		// 使用延迟执行风格的方法
		pool.schedule(t4, 10000, TimeUnit.MILLISECONDS);
		pool.schedule(t5, 10000, TimeUnit.MILLISECONDS);
		// 关闭线程池
		pool.shutdown();
	}

	public static class MyThread extends Thread {
		public void run() {
			System.out.println(Thread.currentThread().getName() + "正在执行。。。");
		}
	}
}
