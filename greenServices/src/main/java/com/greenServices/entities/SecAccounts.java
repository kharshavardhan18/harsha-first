package com.greenServices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customeraccounts")
public class SecAccounts {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private int user_id;
	
	@Column
	private String company_name;
	
	@Column
	private String country;
	
	@Column
	private String account_username;
	
	@Column
	private String password;
	
	@Column
	private String account_email;
	
	@Column
	private String company_url;
	
	@OneToOne
	@JoinColumn(name="addr_id")
	private AddressEntity addressEntity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAccount_username() {
		return account_username;
	}

	public void setAccount_username(String account_username) {
		this.account_username = account_username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount_email() {
		return account_email;
	}

	public void setAccount_email(String account_email) {
		this.account_email = account_email;
	}

	public String getCompany_url() {
		return company_url;
	}

	public void setCompany_url(String company_url) {
		this.company_url = company_url;
	}

	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	public void setAddressEntity(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}

	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "STOCK_ID", nullable = false)
	private CustomerEntity customerEntity;*/

	
	
	
	
	}

