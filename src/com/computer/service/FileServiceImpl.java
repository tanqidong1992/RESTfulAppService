package com.computer.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.stereotype.Service;

import com.computer.entity.Response;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public Response<Integer> uploadFile(Attachment image, Attachment identifier) {
		// TODO Auto-generated method stub
		
		Response<Integer> resp=new Response<Integer>();
		
		InputStream is=null;
		try {
			is = image.getDataHandler().getInputStream();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		 int temp=0;
		 
		 byte []buffer=new byte[1024];
		 try {
			while((temp=is.read(buffer))!=-1)
			 {
				 baos.write(buffer, 0, temp);
				 
			 }
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(baos.size());
		return resp;
	}
	
	
}
