package com.greenServices.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.greenServices.bean.ResponseBody;
import com.greenServices.bean.UserBean;

@Path("/secAcct")
public interface IUserResource {

	// @GET call with URL params
	// http://localhost:8080/greenServices/services/secAcct/printName/{name}
	@GET
	@Path("printName/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String printCustomName(@PathParam("name") String name);
	
	// http://localhost:8080/greenServices/services/userInfo/{userId}
	@GET
	@Path("userInfo/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBean getUserInfo(@PathParam("userId") int userId);

	
	// Simple POST call
	// http://localhost:8080/greenServices/services/secAcct/getName
	@POST
	@Path("getName")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String addName(String name);
	
	// http://localhost:8080/greenServices/services/secAcct/addUser
	@POST
	@Path("addUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody addUser(UserBean name);
	
}
