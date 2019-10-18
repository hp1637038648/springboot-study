package com.hp;

public class TestMethod implements Runnable {
	
	private static int number;

	TestMethod() {
		number = 0;
	}

	public static synchronized void m1() {
		for (int i = 0; i < 3; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + ":" + (number++));
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("异常");
			}
		}
	}

	public synchronized void m2() {
		for (int i = 0; i < 3; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + ":" + (number++));
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("异常");
			}
		}
	}

	public void run() {
		String name = Thread.currentThread().getName();
		if (name.equalsIgnoreCase("thread1")) {
			m1();
		} else {
			//m1();
			m2(); //让thread2访问非同步方法m2()
		}
	}

	public static void main(String[] args) {
		TestMethod testMethod = new TestMethod();
		Thread thread1 = new Thread(testMethod, "thread1");
		Thread thread2 = new Thread(testMethod, "thread2");
		thread1.start();
		thread2.start();
	}
}
