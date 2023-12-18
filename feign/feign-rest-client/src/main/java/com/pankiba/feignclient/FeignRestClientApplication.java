package com.pankiba.feignclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignRestClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignRestClientApplication.class, args);
	}

}
