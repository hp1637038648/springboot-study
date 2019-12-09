package com.hp.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
@DependsOn("transactionManager")  // @DependsOn控制bean初始化顺序,先加载transactionManager的bean
@EnableConfigurationProperties(DBCustomerConfig.class)
@EnableJpaRepositories(basePackages = {"com.hp.dao.customer"}, // 设置Repository所在位置(用于扫描持久层)
                       entityManagerFactoryRef = "customerEntityManager",  // 实体管理工厂引用名称，对应到@Bean注解对应的方法
                       transactionManagerRef = "transactionManager")   // 事务管理工厂引用名称，对应到@Bean注解对应的方法
public class JtaCustomerDataSource {

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Autowired
	public DBCustomerConfig dbCustomerConfig;

	/*
	 * 配置数据源
	 */
	@Bean(name = "customerDataSource", initMethod = "init", destroyMethod = "close")
	public DataSource customerDataSource() throws Throwable {
		// 新建数据源，并将数据源配置信息装配（给XADataSource设置 DataSource属性）
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setUrl(dbCustomerConfig.getUrl());
		mysqlXADataSource.setUser(dbCustomerConfig.getUsername());
		mysqlXADataSource.setPassword(dbCustomerConfig.getPassword());
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		// 将事务信息交给Atomikos进行统一管理（创建 Atomiko， 并将 mysql的XA交给JTA管理）
		AtomikosDataSourceBean xDataSource = new AtomikosDataSourceBean();
		xDataSource.setXaDataSource(mysqlXADataSource);
		xDataSource.setUniqueResourceName("datasource2");
		// xaDataSource.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
		
		// 设置数据源其他参数信息
		xDataSource.setMinPoolSize(dbCustomerConfig.getMinPoolSize());
		xDataSource.setMaxPoolSize(dbCustomerConfig.getMaxPoolSize());
		xDataSource.setMaxLifetime(dbCustomerConfig.getMaxLifetime());
		xDataSource.setBorrowConnectionTimeout(dbCustomerConfig.getBorrowConnectionTimeout());
		xDataSource.setLoginTimeout(dbCustomerConfig.getLoginTimeout());
		xDataSource.setMaintenanceInterval(dbCustomerConfig.getMaintenanceInterval());
		xDataSource.setMaxIdleTime(dbCustomerConfig.getMaxIdleTime());
		xDataSource.setTestQuery(dbCustomerConfig.getTestQuery());
		return xDataSource;
	}
	
	@Bean(name = "customerEntityManager")
	@DependsOn("transactionManager")
	public LocalContainerEntityManagerFactoryBean customerEntityManager() throws Throwable {
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType","JTA");
		
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(customerDataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.hp.entity.customer"); // 设置实体类所在位置
		entityManager.setPersistenceUnitName("customerPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
