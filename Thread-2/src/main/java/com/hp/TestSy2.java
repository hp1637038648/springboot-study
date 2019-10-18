package com.hp;

/**
 * synchronized不可以直接修饰类，但是可以通过synchronized（类名.class）作用于类。
 * 修饰类和修饰静态方法一样，也是作用于该类的所有对象
 * 
 * @author hp
 *
 */
public class TestSy2 implements Runnable {
	private int number;

	TestSy2() {
		number = 0;
	}

	public void add() {
		synchronized (TestSy2.class) {
			for (int i = 0; i < 4; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":thread:" + (number++));
					Thread.sleep(1000);
				} catch (Exception e) {
					System.out.println("异常");
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

	public void run() {
		String name = Thread.currentThread().getName();
		if (name.equalsIgnoreCase("thread1")) {
			add();
		} else {
			//add();
			show();
		}
	}

	public static void main(String[] args) {
		TestSy2 testSy2 = new TestSy2();
		Thread thread1 = new Thread(testSy2, "thread1");
		Thread thread2 = new Thread(testSy2, "thread2");
		thread1.start();
		thread2.start();
	}
}
