package com.hp.entity;

import java.io.Serializable;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * UserDetails为用户详细信息接口
 * @Description 该接口是实现Spring Security 认证信息的核心接口
 */
public class User implements UserDetails,Serializable{
	
    private Long id;
    private String username;
    private String password;
	private List<Role> authorities;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}

	/**
	 * getAuthorities() 方法返回的是该用户设置的权限信息，
	 * @Description 本实例中，从数据库取出用户的所有角色信息，权限信息也可以是用户的其他信息，不一定是角色信息
	 */
	@Override
    public List<Role> getAuthorities() {
        return authorities;
    }

	/*
	 * getPassword方法为 UserDetails接口的方法
	 */
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	/*
	 * getUsername方法为 UserDetails接口的方法，这个方法返回 username，也可以是其他的用户信息，例如手机号、邮箱等
	 */
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
    /**
     * @Description 用户账号是否过期
     */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

    /**
     * @Description 用户账号是否被锁定
     */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

    /**
     * @Description 用户密码是否过期
     */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

    /**
     * @Description 用户是否可用
     */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
