package com.hp.pcthread;

import java.io.PipedInputStream;

/*
 * 读数据的线程类
 */
public class ThreadRead extends Thread{
	
	private ReadData read;
	private PipedInputStream input;
	
    public ThreadRead(ReadData read, PipedInputStream input) {
        super();
        this.read = read;
        this.input = input;
    }

    public void run() {
        read.readMethod(input);
    }

}
