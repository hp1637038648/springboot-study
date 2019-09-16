package com.hp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Autowired是根据类型进行自动装配的。
 * 如果当Spring上下文中存在不止一个UserDao类型的bean时，就会抛出BeanCreationException异常;
 * 如果Spring上下文中不存在UserDao类型的bean，也会抛出BeanCreationException异常。
 * 我们可以使用@Qualifier配合@Autowired来解决这些问题。
 */
//@Configuration把一个类作为一个IoC容器，它的某个方法头上如果注册了@Bean，就会作为这个Spring容器中的Bean。 
@Configuration
public class DataSourceConfig {

	// @Qualifier("bean的名字") 按名称装配Bean
	// @Autowired 默认是根据类型Type来自动注入的
	// @Qualifier("bean的名字") 一般作为@Autowired()的修饰用
	// @Resource 默认按名称装配，当找不到与名称匹配的bean才会按类型装配。
	/*
	 * @Bean 的用法:https://www.cnblogs.com/feiyu127/p/7700090.html
	 */
	@Bean(name = "primaryDataSource")
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "secondaryDataSource")
	@Qualifier("secondaryDataSource")
	// @Primary 自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
	@Primary // 讲解网址 https://blog.csdn.net/qq_16055765/article/details/78833260
	/*
	 * 将配置文件application.properties中配置的每一个属性值映射到当前类的属性中；
	 * 
	 * @ConfigurationProperties：告诉springboot将本类中所有属性和配置文件中相关的配置进行绑定；
	 * prefix="person"：指出将配置文件中person下的所有属性进行一一映射；
	 *
	 * 注意：只有当前这个类是容器中的组件时，才可以用容器提供的@ConfigurationProperties功能；
	 *
	 */
	@ConfigurationProperties(prefix = "spring.datasource.secondary")
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "primaryJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = "secondaryJdbcTemplate")
	public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
