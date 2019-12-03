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
import com.hp.repository.order.OrderDatasourceProperties;
import com.mysql.cj.jdbc.MysqlXADataSource;

/**
 * @Description order数据源配置
 * @author hp
 * @Data 2019/12/3
 */
@Configuration
@DependsOn("transactionManager") // @DependsOn控制bean初始化顺序,先加载transactionManager的bean
@EnableConfigurationProperties(OrderDatasourceProperties.class)
@EnableJpaRepositories(basePackages = "com.hp.repository.order",  // 设置Repository所在位置
                       entityManagerFactoryRef = "orderEntityManager",  // 实体管理工厂引用名称，对应到@Bean注解对应的方法
                       transactionManagerRef = "transactionManager")   // 事务管理工厂引用名称，对应到@Bean注解对应的方法
public class OrderConfig {

	// jpa配置信息
	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	// 这里注入order dataSource信息的类
	@Autowired
	private OrderDatasourceProperties orderDatasourceProperties;

	@Bean(name = "orderDataSource", initMethod = "init", destroyMethod = "close")
	public DataSource orderDataSource() throws Throwable {
		// 给XADataSource 设置 DataSource 属性
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setURL(orderDatasourceProperties.getUrl());
		mysqlXADataSource.setUser(orderDatasourceProperties.getUser());
		mysqlXADataSource.setPassword(orderDatasourceProperties.getPasswd());
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

		// 创建 Atomiko， 并将 mysql的XA交给JTA管理
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXADataSource);
		xaDataSource.setUniqueResourceName("datasource1");
		// xaDataSource.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
		return xaDataSource;
	}

	@Bean(name = "orderEntityManager")
	public LocalContainerEntityManagerFactoryBean orderEntityManager() throws Throwable {

		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(orderDataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.hp.entity.order");   // 设置实体类所在位置
		entityManager.setPersistenceUnitName("orderPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
