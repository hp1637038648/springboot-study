package com.hp.service.impl;

import org.springframework.stereotype.Service;

import com.hp.service.Arithmetic;

/*
 * 业务逻辑实现类
 */
@Service("calculator")
public class Calculator implements Arithmetic{

	@Override
	public int add(int val1, int val2) {
		// TODO Auto-generated method stub
		return val1 + val2;
	}

	@Override
	public int sub(int val1, int val2) {
		// TODO Auto-generated method stub
		return val1 - val2;
	}

	@Override
	public int mul(int val1, int val2) {
		// TODO Auto-generated method stub
		return val1 * val2;
	}

	@Override
	public int div(int val1, int val2) {
		// TODO Auto-generated method stub
		return val1 / val2;
	}

}
