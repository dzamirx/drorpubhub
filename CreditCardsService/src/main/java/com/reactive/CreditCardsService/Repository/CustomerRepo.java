package com.reactive.CreditCardsService.Repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactive.CreditCardsService.Entity.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface CustomerRepo extends ReactiveMongoRepository<Customer, Integer> {

	Mono<Customer> deleteByName(String name);

	Flux<Customer> findByAgeBetween(Range<Integer> closed);

	Mono<Customer> findById(String id);

}
