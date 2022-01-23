package com.warehouseservice.Service;

import java.util.Comparator;

public class TreesetComperator implements Comparator<Customer>
{

	@Override
	public int compare(Customer o1, Customer o2) {
		if(o1.id == o2.id) {return 0;}
		else{return (o1.id-o2.id);}
	}

}
