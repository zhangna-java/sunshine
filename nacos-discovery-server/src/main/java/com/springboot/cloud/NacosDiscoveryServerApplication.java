package com.springboot.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author zhangna
 * @Date 2020-01-06 16:21
 * @Description 服务提供者
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosDiscoveryServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(NacosDiscoveryServerApplication.class, args);
	}

	@Slf4j
	@RestController
	static class TestController {

		@GetMapping("/hello")
		public String hello(@RequestParam String name) {
			log.info("invoked name = " + name);
			return "hello " + name;
		}

	}


}
