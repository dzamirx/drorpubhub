package com.Neo4jWarehouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class Neo4jWarehouseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4jWarehouseServiceApplication.class, args);

	}
	
	@Bean
	@LoadBalanced
	public WebClient getWebClient()
	{
		return  WebClient.create(null);
	}
	
	
	//too complex relative to WebClient
//	@Bean
//	@LoadBalanced
//	public WebClient.Builder getWebClientBuilder()
//	{
//		return  WebClient.builder();
//	}
	
	
	//deprecated 
//	@Bean
//	@LoadBalanced
//	public RestTemplate getRestTemplate()
//	{
//		return new RestTemplate();
//	}

}
