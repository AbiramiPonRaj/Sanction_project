package com.ponsun.san;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCaching
//@Import(AsyncConfiguration.class)
public class PONSUN_SAN_Application {
	public static void main(String[] args) {
		SpringApplication.run(PONSUN_SAN_Application.class, args);
	}
}