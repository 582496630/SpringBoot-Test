package com.springboot.zy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching//缓存注解支持
@SpringBootApplication
@MapperScan("com.springboot.zy.dao")//不用每个mapper.java类在添加@Mapper这个注解
public class SpringBootWebTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebTestApplication.class, args);
	}
}
