package com.hp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mysql.datasource.order")
public class DBOrderConfig {

	private String url;

	private String username;

	private String password;

	// 最小连接数
	private int minPoolSize;

	// 最大连接数
	private int maxPoolSize;

	// 连接最大存活时间
	private int maxLifetime;

	// 获取连接失败后重新获取等待的最大时间，在这个时间内如果有可用连接，将返回
	private int borrowConnectionTimeout;

	// java数据库连接池，最大可等待获取datasource的时间
	private int loginTimeout;

	// 连接回收时间
	private int maintenanceInterval;

	// 最大闲置，超过最小连接池连接的连接将被关闭
	private int maxIdleTime;

	// 测试SQL
	private String testQuery;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}

	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public int getMaxLifetime() {
		return maxLifetime;
	}

	public void setMaxLifetime(int maxLifetime) {
		this.maxLifetime = maxLifetime;
	}

	public int getBorrowConnectionTimeout() {
		return borrowConnectionTimeout;
	}

	public void setBorrowConnectionTimeout(int borrowConnectionTimeout) {
		this.borrowConnectionTimeout = borrowConnectionTimeout;
	}

	public int getLoginTimeout() {
		return loginTimeout;
	}

	public void setLoginTimeout(int loginTimeout) {
		this.loginTimeout = loginTimeout;
	}

	public int getMaintenanceInterval() {
		return maintenanceInterval;
	}

	public void setMaintenanceInterval(int maintenanceInterval) {
		this.maintenanceInterval = maintenanceInterval;
	}

	public int getMaxIdleTime() {
		return maxIdleTime;
	}

	public void setMaxIdleTime(int maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}

	public String getTestQuery() {
		return testQuery;
	}

	public void setTestQuery(String testQuery) {
		this.testQuery = testQuery;
	}

}
