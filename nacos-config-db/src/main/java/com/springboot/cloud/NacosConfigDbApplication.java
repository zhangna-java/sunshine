package com.springboot.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author zhangna
 * @Date 2020-01-07 17:56
 * @Description
 */
@SpringBootApplication
@EnableConfigServer
public class NacosConfigDbApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigDbApplication.class, args);
		JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
//		jdbcTemplate.execute("delete from properties")
//		jdbcTemplate.execute("INSERT INTO properties VALUES(1, 'com.sunshine.message', 'test-stage-master', 'config-client', 'stage', 'master')");
//		jdbcTemplate.execute("INSERT INTO properties VALUES(2, 'com.sunshine.message', 'test-online-master', 'config-client', 'online', 'master')");
//		jdbcTemplate.execute("INSERT INTO properties VALUES(3, 'com.sunshine.message', 'test-online-develop', 'config-client', 'online', 'develop')");
//		jdbcTemplate.execute("INSERT INTO properties VALUES(4, 'com.sunshine.message', 'hello-online-master', 'hello-service', 'online', 'master')");
//		jdbcTemplate.execute("INSERT INTO t_sunshine_config VALUES(5, 'com.sunshine.message', 'hello-online-develop', 'nacos', '1')");

	}

}
