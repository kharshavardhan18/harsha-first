package com.greenServices.services;

import java.util.Comparator;

import com.greenServices.bean.AccountList;





public class CountryComparatorDesc implements Comparator<AccountList> {

	@Override
	public int compare(AccountList acc1, AccountList acc2) {
		
		return -(acc1.getCountry().compareToIgnoreCase(acc2.getCountry()));
		
	}

	
}
