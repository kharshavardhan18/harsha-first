package com.greenServices.contract;

import java.util.Comparator;



public class AccountListContract{
	
	
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
	
	public AddressContract getAddress() {
		return address;
	}
	public void setAddress(AddressContract address) {
		this.address = address;
	}
	int id;
	int user_id;
	String company_name;
	String country;
	String account_username;
	String password;
	String account_email;
	String company_url;
	AddressContract address;
	
	
	
}
