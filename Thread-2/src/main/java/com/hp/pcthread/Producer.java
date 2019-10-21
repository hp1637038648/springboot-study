package com.hp.pcthread;

import com.hp.storage.AbstractStorage;

/*
 *  生产者
 */
public class Producer extends Thread {
	// 每次生产的数量
	private int num;

	// 所属的仓库
	public AbstractStorage abstractStorage;

	public Producer(AbstractStorage abstractStorage) {
		this.abstractStorage = abstractStorage;
	}

	public void setNum(int num) {
		this.num = num;
	}

	// 调用仓库的Storage的生产函数
	public void produce(int num) {
		abstractStorage.produce(num);
	}

	// 线程的run函数
	public void run() {
		produce(num);
	}
}
