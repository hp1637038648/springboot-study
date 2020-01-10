package com.hp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hp.entity.Role;

@Mapper
public interface RoleMapper {
	
	/*
	   *  获取用户的权限列表
	 */
	@Select("select a.id,a.name from role a left join user_role b on a.id=b.role_id where b.user_id=${userId}")
	List<Role> getRolesByUserId(@Param("userId") Long userId);
	
}
