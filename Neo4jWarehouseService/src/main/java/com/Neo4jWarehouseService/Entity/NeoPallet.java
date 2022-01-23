package com.Neo4jWarehouseService.Entity;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.neo4j.ogm.annotation.Relationship;


@Node
public class NeoPallet 
{

	@Id
	private long id;
	private String product;
	private int batch;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	

	
		
}

