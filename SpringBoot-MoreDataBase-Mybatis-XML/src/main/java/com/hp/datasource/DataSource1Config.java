package com.hp.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration //@Configuration把一个类作为一个IoC容器，它的某个方法头上如果注册了@Bean，就会作为这个Spring容器中的Bean。 
@MapperScan(basePackages = "com.hp.mapper.test1", sqlSessionTemplateRef = "test1SqlSessionTemplate")
public class DataSource1Config {
	
	@Bean(name = "test1DataSource")  
	/*
	 * 将配置文件application.properties中配置的每一个属性值映射到当前类的属性中；
	 * @ConfigurationProperties：告诉SpringBoot将本类中所有属性和配置文件中相关的配置进行绑定；
	 * prefix="person"：指出将配置文件中person下的所有属性进行一一映射；
	 * 注意：只有当前这个类是容器中的组件时，才可以用容器提供的@ConfigurationProperties功能；
	 */
	@ConfigurationProperties(prefix = "spring.datasource.test1")
	@Primary  // 自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "test1SqlSessionFactory")
	@Primary
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
		return bean.getObject();
	}
	
	@Primary
	@Bean(name = "test1TransactionManager")
	public DataSourceTransactionManager tesTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean(name = "test1SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test1SqlSessionFactory") SqlSessionFactory sessionFactory) throws Exception {
		return new SqlSessionTemplate(sessionFactory);
	}
}
