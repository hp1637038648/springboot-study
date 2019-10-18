package com.hp;

/**
 * @Description 修饰对象
 * @author hp
 */
public class TestSy1 implements Runnable {

	private int number;

	public static void main(String[] args) {
//		TestSy1 testSy1 = new TestSy1();
//		Thread thread1 = new Thread(testSy1, "thread1");
//		Thread thread2 = new Thread(testSy1, "thread2");
//		thread1.start();
//		thread2.start();
		// 验证其他线程是否可以访问其他对象的同步方法
		TestSy1 testSy1 = new TestSy1();
		TestSy1 testSy2 = new TestSy1();
		Thread thread1 = new Thread(testSy1,"thread1");
		Thread thread2 = new Thread(testSy2,"thread2");
		thread1.start();
		thread2.start();
	}

	public void run() {
		String name = Thread.currentThread().getName();
		if (name.equalsIgnoreCase("thread1")) {
			add();
		} else {
			//线程thread1 访问对象testSy1的带有同步代码块的add()时,其他线程不能访问add()方法
			add();
			//其他线程可以访问该对象的未上锁的show()方法
			//show();  
		}
	}

	public TestSy1() {
		number = 0;
	}

	public void add() {
		//修饰this，当前对象，这里的this指的是执行这段代码的对象(即testSy1对象)，synchronized得到的锁就是this这个对象的锁。
		synchronized (this) {
			for (int i = 0; i < 4; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":thread:" + (number++));
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("异常");
					e.printStackTrace();
				}
			}
		}
		System.out.println("add");
	}

	public void show() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + " 非同步:" + number);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("show");
	}

}
