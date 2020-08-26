package com.springboot.cloud;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author zhangna
 * @Date 2020-01-07 19:10
 * @Description
 */
@SpringBootApplication
public class SentinelDishboardApplication {
	public static void main(String[] args) {
		SpringApplication.run(SentinelDishboardApplication.class, args);
	}

//	@Slf4j
//	@RestController
//	static class TestController {
//
//		@GetMapping("/hello")
//		public String hello() {
//			return "--sunshine--";
//		}
//
//	}

	@Bean
	public SentinelResourceAspect sentinelResourceAspect() {
		return new SentinelResourceAspect();
	}

}
