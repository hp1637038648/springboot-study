package com.hp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hp.entity.RolePermisson;

@Mapper
public interface PermissionMapper {

	/*
	 * 获取各权限所对应的资源列表 
	 */
	@Select("select A.name as roleName,C.url from role as A left join role_permission B on A.id=B.role_id left join permission as C on B.permission_id=C.id")
	List<RolePermisson> getRolePermissons();
	
}
