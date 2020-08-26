package com.springboot.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author zhangna
 * @Date 2020-01-08 13:17
 * @Description
 */
@Slf4j
@Service
public class TestService {

	/**
	 * 限流控制
	 * FlowException
	 *
	 * @param para
	 */
	@SentinelResource(value = "doThing", blockHandler = "exceptionHandle")
	public void doThing(String para) {
		log.info(para);
	}

	// 限流与阻塞处理
	public void exceptionHandle(String str, BlockException blockException) {
		log.error("blockException: " + str, blockException);
	}

	/**
	 * 熔断降级
	 * DegradeException
	 *
	 * @param para
	 */
	@SentinelResource(value = "doThing2", fallback = "fallbackHandler")
	public void doThing2(String para) {
		log.info(para);
		throw new RuntimeException("发生异常");
	}

	public void fallbackHandler(String str) {
		log.error("blockException: " + str);
	}
}
