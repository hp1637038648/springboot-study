package com.hp.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

/**
 * @Description 每种受支持的安全对象类型（方法调用或Web请求）都有自己的拦截器类，它是AbstractSecurityInterceptor的子类，
 * AbstractSecurityInterceptor 是一个实现了对受保护对象的访问进行拦截的抽象类
 * @Description 自定义MyFilterSecurityInterceptor就是想使用之前自定义的 AccessDecisionManager 和 securityMetadataSource。
 */
@Component
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

	@Autowired
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	
	@Autowired
	public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
		super.setAccessDecisionManager(myAccessDecisionManager);
	}
	
	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response,chain);
		invoke(fi);
	}
	
	public void invoke(FilterInvocation fi) throws IOException, ServletException{
		//beforeInvocation()方法实现了对访问受保护对象（方法调用或Web请求）的权限校验，
		//内部用到了AccessDecisionManager和AuthenticationManager
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			// 执行下一个拦截器
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			//afterInvocation()方法实现了对返回结果的处理，
			//在注入了AfterInvocationManager的情况下默认会调用AccessDecisionManagerd的decide()方法。
			super.afterInvocation(token, null);
		} 
	}

}
