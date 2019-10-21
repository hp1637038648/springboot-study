package com.hp.pcthread;

import java.io.PipedInputStream;

/*
 * 读数据的类，从管道输入流读数据
 */
public class ReadData {
	public void readMethod(PipedInputStream input) {
		try {
			System.out.println("read：");
			byte[] byteArray = new byte[20];
			int readLength = input.read(byteArray);
			while (readLength != -1) {
				String newData = new String(byteArray,0,readLength);
				System.out.println(newData);
				readLength = input.read(byteArray);
			}
			System.out.println();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
