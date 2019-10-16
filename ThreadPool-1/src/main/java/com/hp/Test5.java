package com.hp;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 一个用队列处理线程池的例子
 */
public class Test5 {

	private static int queueDeep = 4;

	public static void main(String[] args) {
		Test5 test = new Test5();
		test.createThreadPool();
	}

	public void createThreadPool() {
		/*
		 * 创建线程池，最小线程数为2，最大线程数为4 线程池维护线程的空闲时间为3秒 缓冲队列为4，线程执行时间为3秒
		 */
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(queueDeep), new ThreadPoolExecutor.DiscardOldestPolicy()); // 这里采取的是抛弃旧的任务
		// 向线程中添加10个任务
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (getQueueSize(tpe.getQueue()) >= queueDeep) {
				System.out.println("队列已满，请等3秒在添加任务！");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			TaskThreadPool ttp = new TaskThreadPool(i);
			System.out.println("put i：" + i);
			tpe.execute(ttp);
		}
		tpe.shutdown();
	}

	public static class TaskThreadPool implements Runnable {
		private int index;
		public TaskThreadPool(int index) {
			this.index = index;
		}
		public void run() {
			System.out.println(Thread.currentThread() + "index：" + index);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized int getQueueSize(Queue queue) {
		return queue.size();
	}
}
