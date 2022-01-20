package com.reactive.CreditCardsService.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document(collection = "CreditCards")
public class Customer 
{
	@Id
	private String id;
	private String name;
	private String Address;
	private int age;

	
}
