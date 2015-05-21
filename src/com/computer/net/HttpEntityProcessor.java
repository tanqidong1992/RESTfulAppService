package com.computer.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.Header;




import com.computer.util.Log;

public class HttpEntityProcessor {
	
	
	private static final String tag = HttpEntityProcessor.class.getSimpleName();


	public HttpEntityProcessor() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static String process(HttpEntity entity)
	{
		
		
		Header encoding=entity.getContentEncoding();
		entity.getContentType();
		entity.getContentLength();
		
		InputStream is = null;
		try {
			is = entity.getContent();
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(is==null)
		{
			Log.i(tag, "read stream fail");
			
		}
		
		byte[] data=readStream(is);
	
		String charSet=null;
		if(encoding!=null)
		 charSet=encoding.getValue();
		
		 String text = null;
		try {
			if(charSet!=null)
			text = new String(data,charSet);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		if(text==null)
			text=new String(data);
		
		 return text;
	}
	

	/**
	 * @param is
	 * @return
	 * @throws IOException ��д��
	 * @Description:
	 */
	private static byte[] readStream(InputStream is) 
	{
		int tmp=0;
		byte[] buffer=new byte[1024];
		
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		
		
		try {
			while((tmp=is.read(buffer))!=-1)
			{
				baos.write(buffer, 0, tmp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return baos.toByteArray();
	}
	

}
