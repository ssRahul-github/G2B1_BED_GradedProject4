package com.greatlearning.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
@ComponentScan
(basePackages={"com.greatlearning.employee.controller","com.greatlearning.employee.service",
		"com.greatlearning.employee.repository","com.greatlearning.employee.util",
		"com.greatlearning.employee.config","com.greatlearning.employee.model"})

@SpringBootApplication
@EnableWebSecurity
public class SpringBootEmployeeDesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmployeeDesignApplication.class, args);
		System.out.println("Hi");
	}

}
