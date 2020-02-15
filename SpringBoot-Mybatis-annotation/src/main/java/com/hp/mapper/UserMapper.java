package com.hp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hp.enums.UserSexEnum;
import com.hp.model.User;

public interface UserMapper {

	/*
	 * @Select 是查询类的注解，所有的查询均使用这个
	 * @Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
	 */
	@Select("select * from users")
	@Results({
		@Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
		@Result(property = "nickName", column = "nick_name")
	})
	public List<User> getAll();
	
	@Select("select * from users where id=#{id}")
	@Results({
		@Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
		@Result(property = "nickName", column = "nick_name")
	})
	public User getOne(Long id);
	
	/*
	 * @Insert 插入数据库使用，直接传入实体类会自动解析属性到对应的值
	 */
	@Insert("insert into users(userName,passWord,user_sex) values (#{userName}, #{passWord}, #{userSex})")
	public void insert(User user);
	
	/*
	 * @Update 负责修改，也可以直接传入对象
	 */
	@Update("update users set userName=#{userName},nick_name=#{nickName} where id=#{id}")
	public void update(User user);
	
	/*
	 * @delete 负责删除
	 */
	@Delete("delete from users where id=#{id}")
	public void delete(Long id);
}
