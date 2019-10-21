package com.hp.pcthread;

import com.hp.storage.impl.MyService;

/*
 * 对应消费者的线程类
 */
public class MyThreadB extends Thread{

	private MyService myService;
	
	public MyThreadB(MyService myService,String name) {
		super(name);
		this.myService = myService;
	}
	
	public void run() {
		for (int i = 0; i < 5; i++) {
			myService.get();
		}
	}
}
