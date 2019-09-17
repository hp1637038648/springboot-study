package com.hp;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * 第二数据源的jpa配置
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "entityManagerFactorySecondary", // 实体管理工厂引用名称，对应到@Bean注解对应的方法
		transactionManagerRef = "transactionManagerSecondary",   // 事务管理工厂引用名称，对应到@Bean注解对应的方法
		basePackages = {"com.hp.domain.s"}) // 设置Repository所在位置
public class SecondaryConfig {

	// 注入第二数据源
	@Autowired
	@Qualifier("secondaryDataSource")
	private DataSource secondaryDataSource;
	
	// 注入jpa配置实体
	@Autowired
	private JpaProperties jpaProperties;
	
	// 获取jpa的配置信息
	private Map<String, Object> getVendorProperties(){
		HibernateSettings hibernateSettings = new HibernateSettings();
		return jpaProperties.getHibernateProperties(hibernateSettings);
	}
	
	// 配置EntityManager工厂实体
	@Bean(name = "entityManagerFactorySecondary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(secondaryDataSource)
				.properties(getVendorProperties())
				.packages("com.hp.domain.s") //设置实体类所在位置
				.persistenceUnit("secondaryPersistenceUnit")
				.build();
	}
	
	// 配置EntityManager实体
	@Bean(name = "entityManagerSecondary")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactorySecondary(builder).getObject().createEntityManager();
	}
	
	// 配置事务支持
	@Bean(name = "transactionManagerSecondary")
	public PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
	}
}
