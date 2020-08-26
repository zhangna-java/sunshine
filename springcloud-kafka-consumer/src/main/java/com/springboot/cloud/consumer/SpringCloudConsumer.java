package com.springboot.cloud.consumer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 * @Author zhangna
 * @Date 2020-03-11 10:52
 * @Description
 */
@Component
@EnableBinding(Sink.class) //输入
@Slf4j
public class SpringCloudConsumer {

	@StreamListener(Sink.INPUT)
	public void revice(Object message){
		log.info("consumer---"+JSON.toJSONString(message));
	}
}
