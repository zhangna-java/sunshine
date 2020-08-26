package com.springboot.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @Author zhangna
 * @Date 2020-03-10 19:09
 * @Description
 */
@SpringBootApplication
@EnableBinding
public class KafkaConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}
}