package com.computer.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.transport.jms.util.TestReceiver;
import org.apache.http.client.ClientProtocolException;
 


import com.computer.net.Client;

public class WebServiceTest {
	
	static String url="http://localhost:8080/GrabDataFromTaobao/user/";
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		
		
 		Map<String, String> params=new HashMap<String, String>();
 		params.put("name", "谭奇栋");
 	 	params.put("password", "123456");
 	 	params.put("userType", "1");
 		String ss=Client.sendPost(url+"register", params,"GBK");
 		
 		
 		System.out.println(ss);
		
//	
		
	}
	
	public static void testlogin() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("name", "tqd");
	 	params.put("password", "123456");
	 	params.put("userType", "1");
		String ss=Client.sendPost(url+"login", params);
		System.out.println(ss);
	}
	
	
	public static void testUserExist() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("name", "tqd");
	//	params.put("password", "123456");
		String ss=Client.sendGet(url+"isExistUserName", params);
		System.out.println(ss);
	}

}
