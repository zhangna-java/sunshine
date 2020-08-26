package com.springboot.cloud.producer;

import com.springboot.cloud.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

/**
 * @Author zhangna
 * @Date 2020-03-10 19:17
 * @Description  发送消息
 */
@Component
@EnableScheduling
@EnableBinding(Source.class) //Source:输出
@Slf4j
public class SpringCloudProducer {

	@Autowired
	private BinderAwareChannelResolver resolver;


	@Scheduled(cron="0/1 * * * * ?")
	public void send(){

		MessageChannel messageChannel = resolver.resolveDestination("data");
		String message = "{\n" + "    \"userId\": 234567,\n" + "    \"orderId\": 2902306918400,\n" + "    \"subOrderId\": 2902306918401,\n" + "    \"siteId\": 10219,\n" + "    \"siteName\": \"site_blabla\",\n" + "    \"cityId\": 101,\n" + "    \"cityName\": \"北京市\",\n" + "    \"warehouseId\": 636,\n" + "    \"merchandiseId\": 187699,\n" + "    \"price\": 299,\n" + "    \"quantity\": 2,\n" + "    \"orderStatus\": 1,\n" + "    \"isNewOrder\": 0,\n" + "    \"timestamp\": 1572963672217\n" + "}";

		messageChannel.send(MessageBuilder.withPayload(message)
//				      .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				      .build());

		log.info("message:{}",message);



	}



}
