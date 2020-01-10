package com.hp.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * GrantedAuthority为授权接口
 * @Description 所有的Authentication（身份验证）实现类都保存了一个GrantedAuthority列表，其表示用户所具有的权限
 * @Description GrantedAuthority是通过AuthenticationManager设置到Authentication对象中的，
 * @Description 然后AccessDecisionManager将从Authentication中获取用户所具有的GrantedAuthority来鉴定用户是否具有访问对应资源的权限。
 */
public class Role implements GrantedAuthority{
	
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name;
	}

}
