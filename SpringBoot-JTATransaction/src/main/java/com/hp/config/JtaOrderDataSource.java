package com.hp.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
@DependsOn("transactionManager")
@EnableConfigurationProperties(DBOrderConfig.class)
@EnableJpaRepositories(basePackages = {"com.hp.dao.order"}, // 设置Repository所在位置(用于扫描持久层)
                       entityManagerFactoryRef = "orderEntityManager",  // 实体管理工厂引用名称，对应到@Bean注解对应的方法
                       transactionManagerRef = "transactionManager")   // 事务管理工厂引用名称，对应到@Bean注解对应的方法
public class JtaOrderDataSource {
	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;
	
	@Autowired
	private DBOrderConfig config;
	
	/*
	 * 配置数据源
	 */
	@Bean(name = "orderDataSource", initMethod = "init", destroyMethod = "close")
	public DataSource orderDataSource() throws Throwable {
		// 新建数据源，并将数据源配置信息装配（给XADataSource设置 DataSource属性）
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setUrl(config.getUrl());
		mysqlXADataSource.setUser(config.getUsername());
		mysqlXADataSource.setPassword(config.getPassword());
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		// 将事务信息交给Atomikos进行统一管理（创建 Atomiko， 并将 mysql的XA交给JTA管理）
		AtomikosDataSourceBean xDataSource = new AtomikosDataSourceBean();
		xDataSource.setXaDataSource(mysqlXADataSource);
		xDataSource.setUniqueResourceName("datasource1");
		// xaDataSource.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
		
		// 设置数据源其他参数信息
		xDataSource.setMinPoolSize(config.getMinPoolSize());
		xDataSource.setMaxPoolSize(config.getMaxPoolSize());
		xDataSource.setMaxLifetime(config.getMaxLifetime());
		xDataSource.setBorrowConnectionTimeout(config.getBorrowConnectionTimeout());
		xDataSource.setLoginTimeout(config.getLoginTimeout());
		xDataSource.setMaintenanceInterval(config.getMaintenanceInterval());
		xDataSource.setMaxIdleTime(config.getMaxIdleTime());
		xDataSource.setTestQuery(config.getTestQuery());
		return xDataSource;
	}
	
	@Bean("orderEntityManager")
	@DependsOn("transactionManager")
	public LocalContainerEntityManagerFactoryBean orderEntityManager() throws Throwable {
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(orderDataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.hp.entity.order");  // 设置实体类所在位置
		entityManager.setPersistenceUnitName("orderPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}
}
