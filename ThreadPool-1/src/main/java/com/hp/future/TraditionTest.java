package com.hp.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/*
 * 使用传统模式
 */
public class TraditionTest {
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		List<Integer> retList = new ArrayList<Integer>();
		// 计算1000次1至1亿的和
		for (int i = 0; i < 1000; i++) {
			retList.add(Calc.cal(100000000));
		}
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
		
		for (int i = 0; i < 1000; i++) {
			Integer result = retList.get(i);
			System.out.println("第" + i + "个结果：" + result);
		}
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
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
