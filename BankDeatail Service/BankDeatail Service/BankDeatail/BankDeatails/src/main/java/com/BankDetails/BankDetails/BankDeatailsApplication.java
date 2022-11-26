package com.BankDetails.BankDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BankDeatailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankDeatailsApplication.class, args);
	}

}
