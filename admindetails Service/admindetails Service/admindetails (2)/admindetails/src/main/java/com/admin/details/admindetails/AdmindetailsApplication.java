package com.admin.details.admindetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AdmindetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdmindetailsApplication.class, args);
	}

}
