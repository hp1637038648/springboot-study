package com.hp;

import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * Spring boot 2.0.1及以上引用的security 依赖是 spring security 5.X版本，此版本需要提供一个PasswordEncorder的实例，否则后台汇报错误：
 * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
   * 并且页面毫无响应。
 */
public class MyPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(rawPassword.toString());
	}

}
