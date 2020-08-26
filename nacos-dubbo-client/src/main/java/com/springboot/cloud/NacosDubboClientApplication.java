package com.springboot.cloud;

import com.springboot.cloud.api.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangna
 * @Date 2020-01-08 17:44
 * @Description
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosDubboClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosDubboClientApplication.class, args);
	}

	@Slf4j
	@RestController
	static class TestController {

		@Reference
		HelloService helloService;

		@GetMapping("/test")
		public String test() {
			return helloService.hello("[dubbo]~ sunshine...");
		}
	}
}
