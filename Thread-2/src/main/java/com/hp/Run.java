package com.hp;

import com.hp.pcthread.MyThreadA;
import com.hp.pcthread.MyThreadB;
import com.hp.storage.impl.MyService;

public class Run {
	public static void main(String[] args) {
		MyService myService = new MyService();
		
		MyThreadA a = new MyThreadA(myService,"a");
		MyThreadA c = new MyThreadA(myService,"c");
		a.start();
		c.start();
		
		MyThreadB b = new MyThreadB(myService,"b");
		MyThreadB d = new MyThreadB(myService,"d");
		b.start();
		d.start();
	}
}
