package com.hp.storage;

/*
 *  抽象仓库类
 */
public interface AbstractStorage {

	/*
	 * 消费者方法
	 */
	void consume(int num);
	
	/*
	 * 生产者方法
	 */
	void produce(int num);
}
