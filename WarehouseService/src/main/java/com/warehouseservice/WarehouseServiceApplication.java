package com.warehouseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
//@EnableCaching
@EnableSwagger2
public class WarehouseServiceApplication {
	
	@Bean
	@LoadBalanced//for Eureka to take control of the load balancing between microservices
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	
	@Bean
	@LoadBalanced
	public WebClient getWebClient()
	{
		return  WebClient.create();
	}
//	@Bean
//	@LoadBalanced
//	public WebClient.Builder getWebclientBuilder()
//	{
//		return WebClient.builder();
//		 
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(WarehouseServiceApplication.class, args);
	}

}
