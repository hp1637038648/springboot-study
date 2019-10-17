package com.hp.testTandR;

public class Test2 {
	
	public static void main(String[] args) {
		MyRunable r1 = new MyRunable();
		new Thread(r1,"线程1").start();
		new Thread(r1,"线程2").start();
	}
	
	public static class MyRunable implements Runnable{
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
