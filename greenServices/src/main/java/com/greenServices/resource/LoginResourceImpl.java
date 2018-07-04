package com.greenServices.resource;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenServices.bean.CustomerList;
import com.greenServices.bean.Keyword;
import com.greenServices.bean.LoginForm;
import com.greenServices.contract.CustomerListContract;
import com.greenServices.contract.LoginFormContract;
import com.greenServices.services.CustomerService;


@Service("loginResourceImpl")
public class LoginResourceImpl implements LoginResource {
	
	
	 
	 @Autowired
		CustomerService customerService;

	@Override
	public CustomerListContract getCustomerList(LoginFormContract loginFormContract)  {
		
	System.out.println("in LoginResource class***");
		CustomerListContract customerList=customerService.isPresent(loginFormContract.getUsername(), loginFormContract.getPassword());
		System.out.println("CustomerListContract details:"+customerList.getFirstname()+" "+customerList.getLastname());
		return customerList;
	}
	

}
	
