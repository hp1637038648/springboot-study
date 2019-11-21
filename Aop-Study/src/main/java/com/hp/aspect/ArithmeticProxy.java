package com.hp.aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import com.hp.service.Arithmetic;
import com.hp.service.impl.Calculator;

/**
 * @Description 动态代理方式实现AOP（切面类）
 * @author hp
 */
public class ArithmeticProxy {

	private Arithmetic arithmetic;
	
	public ArithmeticProxy(Arithmetic arithmetic) {this.arithmetic = arithmetic;}
	
	public Arithmetic getLoggingProxy() {
		
		Arithmetic proxy = null;
		
		// 代理对象由哪一个类加载器负责加载
		ClassLoader classLoader = arithmetic.getClass().getClassLoader();
		
		// 代理对象的类型，即其中有哪些方法（表示代理类所实现的接口列表）
		Class[] interfaces =  arithmetic.getClass().getInterfaces();
		
		// 当调用代理对象其中的方法时，执行下面的方法
		InvocationHandler h = (proxy1,method,args) -> {
			String methodName = method.getName();
			Object result = null;
			try {
				// 前置通知
				System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
				result = method.invoke(arithmetic, args);
				// 返回通知，可以在方法正常结束的时候访问到方法的返回值
				System.out.println(result);
			} catch (Exception e) {
				// 异常通知
				System.out.println(e);
				e.printStackTrace();
			}
			// 后置通知，因为方法可能会出异常，所以访问不到方法的返回值
			System.out.println("The method " + methodName + " end with " + result);
			return result;
		};
		proxy = (Arithmetic)Proxy.newProxyInstance(classLoader, interfaces, h);
		return proxy;
	}
	
	public static void main(String[] args) {
		Arithmetic arithmetic = new Calculator();
		
		//动态代理的方式
		Arithmetic proxy = new ArithmeticProxy(arithmetic).getLoggingProxy();
		proxy.div(10, 10);
	}

}
