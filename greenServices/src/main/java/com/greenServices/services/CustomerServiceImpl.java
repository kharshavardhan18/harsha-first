package com.greenServices.services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.greenServices.Dao.CustomerDao;
import com.greenServices.bean.AccountList;
import com.greenServices.bean.Address;
import com.greenServices.bean.CustomerList;
import com.greenServices.bean.RegisterForm;
import com.greenServices.contract.CustomerListContract;
import com.greenServices.entities.SecAccounts;


@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

	//static int companyClick=0;
	//static int countryClick=0;
	@Autowired
	CustomerDao customerDoa;

	@Override
	public CustomerListContract isPresent(String userName,String password)  {

		System.out.println("In service Layer ispresent()");

		CustomerListContract customerList= customerDoa.fetchByUserName(userName,password);

		return customerList;
	}

	@Override
	public boolean isAdded(RegisterForm registerForm) throws ClassNotFoundException, SQLException, ParseException {
		boolean userAdded=customerDoa.addRequest(registerForm);
		return userAdded;
	}

	@Override
	public List<SecAccounts> addAccountForm(int userId,AccountList accountList) {

		List<SecAccounts> account=customerDoa.addAccount(userId,accountList);



		return account;
	}

	/*@Override
	public List<SecAccounts> searchBykey(int userId, String keyword) {
		System.out.println("in service layer search by key()=="+keyword);

		List<SecAccounts> allaccounts=customerDoa.fetchAllSecAccounts();

		List<SecAccounts> accListByKey=null;

		for(SecAccounts accounts:allaccounts)
		{
			if(accounts.getCompany_name().contains(keyword)|| accounts.getAccount_username().equals(keyword)||accounts.getCountry().contains(keyword))
			{
				accListByKey.add(accounts);
			}
		}
		return accListByKey;
	}*/

	public List<AccountList> searchBykey(int userId, String keyword)
	{
		List<SecAccounts> accountByKey=customerDoa.searchByKeyword(userId, keyword);
		List<AccountList> accountList=new ArrayList<AccountList>();
		AccountList accounts=null;
		for(SecAccounts secAccountsList: accountByKey)
		{
			accounts=new AccountList();

			accounts.setId(secAccountsList.getId());
			accounts.setPassword(secAccountsList.getPassword());
			accounts.setAccount_email(secAccountsList.getAccount_email());
			accounts.setAccount_username(secAccountsList.getAccount_username());
			accounts.setCompany_name(secAccountsList.getCompany_name());
			accounts.setCompany_url(secAccountsList.getCompany_url());
			Address address=new Address();
			address.setId(secAccountsList.getAddressEntity().getId());
			address.setAddress(secAccountsList.getAddressEntity().getAddress());
			address.setZipcode(secAccountsList.getAddressEntity().getZipcode());
			accounts.setAddress(address);
			accountList.add(accounts);
		}

		return accountList;

	}

	@Override
	public List<AccountList> fetchByUserId(int userId) {

		List<SecAccounts> secAccounts= customerDoa.fetchByUserId(userId);

		AccountList accountList=null;

		List<AccountList> pojoAccounts=new ArrayList<AccountList>();

		for(SecAccounts accounts:secAccounts)
		{
			accountList=new AccountList();
			accountList.setId(accounts.getId());
			accountList.setUser_id(accounts.getUser_id());
			accountList.setCompany_name(accounts.getCompany_name());
			accountList.setAccount_email(accounts.getAccount_email());
			accountList.setCountry(accounts.getCountry());
			accountList.setAccount_username(accounts.getAccount_username());
			accountList.setPassword(accounts.getPassword());
			accountList.setCompany_url(accounts.getCompany_url());

			Address address=new Address();
			if(accounts.getAddressEntity()!=null)
			{
				address.setId(accounts.getAddressEntity().getId());
				address.setAddress(accounts.getAddressEntity().getAddress());
				address.setZipcode(accounts.getAddressEntity().getZipcode());
			}

			accountList.setAddress(address);

			/*	Set<AccountList> treeSet=new TreeSet<AccountList>();
			treeSet.add(accountList);*/

			pojoAccounts.add(accountList);

		}
		return pojoAccounts;
	}

	@Override
	public List<AccountList> OrderByCompany(int userId) {

		List<AccountList> accounts=fetchByUserId(userId);
		Collections.sort(accounts, new CompanyComparatorAsce());
		return accounts;
	}

	@Override
	public List<AccountList> OrderByCountry(int userId,int countryClick) {
		
		List<AccountList> accounts=	fetchByUserId(userId);
		if(countryClick%2==0)
		{
			Collections.sort(accounts, new CountryComparatorAsce());
		}
		else {
			Collections.sort(accounts, new CountryComparatorDesc());
		}
		return accounts;
	}


	public TreeSet<AccountList> sortByTree(int userId,int companyClick)
	{
		List<AccountList> accounts=fetchByUserId(userId);
		TreeSet<AccountList> treeSort;
		if(companyClick%2==0)
		{
			treeSort=new TreeSet<AccountList>(new CompanyComparatorAsce());
		}
		else {
			treeSort=new TreeSet<AccountList>(new CompanyComparatorDesc());
		}
		for(AccountList acc:accounts)
		{
			treeSort.add(acc);
		}
		return treeSort;
	}

}
