package com.hp.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<Integer>> fuList = new ArrayList<Future<Integer>>();
		// 计算1000次1至1亿的和
		for (int i = 0; i < 1000; i++) {
			// 调度执行
			fuList.add(executorService.submit(new Calc()));
		}
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
		
		for (int i = 0; i < fuList.size(); i++) {
			try {
				Integer result = fuList.get(i).get();
				System.out.println("第" + i + "个结果：" + result);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
		executorService.shutdown();
	}

	public static class Calc implements Callable<Integer> {

		public Integer call() throws Exception {
			return cal(100000000);
		}
		
		public static int cal(int num) {
			int sum = 0;
			for (int i = 0; i < num; i++) {
				sum += i;
			}
			return sum;
		}
	}
}
