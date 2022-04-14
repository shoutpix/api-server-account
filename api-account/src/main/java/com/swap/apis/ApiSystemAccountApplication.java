package com.swap.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching 
public class ApiSystemAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSystemAccountApplication.class, args);
	}

}
