package com.computer.net;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.computer.util.Log;

public class NewClient {
	
	
	private static final String tag = NewClient.class.getSimpleName();

	public static void testGet(String url)
	{
		 
		 
		CloseableHttpClient client=HttpClients.createDefault();
		HttpGet httpGet=new HttpGet(url);
		
		
		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpEntity httpEntity=response.getEntity();
		StatusLine statusLine=response.getStatusLine();
		int statuesCode=statusLine.getStatusCode();
		if(statuesCode==200)
		{
 
			String result=HttpEntityProcessor.process(httpEntity);
			
			Log.i(tag, result);
		}
		else
		{
			System.out.println("the response status code is "+statuesCode);
		}
		
		
		try {
			response.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		testGet("http://www.baidu.com");
	}

}
