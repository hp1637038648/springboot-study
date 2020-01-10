package com.hp.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.hp.entity.RolePermisson;
import com.hp.mapper.PermissionMapper;

/*
 * FilterInvocationSecurityMetadataSource接口的作用是用来储存请求与权限的对应关系。
 */
@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource{
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	/**
	 * @Description 每一个资源所需要的角色（权限）  Collection<ConfigAttribute>,决策器会用到
	 */
	 private static HashMap<String, Collection<ConfigAttribute>> map = null;

	 /**
	  * @Description 当接收到一个http请求时, filterSecurityInterceptor会调用的方法. 
	  * @Description 参数object是一个包含url信息的HttpServletRequest实例. 这个方法要返回请求该url所需要的所有权限集合。
	  */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        for (Iterator<String> it = map.keySet().iterator();it.hasNext();) {
			String url = it.next();
			if (new AntPathRequestMatcher(url).matches(request)) {
				return map.get(url);
			}
		}
		return null;
	}

	/*
	 * Spring容器启动时自动调用, 一般把所有请求与权限的对应关系也要在这个方法里初始化, 保存在一个属性变量里。
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
        loadResourceDefine();
		return null;
	}

	/**
	 * @Description 指示该类是否能够为指定的方法调用或Web请求提供ConfigAttributes
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	/**
	 * @Description 初始化所有资源对应的角色
	 */
	public void loadResourceDefine() {
		map = new HashMap<>(16);
		// 权限资源 和 角色对应的表 也就是 角色权限中间表, 获取资源对应的角色信息
		List<RolePermisson> rolePermissons = permissionMapper.getRolePermissons();
		
		//某个资源可以被哪些角色访问
		for (RolePermisson rolePermisson : rolePermissons) {
			
			String url = rolePermisson.getUrl();
			String roleName = rolePermisson.getRoleName();
			ConfigAttribute role = new SecurityConfig(roleName);
			
			if (map.containsKey(url)) {
				map.get(url).add(role);
			} else {
				List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
				list.add(role);
				map.put(url, list);
			}
		}
	}

}
