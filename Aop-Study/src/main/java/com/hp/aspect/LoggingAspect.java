package com.hp.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/*
 * 日志切面类（基于xml的切面实现方式）
 */
public class LoggingAspect {

	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("The method " + methodName + " bengin with:" + args);
	}
	
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " end");
	}
	
	public void AfterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " end with: " + result);
	}
	
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " occurs exception:" + e);
	}
	
	public Object around(ProceedingJoinPoint proceedingJoinPoint) {
		Object result = null;
		try {
			// 前置通知
			System.out.println("start!");
			// 执行目标方法
			result = proceedingJoinPoint.proceed();
			// 返回通知
			System.out.println("result: " + result);
		} catch (Throwable throwable) {
			// 异常通知
			System.out.println("Exception: ");
			throwable.printStackTrace();
		}
		// 后置通知
		System.out.println("end!");
		return result;
	}
}
