package com.reactive.CreditCardsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.reactive.CreditCardsService.Controller.CustomerController;
import com.reactive.CreditCardsService.Entity.Customer;
import com.reactive.CreditCardsService.Repository.CustomerRepo;
import com.reactive.CreditCardsService.Service.DaoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@RunWith(SpringRunner.class)
@WebFluxTest(DaoService.class)
//@SpringBootTest
class CreditCardsServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private CustomerController cntr;
	@MockBean
	private CustomerRepo crep;
	
	public static Logger loggertest = LoggerFactory.getLogger(CreditCardsServiceApplication.class);
	
	
	@Test		//post a customer functionality
	public void addCustomerTest() 
	{
		loggertest.info("1st Mockito test-case executing for ADDING A CUSTOMER");
		Mono<Customer> cs = Mono.just(new Customer("323","may","egypt",34));
		
		when(cntr.addCustomer(cs)).thenReturn(cs);
		
		webTestClient.post().uri("/api/addcustomer")
		.body(Mono.just(cs),Customer.class)
		.exchange()
		.expectStatus().isOk();//200
	}
	
	
	@Test		//get all customers functionality
	public void getCustomersTest() 
	{
		loggertest.info("2nd Mockito test-case executing for GETTING ALL CUSTOMERS");
		Flux<Customer> flxcs = Flux.just(new Customer("787","malik","egypt",34),new Customer("543","john","nizar",12));
		
		when(cntr.getCustomers()).thenReturn(flxcs);
		
		Flux<Customer> response = webTestClient.get().uri("/api/customers")
		.exchange()
		.expectStatus().isOk()//200
		.returnResult(Customer.class)
		.getResponseBody();
		
		StepVerifier.create(response)
		.expectSubscription().expectNext(new Customer("787","malik","egypt",34))
		.expectNext(new Customer("543","john","nizar",12))
		.verifyComplete();
	}
	
	
	@Test		//get a customer by ANY id functionality
	public void getCustomerTest() 
	{
		loggertest.info("3rd Mockito test-case executing for GETTING A CUSTOMER");
		Mono<Customer> csi = Mono.just(new Customer("787","malik","egypt",34));
		
		when(cntr.getCustomer(any())).thenReturn(csi);
		
		Flux<Customer> response = webTestClient.get().uri("/api/customer/787")
				.exchange()
				.expectStatus().isOk()//200
				.returnResult(Customer.class)
				.getResponseBody();
				
				StepVerifier.create(response)
				.expectSubscription()
				.expectNextMatches(c->c.getName().equals("malik"))
				.verifyComplete();
	}
	
	
	@Test		//update a customer by ANY id functionality
	public void updateCustomerTest() 
	{
		loggertest.info("4th Mockito test-case executing for UPDATING A CUSTOMER");
		Mono<Customer> ucs = Mono.just(new Customer("965","loren","france",28));
		String new_id = "965";
		
		when(cntr.updateCustomer(ucs,new_id)).thenReturn(ucs);
		
		webTestClient.put().uri("/api/updatecustomer/965")
				.body(Mono.just(ucs),Customer.class)
				.exchange()
				.expectStatus().isOk();//200	
	}
	
	
	@Test		//delete a customer by ANY id functionality
	public void deleteCustomerTest() 
	{
		loggertest.info("5th Mockito test-case executing for DELETING A CUSTOMER");
		given(cntr.removeCustomer(any())).willReturn(Mono.empty());
		
		webTestClient.delete().uri("/api/delete/965")
				.exchange()
				.expectStatus().isOk();//200		
	}
	
}
