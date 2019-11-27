package com.hp.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description 纯注解配置AOP
 * @author hp
 */
@Order(1) //指定切面的优先级，值越小，优先级越高
@Aspect   //将这个类声明为一个切面：1、加入到IOC容器；2、声明为切面
@Component
@EnableAspectJAutoProxy //使Aspect注解起作用：自动为匹配的类生成代理对象
public class LoggingAspect1 {

	/**
	 *  使用Pointcut来声明切入点表达式
	 */
	@Pointcut("execution(* com.hp.service..*.*(..))") //..代表任意参数
	public void declareJoinPointExpression() {};
	
	/**
	 *  申明该方法是一个前置通知：在目标方法开始之前执行
	 * @param joinPoint
	 */
	@Before("declareJoinPointExpression()")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("The method " + methodName + " begin with: " + args);
//		  // 接收到请求，记录请求内容  
//        ServletRequestAttributes attributes = 
//        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
//        HttpServletRequest request = attributes.getRequest();  
//        // 记录下请求内容  
//        System.out.println("URL : " + request.getRequestURL().toString());  
//        System.out.println("HTTP_METHOD : " + request.getMethod());  
//        System.out.println("IP : " + request.getRemoteAddr());  
//        System.out.println("CLASS_METHOD : " +
//         joinPoint.getSignature().getDeclaringTypeName() + 
//        "." + joinPoint.getSignature().getName());  
//        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));  
	}
	
	/**
	 *  后置通知：在目标方法执行后（无论是否发生异常），执行的通知
	 *  在后置通知中不能访问目标方法执行的结果
	 * @Param joinPoint
	 */
	@After(value = "declareJoinPointExpression()")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " end");
	}
	
	/**
	 *  返回通知：在方法【正常结束】之后才执行的代码，可以访问到方法的返回值
	 *  @Param joinPoint
	 *  @Param result
	 */
	@AfterReturning(value = "declareJoinPointExpression()",returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " end with：" + result);
	}
	
	/**
	 *  在目标方法出现异常时会执行的代码
	 *  可以访问到异常对象；且可以指定在出现特定异常时再执行此方法
	 *  @Param joinPoint
	 *  @Param e
	 */
	@AfterThrowing(value = "declareJoinPointExpression()",throwing = "e")
	public void afterThrowing(JoinPoint joinPoint,Exception e) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " occurs exception：" + e);
	}
	
	/**
	 *  【和proxy的动态代理一样，可以实现上述的所有功能，测试时注释掉上面的所有方法】
	 *  环绕通知需要携带proceedingJoinPoint类型的参数
	 *  proceedingJoinPoint可以决定什么时候执行目标方法
	 *  且必须有返回值，即目标方法的返回值
	 *  @Param proceedingJoinPoint
	 */
	@Around(value = "declareJoinPointExpression()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) {
		Object result = null;
		
		try {
			//前置通知
			System.out.println("start!");
			//执行目标方法
			result = proceedingJoinPoint.proceed();
			//返回通知
			System.out.println("return: " + result);
		} catch (Throwable throwable) {
			// 异常通知
			System.out.println("Exception");
			throwable.printStackTrace();
		}
		//后置通知
		System.out.println("end!");
		return result;
	}
}
