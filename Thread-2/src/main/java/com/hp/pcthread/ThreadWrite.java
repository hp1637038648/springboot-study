package com.hp.pcthread;

import java.io.PipedOutputStream;

/*
 * 写数据的线程类
 */
public class ThreadWrite extends Thread{
	
	private WriteData write;
	private PipedOutputStream out;
	
	public ThreadWrite(WriteData write,PipedOutputStream out) {
		super();
		this.write = write;
		this.out = out;
	}
	
	public void run() {
		write.writeMethod(out);
	}

}
