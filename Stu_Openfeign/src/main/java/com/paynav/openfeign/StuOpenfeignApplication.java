package com.paynav.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StuOpenfeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(StuOpenfeignApplication.class, args);
	}

}
