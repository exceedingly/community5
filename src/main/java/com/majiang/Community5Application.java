package com.majiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("life.majiang.community.mapper.*")
@ComponentScan(basePackages = {"com.majiang.mapper.*"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Community5Application {

	public static void main(String[] args) {
		SpringApplication.run(Community5Application.class, args);
	}

}
