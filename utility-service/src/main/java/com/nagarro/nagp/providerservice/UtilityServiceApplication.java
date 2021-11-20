package com.nagarro.nagp.providerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UtilityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtilityServiceApplication.class, args);
	}

}
