package com.springboot.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangna
 * @Date 2020-01-07 19:24
 * @Descriptio nacos配置更新同步到sentinel
 */
@SpringBootApplication
public class NacosSentinelDashboardApplication {
	public static void main(String[] args) {
		SpringApplication.run(NacosSentinelDashboardApplication.class, args);
	}

	@Slf4j
	@RestController
	static class TestController {

		@GetMapping("/test")
		public String hello() {
			return "hello ; sunshine !!! ";
		}

	}
}
