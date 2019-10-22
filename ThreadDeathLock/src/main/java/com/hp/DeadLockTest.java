package com.hp;

import com.hp.lock.DeadLock;

public class DeadLockTest {
	public static void main(String[] args) {

		Thread t1 = new Thread(new DeadLock(true), "线程1");
		//Thread t2 = new Thread(new DeadLock(false),"线程2");
		// 通过改变加锁顺序（线程按照一定的顺序加锁）避免死锁
		Thread t2 = new Thread(new DeadLock(true),"线程2");
		
		t1.start();
		t2.start();
	}
}
