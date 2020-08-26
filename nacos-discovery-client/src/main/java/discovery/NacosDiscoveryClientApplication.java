package discovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


/**
 * @Author zhangna
 * @Date 2020-01-06 16:21
 * @Description 服务消费 1，RestTemplate；2，WebClient 3，Feign
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosDiscoveryClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(NacosDiscoveryClientApplication.class, args);
	}


	/**
	 * 使用RestTemplate
	 */
//	@Slf4j
//	@RestController
//	static class TestController {
//
//		@Autowired
//		RestTemplate restTemplate;
//
//		@GetMapping("/test")
//		public String test() {
//			String result = restTemplate.getForObject("http://sunshine-nacos-discovery-server/hello?name=wwww", String.class);
//			return "Return : " + result;
//		}
//	}
//
//	@Bean
//	@LoadBalanced //开启负载均衡的注解
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
//

	/**
	 * 使用 WebClient
	 */
	@Slf4j
	@RestController
	static class TestController {

		@Autowired
		private WebClient.Builder webClientBuilder;

		@GetMapping("/test")
		public Mono< String > test() {
			Mono< String > result = webClientBuilder.build().get().uri("http://sunshine-nacos-discovery-server/hello?name=qwee").retrieve().bodyToMono(String.class);
			return result;
		}
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}

}
