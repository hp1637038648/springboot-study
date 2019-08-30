package com.hp.service;

import java.util.List;
import java.util.Map;

import com.hp.entity.Student;

public interface UserService {

	/**
	 * 获取用户总量
	 * @return
	 */
	String getAllUsers();
	
	/**
	 * 获取全部学生
	 * @return
	 */
	List<Map<String, Object>> findAll();
	
	/**
	 * 根据ID获取学生
	 * @param
	 * @return
	 */
	Student getById(int id);
	
	/**
	 * 增加学生
	 * @param student
	 * @return
	 * 
	 */
	int addstu(Student student);
	
	/**
	 * 根据ID删除学生
	 * @param id
	 * @return
	 */
	int deleteStu(int id);
	
	/**
	 * 修改学生信息
	 * @param student
	 * @return
	 */
	int updateStu(Student student);
	
	/**
	 * 判断是否存在该学生
	 * @param id
	 * @return
	 */
	int isHasStu(int id);
}
