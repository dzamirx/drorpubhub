package com.warehouseservice.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pallet implements Serializable
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private int date;
	
	@Column
	private int batch;
	
	@Column
	private String product;
	
	@Column
	private int tracing;
	
	@Column
	private String remarks;
	
	@Column
	private String in_name;
	
	@Column
	private String out_name;
	
	@Column
	private int slotId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
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

	public int getTracing() {
		return tracing;
	}

	public void setTracing(int tracing) {
		this.tracing = tracing;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIn_name() {
		return in_name;
	}

	public void setIn_name(String in_name) {
		this.in_name = in_name;
	}

	public String getOut_name() {
		return out_name;
	}

	public void setOut_name(String out_name) {
		this.out_name = out_name;
	}

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}



}
/*

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public int getDate() {
	return date;
}

public void setDate(int date) {
	this.date = date;
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

public int getTracing() {
	return tracing;
}

public void setTracing(int tracing) {
	this.tracing = tracing;
}

public String getRemarks() {
	return remarks;
}

public void setRemarks(String remarks) {
	this.remarks = remarks;
}

public String getIn_name() {
	return in_name;
}

public void setIn_name(String in_name) {
	this.in_name = in_name;
}

public String getOut_name() {
	return out_name;
}

public void setOut_name(String out_name) {
	this.out_name = out_name;
}

public int getSlotId() {
	return slotId;
}

public void setSlotId(int slotId) {
	this.slotId = slotId;
}
	//@Embedded
	//private Slot slot;
*/