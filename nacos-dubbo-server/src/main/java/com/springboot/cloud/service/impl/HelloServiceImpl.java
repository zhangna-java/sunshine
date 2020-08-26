package com.springboot.cloud.service.impl;

import com.springboot.cloud.api.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author zhangna
 * @Date 2020-01-08 17:30
 * @Description
 */
@Service
@Slf4j
public class HelloServiceImpl implements HelloService {
	@Override
	public String hello(String name) {
		return "hello " + name;
	}
}
