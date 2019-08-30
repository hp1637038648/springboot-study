package com.hp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hp.entity.Student;
import com.hp.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(description = "提供用户相关的 Rest API")
public class UserController {
    
	@Autowired
	private UserService service;
	
	@ApiOperation(value="获取用户总数",notes = "获取所有学生用户的数量")
	@RequestMapping(value="/getAllUsers",method = RequestMethod.GET)
	public String getAllUsers() {
		return service.getAllUsers();
	}
	
    @ApiOperation(value = "获取用户列表",notes = "获取所有用户的详细信息")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Map<String, Object>> findAll(){
        List<Map<String, Object>> list = service.findAll();
        return list;
    }
    
    @ApiOperation(value = "获取用户信息",notes = "根据用户id获取用户")
    @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "Long",paramType="path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStuById(@PathVariable int id){
        Student student = service.getById(id);
        return student;
    }
    
    @ApiOperation(value = "添加用户",notes = "添加学生用户")
    @ApiImplicitParam(name="student",value = "学生详细实体student",required = true, dataType = "Student")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public int addStu(@ModelAttribute Student student){
        int res = service.addstu(student);
        return res;
    }
    
    @ApiOperation(value = "删除用户",notes = "根据用户Id删除用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int deleteStu(@PathVariable int id){
        System.out.println(id);
        int res = service.deleteStu(id);
        return res;
    }

    @ApiOperation(value = "修改用户信息",notes = "根据用户Id修改用户信息")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public int updateStu(@ModelAttribute Student student){
        System.out.println(student.getId());
        int isHas = service.isHasStu(student.getId());
        int res = 0;
        if (isHas==1){
            res = service.updateStu(student);
        }
        return res;
    }
}
