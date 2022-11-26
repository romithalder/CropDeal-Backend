package com.farmer.farmer.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "farmer_info")
public class farmer {
	
	@Id
	private String id;
	
	@NotNull(message = "You have give your name, this cannot be null")
	private String name;
	
	@NotNull(message = "You have give your email, this cannot be null")
	private String email;
	
	@NotNull(message = "You have give your name, this cannot be null")
	private String address;
	
	@NotNull(message = "You have give your mobile no., this cannot be null")
	private String mobile;
	
	@NotNull(message = "You have give a password, this cannot be null")
	private String password;
	
	List<cropDetail>crop=new ArrayList<>();

	public String getId() {
		return id;
	}

	public List<cropDetail> getCrop() {
		return crop;
	}

	public void setCrop(List<cropDetail> crop) {
		this.crop = crop;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public farmer(String id, String name, String email, String address, String mobile, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.mobile = mobile;
		this.password = password;
	}

	public farmer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public farmer(String id, @NotNull(message = "You have give your name, this cannot be null") String name,
			@NotNull(message = "You have give your email, this cannot be null") String email,
			@NotNull(message = "You have give your name, this cannot be null") String address,
			@NotNull(message = "You have give your mobile no., this cannot be null") String mobile,
			@NotNull(message = "You have give a password, this cannot be null") String password,
			List<cropDetail> crop) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.mobile = mobile;
		this.password = password;
		this.crop = crop;
	}

	@Override
	public String toString() {
		return "farmer [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", mobile="
				+ mobile + ", password=" + password + ", crop=" + crop + "]";
	}
	
	
	
}




