package com.springboot.cloud.controller;

import com.springboot.cloud.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author zhangna
 * @Date 2020-01-08 13:20
 * @Description
 */
@RestController
public class TestController {

	@Resource
	private TestService testService;

	@GetMapping("/hello")
	public String hello() {
		testService.doThing("hello " + new Date());
		return "sunshine...";
	}

	@GetMapping("/hello2")
	public String hello2() {
		testService.doThing2("hello2 " + new Date());
		return "sunshine2...";
	}

}
