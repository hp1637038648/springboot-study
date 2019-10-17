package com.hp.testTandR;

public class Test1 {

	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		new Thread(t1,"线程1").start();
		new Thread(t1,"线程2").start();
	}
	
	public static class MyThread extends Thread{
		private int total = 10;
		
		public void run() {
			for (int i = 0; i < 10; i++) {
				synchronized (this) {
					if (total > 0) {
						try {
							Thread.sleep(100);
							System.out.println(Thread.currentThread().getName()
									+ "卖票---->" + (this.total--));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
