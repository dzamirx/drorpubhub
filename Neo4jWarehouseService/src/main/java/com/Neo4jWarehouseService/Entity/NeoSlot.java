package com.Neo4jWarehouseService.Entity;


import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Collections;
import java.util.List;

@Node
public class NeoSlot 
{

	@Id
	private long id;
	private String aisle;
	private int wrow;
	
	@Relationship(type = "OCCUPIED", direction = Relationship.INCOMING)
	private NeoPallet neoPallet;
	
	
	public NeoPallet getNeoPallets() {
		return neoPallet;
	}


	public void setNeoPallets(List<NeoPallet> neoPallets) {
		this.neoPallet = neoPallet;
	}


	public long getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAisle() {
		return aisle;
	}


	public void setAisle(String aisle) {
		this.aisle = aisle;
	}


	public int getWrow() {
		return wrow;
	}


	public void setWrow(int wrow) {
		this.wrow = wrow;
	}



	
	
		
}

