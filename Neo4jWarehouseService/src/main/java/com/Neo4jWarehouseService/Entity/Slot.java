package com.Neo4jWarehouseService.Entity;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;



@Node
//@Embeddable
public class Slot 
{

	@Id
	private int id;
	

	private int aisle;
	

	private int wrow;
	
	
	private int floor;
	
	
	private double height;
		
	
	private boolean occupied;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAisle() {
		return aisle;
	}

	public void setAisle(int aisle) {
		this.aisle = aisle;
	}

	public int getWrow() {
		return wrow;
	}

	public void setWrow(int wrow) {
		this.wrow = wrow;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	
		
}

