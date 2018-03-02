package com.springboot.zy.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源配置，支持多数据源
 * 
 * @author zhoupingping
 * 
 */
@Configuration
public class DataSourceConfig {
	private static Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

	/**
	 * 创建主数据源，W/R
	 * 
	 * @return
	 */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource masterDataSource() {
		log.info("Get {} master datasource successfully ......");
		// master.setDefaultAutoCommit(false);
		return new DruidDataSource();
	}

	/**
	 * 创建从数据源，R
	 * 
	 * @return
	 */
	// @Bean(name="slave1", destroyMethod = "close", initMethod="init")
	// @ConfigurationProperties(prefix = "yimi.slave1.datasource")
	// public DataSource slave1DataSource() {
	// log.info("Get {} slave1 datasource successfully ......");
	// return new DruidDataSource();
	// }

}
