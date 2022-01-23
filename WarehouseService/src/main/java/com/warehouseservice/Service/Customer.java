package com.warehouseservice.Service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Customer 
{

	String name;
	public String getName() {
		return name;
	}


	int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//general constructor
	public Customer(String name, int id) {
		
		this.name = name;
		this.id = id;
	}

	//Overrides hascode and equals method to consider only ID's when inserting therfor id duplications will be ignored
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id;
	}


	@Override
	public boolean equals(Object obj) {
		
		Customer cus = (Customer)obj;
		return (id==cus.id);
	}

		
	public static void main(String[] args) {
		// LinkedHashSet<Customer> set = new LinkedHashSet<Customer>();
		TreesetComperator com = new TreesetComperator();
		 TreeSet<Customer> set = new TreeSet<Customer>(com);//^treeset needs a comparator in order to know according to what field we want to sort by

		 set.add(new Customer("jon", 0));
		 set.add(new Customer("jony", 6));
		 set.add(new Customer("jon", 5));
		 set.add(new Customer("jon", 6));
		 set.add(new Customer("jon", 0));
		 
		 Iterator<Customer> it = set.iterator();
		 while (it.hasNext()) {
			Customer customer = (Customer) it.next();
			System.out.println(customer);
		}
		 
		 
	}

}
