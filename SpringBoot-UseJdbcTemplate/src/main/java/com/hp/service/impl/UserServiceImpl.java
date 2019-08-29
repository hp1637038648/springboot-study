package com.hp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hp.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void create(String name, Integer age) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("insert into USER(NAME,AGE) values(?,?)",name,age);
		
	}

	@Override
	public void deleteByName(String name) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("delete from USER where NAME=?",name);
	}

	@Override
	public Integer getAllUsers() {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("select count(1) from USER", Integer.class);
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub
		jdbcTemplate.update("delete from USER");
	}

}
