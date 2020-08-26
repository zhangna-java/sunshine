package com.springboot.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author zhangna
 * @Date 2020-01-08 17:28
 * @Description
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosDubboServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(NacosDubboServerApplication.class, args);
	}
}
