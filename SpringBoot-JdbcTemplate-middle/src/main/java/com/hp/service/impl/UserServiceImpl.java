package com.hp.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.hp.entity.Student;
import com.hp.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String getAllUsers() {
		return jdbcTemplate.queryForObject("select count(1) from student", String.class);
	}

	@Override
	public List<Map<String, Object>> findAll() {
		String sql = "select * from student";
		//queryForList方法， 该方法将返回一个List，该List中的每一条 记录是一个Map对象，对应应数据库中某一行；而该Map 中的每一项对应该数据库行中的某一列值
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			String string = (String) map.get("name");
			System.out.println(string);
		}
		return list;
	}

	@Override
	public Student getById(int id) {
		String sql = "select * from student where id=?";
		List<Student> stu = jdbcTemplate.query(sql, new Object[]{id}, new StudentRowMapper());
		Student student = null;
		if (!stu.isEmpty()) {
			student = stu.get(0);
		}
		return student;
	}

    /**
     * 插入用户-防止sql注入-可以返回该条记录的主键
     * @param student
     * @return
     */
	@Override
	public int addstu(Student student) {
		String sql = "insert into student(id,name,sex,age) values(null,?,?,?)";
		//使用Spring JdbcTemplate插入数据之后，如果需要获取插入之后的ID,使用KeyHolder
		//int userId = keyHolder.getKey().longValue();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int resRow = jdbcTemplate.update(new PreparedStatementCreator() {
			//PreparedStatementCreator：通过回调获取JdbcTemplate提供的Connection，由用户使用该Conncetion创建相关的PreparedStatement
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql,new String[]{"id"});
				ps.setString(1, student.getName());
				ps.setInt(2, student.getSex());
				ps.setInt(3, student.getAge());
				return ps;
			}
		},keyHolder);
		System.out.println("操作记录数："+resRow+"主键："+keyHolder.getKey());
		return Integer.parseInt(keyHolder.getKey().toString());
	}

	@Override
	public int deleteStu(int id) {
		String sql = "delete from student where id=?";
		return jdbcTemplate.update(sql,id);
	}

	@Override
	public int updateStu(Student student) {
		String sql = "update student set name=?,sex=?,age=? where id=?";
		//PreparedStatementSetter：通过回调获取JdbcTemplate提供的PreparedStatement，由用户来对相应的预编译语句相应参数设值
		//PreparedStatementSetter对象来替换查询中的占位符。
		int res = jdbcTemplate.update(sql,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, student.getName());
				ps.setInt(2, student.getSex());
				ps.setInt(3, student.getAge());
				ps.setInt(4, student.getId());
			}	
		});
		return res;
	}

	@Override
	public int isHasStu(int id) {
		String sql = "select * from student where id=?";
		//StudentRowMapper是一个RowMapper对象，用于将每个获取的记录映射到Student对象
		List<Student> students = jdbcTemplate.query(sql, new Object[]{id}, new StudentRowMapper());
		if (students!=null && students.size()>0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	// RowMapper：用于将结果集每行数据转换为需要的类型，用户需实现方法mapRow(ResultSet rs, int rowNum)来完成将每行数据转换为相应的类型
	class StudentRowMapper implements RowMapper<Student> {
	    @Override
	    public Student mapRow(ResultSet resultSet,int i) throws SQLException{
	        Student stu = new Student();
	        stu.setId(resultSet.getInt("id"));
	        stu.setAge(resultSet.getInt("age"));
	        stu.setSex(resultSet.getInt("sex"));
	        stu.setName(resultSet.getString("name"));
	        return stu;
	    }
	}
}
