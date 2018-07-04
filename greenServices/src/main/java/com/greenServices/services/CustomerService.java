package com.greenServices.services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.TreeSet;

import com.greenServices.bean.AccountList;
import com.greenServices.bean.CustomerList;
import com.greenServices.bean.RegisterForm;
import com.greenServices.contract.CustomerListContract;
import com.greenServices.entities.SecAccounts;



public interface CustomerService {
	
	public CustomerListContract isPresent(String userName,String password) ;
	
	public boolean isAdded(RegisterForm registerForm) throws ClassNotFoundException, SQLException, ParseException ;
	
	public List<SecAccounts> addAccountForm(int userId,AccountList accountList);
	
	public List<AccountList> searchBykey(int userId,String keyword);
	
	public List<AccountList> fetchByUserId(int userId);
	
	public List<AccountList> OrderByCompany(int userId); 
	
	public List<AccountList> OrderByCountry(int userId,int countryClick); 
	
	public TreeSet<AccountList> sortByTree(int userId,int companyClick);
}
