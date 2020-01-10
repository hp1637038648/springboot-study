package com.hp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity  //通过该注解开启Spring Security的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		       .antMatchers("/index","/home").permitAll()
		       .anyRequest().authenticated()
		       .and()
		    .formLogin()
		       .loginPage("/login")
		       .permitAll()
		       .and()
		    .logout()
		       .permitAll(); 
	}
	
	/*
	 * configureGlobal(AuthenticationManagerBuilder auth)方法，
	 * 在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER。
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		    .inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
		       .withUser("user").password("password").roles("USER");
	}
}
