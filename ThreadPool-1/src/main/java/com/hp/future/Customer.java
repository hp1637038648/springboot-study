package com.hp.future;

/**
 * 动手实现简易Future模式——客户端
 * @author hp
 */
public class Customer {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Server server = new Server();
		DataFuture<String> dataFuture = server.getData();
		
		try {
			System.out.println("正在干其他事情！！！");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("结果数据：" + dataFuture.getRealDate());
		System.out.println("耗时：" + (end - start));
	}
}
