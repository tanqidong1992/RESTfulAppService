package com.computer.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.computer.entity.Response;
import com.computer.entity.User;

@Produces("application/json") 
public interface UserService {

	@POST
	@Path("updatePassword")
	public Response<Integer> updatePassword(
			@FormParam("id")Integer id,
			@FormParam("password")String password,
			@FormParam("oldPassword")String oldPassword);
	@GET
	@Path("isExistUserName")
	public Response<Boolean> isExistUserName(
			@QueryParam("name")String name);
	@POST
	@Path("login")
	public Response<User>  login(
			@FormParam("name")String name,
			@FormParam("password")String password,
			@FormParam("userType")int userType);
	@POST
	@Path("register")
	public Response<User>  register(
			@FormParam("name")String name,
			@FormParam("password")String password,
			@FormParam("userType")Integer userType,
			@FormParam("stunumber")String stunumber,
			@FormParam("tel")String tel,
			@FormParam("sex")String sex,
			@FormParam("reserve")String reserve
			);
	
}
