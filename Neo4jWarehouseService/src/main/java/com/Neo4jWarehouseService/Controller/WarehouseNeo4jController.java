package com.Neo4jWarehouseService.Controller;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.Neo4jWarehouseService.Entity.AuthenticationRequest;
import com.Neo4jWarehouseService.Entity.NeoPallet;
import com.Neo4jWarehouseService.Entity.NeoSlot;
import com.Neo4jWarehouseService.Entity.Slot;
import com.Neo4jWarehouseService.Repositories.NeoRepo;
//import com.warehouseservice.Entity.Slot;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import reactor.core.publisher.Mono;
//import com.warehouseservice.Entity.Pallet;

@RestController
@RequestMapping("/api")
public class WarehouseNeo4jController 
{

	@Autowired
	private NeoRepo slotsRepo;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
//	@Autowired
//	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private WebClient webClient;
	
	List<NeoSlot> listNslots = new ArrayList<NeoSlot>();
	
	@GetMapping("/allslots")
	public List<NeoSlot> getAll()
	{
		listNslots = slotsRepo.findAll();
		return listNslots;
	}

	/*************** Request to get information from a different/separate microservice*****************/
	@GetMapping("/slot")
	public Mono<Slot> findById()
	{		
		String newToken = getAuthentication();
		String temp = "Bearer "+newToken.substring(8, 136);
		
		/* we send a GET request with the new JWT given, to the WAREHOUSE-SERVICE to get back a the slot according to its ID */
		return webClient.get()
				.uri("http://localhost:8081/api/slotid/6")				//destination URi
				.header(HttpHeaders.AUTHORIZATION, temp)			//add the received JWT to the new massage header as an authorization field
				.retrieve()											//sends the request to the destination microservice
				//.onStatus(HttpStatus.UNAUTHORIZED, throwable ->FallbackMethod())
				.bodyToMono(Slot.class);							//promise we will get what you wanted but for now an asynchronous object like a place holder empty page

	}


	/*** Authentication Utility Method to connect to the WAREHOUSE-SERVICE first in order to get JWT authentication capability string ***/
	public String getAuthentication()
	{
		AuthenticationRequest newrequest = new AuthenticationRequest("foo","foo");
		/* we send a POST request with user-name and password as an authentication request to the WAREHOUSE-SERVICE to get back a JWT for next interaction */
		String newToken = webClient.post()
						.uri("http://localhost:8081/api/authenticate")		//destination URi
						.bodyValue(newrequest)							//add an authenticationRequest object to the massage body which consists of username and passwords strings
						.retrieve()										//sends the request to the destination microservice
						.bodyToMono(String.class)						//get the String-response back to a Mono waiting type, but if blocking now then Mono is not really has to wait
						.timeout(Duration.ofSeconds(15))				//setting a time limit for authentication to succeed 
						.onErrorResume(throwable ->authFallbackMethod())//if authentication takes too long we switch to the fallback method below to recover
						//.onErrorReturn("fallbackMethod")				//if timeout passes we can also return a String directly with no fallback method to be used
						.block();										//blocking all further tasks until a jwt is returned from the destination server(actually downgrading to RestTemplate)

		return newToken;
	}
	
	public Mono<? extends String> authFallbackMethod()//the fallback method which be called if the second GET request is failing not to response in time
	{
		return Mono.just("Authentication is not relevant");

	}
}

