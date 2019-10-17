package com.hp;

public class ThreadTest {
	
	public static void main(String[] args) {
		ThreadTest t = new ThreadTest();
		Thread t1 = t.new Thread1("01");
        Thread1 t2 = t.new Thread1("02");  
        t1.start();  
        t2.start();  
	}

	class Thread1 extends Thread {
		private int ticket = 10;
		
		public Thread1(String name) {
			super(name);
		}
		
		public void run() {
			while (ticket > 0) {
				System.out.println("窗口：" + Thread.currentThread().getName()
						+ ",卖了一张票，剩余：" + (ticket--));
			}
		}
	}
}
