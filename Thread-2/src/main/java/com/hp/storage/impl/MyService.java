package com.hp.storage.impl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ReentrantLock类加锁的线程的Condition类的
 * await()/signal()/signalAll()
 */
public class MyService {

	// 拿到可重入锁，相当于synchronized的作用
	private ReentrantLock lock = new ReentrantLock();
	// 调用await和signal方法的对象，相当于Object对象（任意对象）的的wait和notify方法
	private Condition condition = lock.newCondition();
	private boolean hasValue = false;

	/*
	 * 生产者
	 */
	public void set() {
		try {
			lock.lock(); // 获得锁
			while (hasValue == true) {
				// 没被消费则阻塞该生产线程，当然也释放了锁，进入等锁的队列
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "：打印★");
			hasValue = true;
			condition.signalAll();
			lock.unlock();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/*
	 * 消费者
	 */
	public void get() {
		try {
			lock.lock();
			while (hasValue == false) {
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "：打印☆");
			hasValue = false;
			condition.signalAll();
			lock.unlock();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
