package com.hp.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hp.exception.entity.ErrorInfo;

/*
 *  创建全局异常处理类
 */
@ControllerAdvice // 通过使用该注解，定义统一的异常处理类，而不是在每个Controller中逐个定义
public class GlobalExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "error";
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req,Exception e) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURI());
		mav.addObject("date", new Date());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
	
	/*
	 * 为MyException异常创建对应的处理
	 */
	@ExceptionHandler(value = MyExcption.class)
	@ResponseBody
	public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyExcption e) throws Exception{
		ErrorInfo<String> r = new ErrorInfo<String>();
		r.setCode(ErrorInfo.ERROR);
		r.setMessage(e.getMessage());
		r.setData("Some Data");
		r.setUrl(req.getRequestURL().toString());
		return r;
	}
	
}
