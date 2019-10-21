package com.hp.pcthread;

import java.io.PipedOutputStream;

/*
 * 写数据的类，把数据写入管道输出流
 */
public class WriteData {

	public void writeMethod(PipedOutputStream out) {
		try {
			System.out.println("write：");
			for (int i = 0; i < 100; i++) {
				String outData = "" + (i + 1);
				out.write(outData.getBytes());
				System.out.print(outData);
			}
			System.out.println();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
