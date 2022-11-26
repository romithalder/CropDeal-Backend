package com.CropDeal.CropDetail.CropDetail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CropDetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(CropDetailApplication.class, args);
	}

}
