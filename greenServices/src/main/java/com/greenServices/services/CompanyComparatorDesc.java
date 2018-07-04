package com.greenServices.services;

import java.util.Comparator;

import com.greenServices.bean.AccountList;




public class CompanyComparatorDesc implements Comparator<AccountList> {

	@Override
	public int compare(AccountList acc1, AccountList acc2) {
		if((acc1.getCompany_name().compareTo(acc2.getCompany_name()))>0)
			return -1;
		else if((acc1.getCompany_name().compareTo(acc2.getCompany_name()))<0)
			return 1;
		else 
			return 1;

	}

}
