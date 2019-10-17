package com.hp;

public class RunnableTest {

	public static void main(String[] args) {
		RunnableTest r = new RunnableTest();
		Runnable r1 = r.new Runnable1();
		new Thread(r1,"01").start();
		new Thread(r1,"02").start();
	}
	
	class Runnable1 implements Runnable{
        private int ticket = 10;
		public void run() {
			while (ticket > 0) {
				System.out.println("窗口：" + Thread.currentThread().getName()
						+ ",卖了一张票,剩余：" + (ticket--));
			}
			
		}
		
	}
}
