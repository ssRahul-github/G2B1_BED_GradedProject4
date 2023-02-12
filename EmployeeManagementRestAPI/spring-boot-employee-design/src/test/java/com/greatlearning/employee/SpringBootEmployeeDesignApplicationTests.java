package com.greatlearning.employee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.greatlearning.employee.SpringBootEmployeeDesignApplication;
@ComponentScan
(basePackages={"com.greatlearning.employee.controller","com.greatlearning.employee.service"})
//@EnableJpaRepositories
@SpringBootTest
class SpringBootEmployeeDesignApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmployeeDesignApplication.class,args);
		System.out.println("Hi");
	}

}
