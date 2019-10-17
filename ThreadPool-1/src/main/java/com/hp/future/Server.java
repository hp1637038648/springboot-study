package com.hp.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 动手实现简易Future模式——服务端
 * @author hp
 */
public class Server {

	public DataFuture<String> getData() {

		final DataFuture<String> data = new DataFuture<String>();

		ExecutorService pool = Executors.newSingleThreadExecutor();

		pool.execute(new Runnable() {
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				data.setRealData("最终数据");
			}
		});
		pool.shutdown();
		return data;
	}

}
