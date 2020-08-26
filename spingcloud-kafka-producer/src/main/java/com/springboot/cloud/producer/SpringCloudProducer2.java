package com.springboot.cloud.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/**
 * @Author zhangna
 * @Date 2020-03-11 11:32
 * @Description
 */
@EnableBinding(Source.class)
@Slf4j
@Service
@EnableScheduling
public class SpringCloudProducer2{

	@Autowired
	private Source source;

	@Scheduled(cron="0/1 * * * * ?")
	public void sendMsg() {
		String message = "{\n" + "    \"userId\": 234567,\n" + "    \"orderId\": 2902306918400,\n" + "    \"subOrderId\": 2902306918401,\n" + "    \"siteId\": 10219,\n" + "    \"siteName\": \"site_blabla\",\n" + "    \"cityId\": 101,\n" + "    \"cityName\": \"北京市\",\n" + "    \"warehouseId\": 636,\n" + "    \"merchandiseId\": 187699,\n" + "    \"price\": 299,\n" + "    \"quantity\": 2,\n" + "    \"orderStatus\": 1,\n" + "    \"isNewOrder\": 0,\n" + "    \"timestamp\": 1572963672217\n" + "}";
		source.output().send(MessageBuilder.withPayload(message).build());
       log.info("111111111");
	}


	}
