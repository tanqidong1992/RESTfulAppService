package com.computer.service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.computer.entity.Absence;
import com.computer.entity.Response;
import com.computer.entity.AState;

@Produces("application/json")
public interface AbsenceService {

	@POST
	@Path("queryall")
	public Response<List<Absence>> queryAllAbsence();
	
	@POST
	@Path("query")
	public Response<Absence> queryAbsence(
			@FormParam("id")Integer id
			);
	
	@POST
	@Path("upd")
	public Response<String> updAbsence(
			@FormParam("tid")Integer tid,
			@FormParam("reason")String reason,
			@FormParam("states")Integer states,
			@FormParam("statemsg")String statemsg,
			@FormParam("reserve")String reserve
			);
	
	@POST
	@Path("del")
	public Response<Boolean>  delAbsence(
			@FormParam("id")Integer id
			);
	@POST
	@Path("add")
	public Response<Absence>  addAbsence(
			@FormParam("uid")Integer uid,
			@FormParam("tid")Integer tid,
			@FormParam("reason")String reason,
			@FormParam("states")Integer states,
			@FormParam("statemsg")String statemsg,
			@FormParam("reserve")String reserve
			);
	 
}
