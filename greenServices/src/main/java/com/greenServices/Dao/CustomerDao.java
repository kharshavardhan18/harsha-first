package com.greenServices.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.greenServices.bean.AccountList;
import com.greenServices.bean.CustomerList;
import com.greenServices.bean.RegisterForm;
import com.greenServices.contract.CustomerListContract;
import com.greenServices.entities.CustomerEntity;
import com.greenServices.entities.SecAccounts;



// DAO interface
public interface CustomerDao {
	
	
	String DB_URL = "jdbc:mysql://localhost:3306/sec_vault?autoReconnect=true&useSSL=false";
	public Connection getConnection()  throws ClassNotFoundException, SQLException ;
	
	public CustomerListContract fetchByUserName(String userName, String password);
	
	public boolean addRequest(RegisterForm registerForm)throws ClassNotFoundException,SQLException, ParseException;
	
	public List<SecAccounts> fetchAllSecAccounts();
	
	public List<SecAccounts> fetchByUserId(int userId);
		
	public List<SecAccounts> addAccount(int userId,AccountList accountList);
	
	public List<SecAccounts> searchByKeyword(int userId,String key);
	
	public CustomerEntity fetchByUsername(String username);
	
}
