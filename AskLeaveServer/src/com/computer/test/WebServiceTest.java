package com.computer.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
 

import com.computer.net.Client;

public class WebServiceTest {
	
	static String url="http://172.28.27.26:8088/AskLeaveServer/user/";
	public static void main(String[] args) throws ClientProtocolException, IOException {
		 TestRegister();
		//  testlogin();
	}
	
	public static void testlogin() throws ClientProtocolException, IOException 
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("name", "12");
	 	params.put("password", "123456");
	 	params.put("userType", "0");
		String ss=Client.sendPost(url+"login", params);
		System.out.println(ss);
	}
	public static void TestRegister() throws ClientProtocolException, IOException 
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("name", "sunquan");
	 	params.put("password", "123456");
	 	params.put("userType", "0");
	 	params.put("stunumber", "201141842121");
	 	params.put("tel", "18207302292");
	 	params.put("sex", "男");
	 	params.put("reserve", "");
		String ss=Client.sendPost(url+"register", params);
		System.out.println(ss);
	}
	
	public static void testUserExist() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("name", "sunquan");
		String ss=Client.sendGet(url+"isExistUserName", params);
		System.out.println(ss);
	}

}
