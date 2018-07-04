package com.greenServices.resource;

import org.springframework.stereotype.Service;

import com.greenServices.bean.ResponseBody;
import com.greenServices.bean.UserBean;

/**
 * 
 * 100 = Success 
 * 101 = Success but invalid credentials
 * 102 = success but invalid body content
 * 301 = failed
 * 302 = failed with internal exception
 * 303 = Filed. Data already exists
 * 
 * @author Harsha
 *
 */

@Service("userResource") 
public class IUserResourceImpl implements IUserResource {


	@Override
	public String printCustomName(String name) {
		System.out.println("In IUserResourceImpl class and printCustomName() method #############");
		System.out.println("Entered Name : " + name);
		
		return "Hello " + name + "!! Welcome to Restful API....";
		
	}
	
	@Override
	public UserBean getUserInfo(int userId) {
		System.out.println("In IUserResourceImpl class and getUserInfo() method *************$$$$$$$$");
				
		UserBean user = new UserBean();
		user.setUserId(userId);
		user.setFirstName("Siddharth");
		user.setLastName("Mannem");
		
		return user;
	}

	@Override
	public String addName(String name) {
		
		System.out.println("In IUserResourceImpl class and addName() method #############");
		System.out.println("POst body String you entered: " + name);		
		return "Hello... " + name;
	}

	@Override
	public ResponseBody addUser(UserBean userbean) {
		
		System.out.println("In IUserResourceImpl and addUser() *** ");
		System.out.println("Request body user firstnmae and Id are : " + userbean.getUserId() + " & " + userbean.getFirstName());
		ResponseBody response = new ResponseBody();
		
		if(userbean.getUserId() == 21 || userbean.getUserId() == 22 || userbean.getUserId() == 23) {
			response.setStatus(100);
			response.setStatusDesc("Success.");
			response.setMessage("New user added into our system...");
		} else {
			response.setStatus(303);
			response.setStatusDesc("Failed!!");
			response.setMessage("Failed to add. Data already exists");
		}
		
		return response;
	}

}
