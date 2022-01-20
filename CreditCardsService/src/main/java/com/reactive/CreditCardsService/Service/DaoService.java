package com.reactive.CreditCardsService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.reactive.CreditCardsService.Entity.Customer;
import com.reactive.CreditCardsService.Repository.CustomerRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Service
public class DaoService 
{
	@Autowired
	private CustomerRepo repo1;
	
	
	/** Define all CURD  referencing to the REACTIVE-MONGODB repositories layer **/
	
	/** Create **/
	public Mono<Customer> addCustomer(Mono<Customer> cs) //creates a new customer
	{
		return cs.flatMap(repo1::insert);
	}

	
	/** Read **/
	public Mono<Customer> getCustomerById(String id) //gets customer by id
	{
		return repo1.findById(id);
	}
	
	public Flux<Customer> getAllCustomers() //gets all customers
	{
		return repo1.findAll();
	}
	
	public Flux<Customer> getCustomerByAge(int min, int max) //gets customers by range of ages
	{
		return repo1.findByAgeBetween(Range.closed(min, max));
	}
	
	
	/** Update **/
	public Mono<Customer> updateCustomer(Mono<Customer> cs, String id) //update a customer's id
	{
		
		return repo1.findById(id)			//first find the specific costumer in DB according to his id
				.doOnNext(e->e.setId(id))	//set a new id to this customer
				.flatMap(repo1::save);		//re-save the customer in DB
	}
	
	
	/** Delete **/
	public Mono<Void> deleteCustomerByName(String name) //delete a customer by id
	{
		repo1.deleteByName(name);
		return null;
	}
	
}
