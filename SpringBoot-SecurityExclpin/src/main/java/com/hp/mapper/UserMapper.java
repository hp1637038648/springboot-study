package com.hp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hp.entity.User;

@Mapper
public interface UserMapper {

	/*
	   * 获取用户的详细信息
	 */
	@Select("select id,username,password from user where username = #{username}")
	User loadUserByUserName(@Param("username") String username);
}
