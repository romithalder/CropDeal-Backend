package com.CropDealAPI.CropDealGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@SpringBootApplication
@EnableEurekaClient
public class CropDealGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CropDealGatewayApplication.class, args);
	}

}
