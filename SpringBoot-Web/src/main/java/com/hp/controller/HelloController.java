package com.hp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hp.exception.MyExcption;

@Controller
public class HelloController {
	@RequestMapping(value="/hello")
	public String index(ModelMap map) {
		// 加入一个属性，用来在模板中读取
		map.addAttribute("host", "http://blog.didispace.com");
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";
	}
	
	@RequestMapping("/hello1")
	public String hello1() throws Exception {
	    throw new NullPointerException("发生空指针错误");
	}
	
	@RequestMapping("/json")
	public String json() throws MyExcption{
		throw new MyExcption("发生特殊错误");
	}
}
