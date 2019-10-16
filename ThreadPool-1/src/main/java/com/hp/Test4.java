package com.hp;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 使用ThreadPoolExecutor创建多线程例子
 */
public class Test4 {
	
	private static int produceTaskSleepTime = 2000;
	private static int produceTaskMaxNumber = 10;
	
	public static void main(String[] args) {
		// 构造一个线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
		for (int i = 1; i < produceTaskMaxNumber; i++) {
			try {
				// 产生一个任务，并将其加入到线程池
				String task = "task@ " + i;
				System.out.println("put " + task);
				threadPool.execute(new ThreadPoolTask(task));
				
				// 便于观察，睡眠一段时间
				Thread.sleep(produceTaskSleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		threadPool.shutdown();
	}

	/**
	 * 线程池执行的任务
	 */
	static class ThreadPoolTask implements Runnable,Serializable {
		private static final long serialVersionUID = 1L;
		private static int consumeTaskSleepTime = 10000;
		// 保存任务所需要的数据
		private Object threadPoolTaskData;
		
		public ThreadPoolTask(Object tasks) {
			// TODO Auto-generated constructor stub
			this.threadPoolTaskData = tasks;
		}
		
		public Object getTask() {
			return this.threadPoolTaskData;
		}

		public void run() {
			// TODO Auto-generated method stub
			// 处理一个打印语句的任务
			System.out.println(Thread.currentThread().getName());
			System.out.println("start.." + threadPoolTaskData);
			try {
				Thread.sleep(consumeTaskSleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			threadPoolTaskData = null;
		}
	}

}
