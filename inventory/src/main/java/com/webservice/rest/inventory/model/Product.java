package com.webservice.rest.inventory.model;

import javax.xml.bind.annotation.XmlRootElement;

/* Model class */
@XmlRootElement
public class Product {
	private int SKU;
	private String name;
	private int store;
	private int count;
	
	public Product(){}
	
	public Product(int SKU, String name, int store, int count) {
		super();
		this.SKU = SKU;
		this.name = name;
		this.store = store;
		this.count = count;
	}

	public int getSKU() {
		return SKU;
	}

	public void setSKU(int sKU) {
		SKU = sKU;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStore() {
		return store;
	}

	public void setStore(int store) {
		this.store = store;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Product [SKU=" + SKU + ", name=" + name + ", store=" + store + ", count=" + count + "]";
	}
	
	
}
