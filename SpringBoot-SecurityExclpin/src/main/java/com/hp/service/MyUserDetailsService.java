package com.hp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hp.entity.Role;
import com.hp.entity.User;
import com.hp.mapper.RoleMapper;
import com.hp.mapper.UserMapper;

/**
 * @Description 获取用户的所有信息以及权限列表
 */
@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//查询数据库获取用户信息
		User user = userMapper.loadUserByUserName(username);
		if (null != user) {
			//获取用户权限列表
			List<Role> roles = roleMapper.getRolesByUserId(user.getId());
			user.setAuthorities(roles);
		}
		return user;
	}

}
