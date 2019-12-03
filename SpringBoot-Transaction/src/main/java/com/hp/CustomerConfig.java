package com.hp;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.hp.repository.customer.CustomerDatasourceProperties;
import com.mysql.cj.jdbc.MysqlXADataSource;

/**
 * @Description customer数据源配置
 * @author hp
 * @Data 2019/12/3
 */
@Configuration
@DependsOn("transactionManager") // @DependsOn控制bean初始化顺序,先加载transactionManager的bean
@EnableConfigurationProperties(CustomerDatasourceProperties.class)
@EnableJpaRepositories(basePackages = "com.hp.repository.customer",  // 设置Repository所在位置
                       entityManagerFactoryRef = "customerEntityManager",  // 实体管理工厂引用名称，对应到@Bean注解对应的方法
                       transactionManagerRef = "transactionManager")   // 事务管理工厂引用名称，对应到@Bean注解对应的方法
public class CustomerConfig {

	// jpa配置信息
	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	// 这里注入customer dataSource信息的类
	@Autowired
	private CustomerDatasourceProperties customerDatasourceProperties;

	@Bean(name = "customerDataSource", initMethod = "init", destroyMethod = "close")
	public DataSource customerDataSource() throws Throwable {
		// 给XADataSource 设置 DataSource 属性
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setURL(customerDatasourceProperties.getUrl());
		mysqlXADataSource.setUser(customerDatasourceProperties.getUser());
		mysqlXADataSource.setPassword(customerDatasourceProperties.getPassword());
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

		// 创建 Atomiko， 并将 mysql的XA交给JTA管理
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXADataSource);
		xaDataSource.setUniqueResourceName("datasource2");
		//xaDataSource.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
		return xaDataSource;
	}

	@Bean(name = "customerEntityManager")
	@DependsOn("transactionManager")
	public LocalContainerEntityManagerFactoryBean customerEntityManager() throws Throwable {
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(customerDataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.hp.entity.customer"); // 设置实体类所在位置
		entityManager.setPersistenceUnitName("customerPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}
}
