package com.hp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import com.hp.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private MyUserDetailsService userService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// 校验用户
		auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
			// 对密码进行判断匹配
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				String encode = DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
				System.out.println("密码为：" + encode);
				boolean result = encodedPassword.equals(encode);
				return result;
			}
			// 对密码进行加密
			@Override
			public String encode(CharSequence rawPassword) {
				return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
			}
		});
	}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		         .antMatchers("/","/index","/login-error","/401","/css/**","/js/**","/login").permitAll()
		         .anyRequest().authenticated()
		         .and()
	 .formLogin().loginPage("/login").failureUrl("/login-error")
	             .and()
	 .exceptionHandling().accessDeniedPage("/401");
		http.logout().logoutSuccessUrl("/");     
	}
	
}
