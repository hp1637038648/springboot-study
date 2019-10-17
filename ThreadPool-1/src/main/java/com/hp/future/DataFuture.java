package com.hp.future;

/**
 * 实现简易future模式的future类
 * @author hp
 * @param <T>
 */
public class DataFuture<T> {

	private T realData;
	
	private boolean isOK = false;
	
	public synchronized T getRealDate() {
		while (!isOK) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realData;
	}
	
	public synchronized void setRealData(T data) {
		this.isOK = true;
		this.realData = data;
		notifyAll();
	}
}
