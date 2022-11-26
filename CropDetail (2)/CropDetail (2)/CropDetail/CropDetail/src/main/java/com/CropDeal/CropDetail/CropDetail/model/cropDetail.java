package com.CropDeal.CropDetail.CropDetail.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cropDetails")
public class cropDetail {
	
	@Id
	private String id;
	
	private String type;
	
	private String quantity;
	
	private String address;
	
	
	public cropDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public cropDetail(String id, String type, String quantity, String address) {
		super();
		this.id = id;
		this.type = type;
		this.quantity = quantity;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "cropDetail [id=" + id + ", type=" + type + ", quantity=" + quantity + ", address=" + address + "]";
	}
	
	
	
	
	

}
