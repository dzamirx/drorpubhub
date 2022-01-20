package com.reactive.CreditCardsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.reactive.config.EnableWebFlux;

import com.reactive.CreditCardsService.Entity.Customer;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;
//import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "springh",version = "1.0",description = "sample"))
//@EnableCaching
public class CreditCardsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardsServiceApplication.class, args);
		
	}

}
