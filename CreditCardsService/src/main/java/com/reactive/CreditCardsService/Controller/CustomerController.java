package com.reactive.CreditCardsService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.CreditCardsService.Entity.Customer;
import com.reactive.CreditCardsService.Service.DaoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CustomerController 
{
	@Autowired
	private DaoService service;
	
	
		/** Read Mappings **/

	@GetMapping("/customers")
	public Flux<Customer> getCustomers() 
	{
		return service.getAllCustomers();
	}
	
	@GetMapping("/customer/{id}")
	public Mono<Customer> getCustomer(@PathVariable String id) //required information will be supplied in the massage URL as {}
	{
		return service.getCustomerById(id);
	}
	
	@GetMapping("/customers-range")
	public Flux<Customer> getCustomersBetweenRange(@RequestParam("min") int min,@RequestParam("max") int max) //required information will be supplied in the massage URL as /url/?x&?y
	{
		return service.getCustomerByAge(min, max);
	}
	
	
		/** Create Mappings **/
	
	@PostMapping("/addcustomer")
	public Mono<Customer> addCustomer(@RequestBody Mono<Customer> cs ) //required information will be supplied in the massage BODY
	{
		return service.addCustomer(cs);
	}
	
	
		/** Update Mappings **/
	
	@PutMapping("/updatecustomer/{id}")
	public Mono<Customer> updateCustomer(@RequestBody Mono<Customer> cs,@PathVariable String id ) //required information will be supplied in the massage BODY and in the URL as {}
	{
		return service.updateCustomer(cs, id);
	}
	
	
		/** Delete Mappings **/
	
	@DeleteMapping("/delete/{name}")
	public Mono<Void> removeCustomer(@PathVariable String name) //required information will be supplied in the massage URL as {}
	{
		return service.deleteCustomerByName(name);

	}
	
	
}
