package com.greenServices.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="customer")
public class CustomerEntity {
	

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	@Column
	String username;
	@Column
	String firstname;
	@Column
	String lastname;
	@Column
	String gender;
	@Column
	String email ; 
	 @Temporal(TemporalType.DATE)
	Date dob;
	@Column
	String password ;
	 @Temporal(TemporalType.TIMESTAMP)
	Date create_date;
	 @Temporal(TemporalType.TIMESTAMP)
	Date update_date;
	 
	 /*Set<SecAccounts> secAccounts = new HashSet<SecAccounts>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<SecAccounts> getSecAccounts() {
		return secAccounts;
	}

	public void setSecAccounts(Set<SecAccounts> secAccounts) {
		this.secAccounts = secAccounts;
	}
*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	
	
	 
	 
	/* @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerEntity")
	 Set<SecAccounts> stockDailyRecords = new HashSet<SecAccounts>();*/
}