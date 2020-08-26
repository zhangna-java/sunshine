package com.springboot.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangna
 * @Date 2020-01-07 12:26
 * @Description 配置中心
 */
@SpringBootApplication
public class NacosConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(NacosConfigApplication.class, args);
	}

	@Slf4j
	@RestController
	@RefreshScope
	static class TestController {

		@Value("${sunshine.name:}")
		private String name;

		@GetMapping("/test")
		public String hello() {
			return name;
		}

	}
}
