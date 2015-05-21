package com.computer.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import com.computer.entity.Response;
@Path("file")
@Produces("application/json;charset=utf-8")
public interface FileService {
	
	@Path("/upload")
	@POST
	public Response<Integer> uploadFile(
			@Multipart(value="file")
			Attachment image,@Multipart(value="identifier")
			Attachment text);

}
