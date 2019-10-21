package com.hp;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import com.hp.pcthread.ReadData;
import com.hp.pcthread.ThreadRead;
import com.hp.pcthread.ThreadWrite;
import com.hp.pcthread.WriteData;

public class Run1 {

	public static void main(String[] args) {
		try {
			WriteData writeData = new WriteData();
			ReadData readData = new ReadData();

			PipedInputStream inputStream = new PipedInputStream();
			PipedOutputStream outputStream = new PipedOutputStream();

			// 将两个Stream之间产生通信链接，这样才能将数据进行输入输出，下面两种方式，其一即可
			// inputStream.connect(outputStream);
			outputStream.connect(inputStream);

			// 开启读线程
			ThreadRead threadRead = new ThreadRead(readData, inputStream);
			threadRead.start();
			
			Thread.sleep(3000);
			
			// 开启写线程
			ThreadWrite threadWrite = new ThreadWrite(writeData, outputStream);
			threadWrite.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
