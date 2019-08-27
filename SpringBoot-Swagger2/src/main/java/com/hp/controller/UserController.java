package com.hp.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hp.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/*
 * @author hp
 * @date 2019/8/22
 */
@Api(value = "用户相关接口", description = "提供用户相关的 Rest API")
@RestController
@RequestMapping(value = "/users")
public class UserController {

	// 创建线程安全的Map
	public static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());
	
	@ApiOperation(value="获取用户列表", notes="获取所有用户信息")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> getUserList() {
		// 处理"/users/"的GET请求，用来获取用户列表
		// 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
		List<User> r = new ArrayList<User>(users.values());
		return r;
	}
    
	@ApiOperation(value = "创建用户",notes="根据User对象创建用户")
	@ApiImplicitParam(name="user",value = "用户详细实体user",required = true, dataType = "User")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String postUser(@ModelAttribute User user) {
		// 处理"/users/"的POST请求，用来创建User
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		users.put(user.getId(), user);
		return "success";
	}
    
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "Long",paramType="path")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long id) {
		// 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
		// url中的id可通过@PathVariable绑定到函数的参数中
		return users.get(id);
	}
    
	@ApiOperation(value = "更新用户详细信息",notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id",value = "用户",required = true, dataType = "Long",paramType = "path"),
		@ApiImplicitParam(name = "user",value = "用户详细实体user",required = true, dataType = "User")
	})
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putUser(@PathVariable Long id, @ModelAttribute User user) {
		// 处理"/users/{id}"的PUT请求，用来更新User信息
		User u = users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(id, u);
		return "success";
	}

	@ApiOperation(value = "删除用户",notes = "根据url的ID来删除指定对象")
	@ApiImplicitParam(name="id",value = "用户ID",required = true,dataType = "Long",paramType = "path")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		// 处理"/users/{id}"的DELETE请求，用来删除User
		users.remove(id);
		return "success";
	}

}
