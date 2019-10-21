package com.hp.pcthread;

import com.hp.storage.impl.MyService;

/*
 * 对应生产者的线程类
 */
public class MyThreadA extends Thread{

	private MyService myService;
	
	public MyThreadA(MyService myService,String name) {
		super(name);
		this.myService = myService;
	}
	
	public void run() {
		for (int i = 0; i < 5; i++) {
			myService.set();
		}
	}
}
