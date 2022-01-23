package com.URLExtractor.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String catName;
	
	@Column
	private String [] keywords;
	
	
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String[] getKeywords() {
		return keywords;
	}
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	
	
	
}
