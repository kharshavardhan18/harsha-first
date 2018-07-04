package com.greenServices.resource;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.greenServices.contract.CustomerListContract;
import com.greenServices.contract.LoginFormContract;

@Path("/login")
public interface LoginResource {
	
	@POST
	@Path("/loginForm")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerListContract getCustomerList(LoginFormContract loginFormContract) ;
	
}
