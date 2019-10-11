package com.hp;

import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hp.async.AsyncCallbackTask;
import com.hp.async.AsyncTask;
import com.hp.async.Task;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAsyncCallApplicationTests {

	@Autowired
	private Task task;
	
	@Autowired
	private AsyncTask asyncTask;
	
	@Autowired
	private AsyncCallbackTask aCallbackTask;

	/*
	 * 同步调用
	 */
	@Test
	public void testSync() throws Exception {
		task.doTaskOne();
		task.doTaskTwo();
		task.doTaskThree();
	}
	
	/*
	 * 异步调用
	 */
	@Test
	public void testAsync() throws Exception {
		asyncTask.doTaskOne();
		asyncTask.doTaskTwo();
		asyncTask.doTaskThree();
	}
	
	/*
	 * 异步回调
	 */
	@Test
	public void testAsyncCallback() throws Exception {
		
		long start = System.currentTimeMillis();
		
		Future<String> task1 = aCallbackTask.doTaskOne();
		Future<String> task2 = aCallbackTask.doTaskTwo();
		Future<String> task3 = aCallbackTask.doTaskThree();

		while(true) {
			if (task1.isDone() && task2.isDone() && task3.isDone()) {
				// 三个任务都调用完成，退出循环等待
				break;
			}
			Thread.sleep(1000);
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}

}
