package com.hp.aspect;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Order(1) //i的值越小，优先级越高，切面越先执行（在切入点前的操作，按order的值由小到大执行；在切入点后的操作，按order的值由大到小执行）
public class WebLogAspect {

//	private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(getClass());

	ThreadLocal<Long> startTime = new ThreadLocal<>();

	@Pointcut("execution(public * com.hp.controller..*.*(..))")
	public void webLog() {
	}

	@Before(value = "webLog()")
	public void doBefore(JoinPoint joinPoint) {

		startTime.set(System.currentTimeMillis());
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		logger.info("URL: " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD: " + request.getMethod());
		logger.info("IP: " + request.getRemoteAddr());
		logger.info("CLASS_METHOD: " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS: " + Arrays.toString(joinPoint.getArgs()));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterReturning(returning = "result", pointcut = "webLog()")
	public void doAfterReturning(Object result) {
		// 处理完请求，返回内容
		logger.info("RESPONSE: " + result);
		logger.info("SPEND TIME: " + (System.currentTimeMillis() - startTime.get()));
	}
}
