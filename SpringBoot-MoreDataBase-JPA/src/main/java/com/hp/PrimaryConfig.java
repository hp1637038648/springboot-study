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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * 创建第一数据源的配置支持类
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "entityManagerFactoryPrimary",     // 实体管理工厂引用名称，对应到@Bean注解对应的方法
		transactionManagerRef = "transactionManagerPrimary",       // 事务管理工厂引用名称，对应到@Bean注解对应的方法
		basePackages = {"com.hp.domain.p"}) // 设置Repository所在位置
public class PrimaryConfig {

	// 注入第一数据源
	@Autowired
	@Qualifier("primaryDataSource")
	private DataSource primaryDataSource;
	
	// 注入jpa配置实体
	@Autowired
	private JpaProperties JpaProperties;
	
	
	// 获取jpa配置信息
	private Map<String, Object> getVendorProperties(){
		HibernateSettings hibernateSettings = new HibernateSettings();
		return JpaProperties.getHibernateProperties(hibernateSettings);
	}
	
	// 配置EntityManager工厂实体
	@Primary
    @Bean(name = "entityManagerFactoryPrimary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(primaryDataSource)
				.properties(getVendorProperties()) 
                .packages("com.hp.domain.p") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
	}
	
	// 配置EntityManager实体
	@Primary
	@Bean(name = "entityManagerPrimary")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
	}
	
	// 配置事务
	@Primary
	@Bean(name = "transactionManagerPrimary")
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
	}
}
