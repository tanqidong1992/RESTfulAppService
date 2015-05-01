package com.computer.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
 

import com.computer.net.Client;

public class StateWebServiceTest {
	
	static String url="http://localhost:8080/AskLeaveServer/state/";
	public static void main(String[] args) throws ClientProtocolException, IOException {
//		testAddState();
		testAddState();
	}
	//ok
	public static void testAddState() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("states", "驳回");
		String ss=Client.sendPost(url+"addstate", params);
		System.out.println(ss);
	}
	
	//ok
	public static void TestDelstate() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("id", "5");
		String ss=Client.sendPost(url+"delstate", params);
		System.out.println(ss);
	}
	//ok
	public static void queryState() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("id", "1");
		String ss=Client.sendPost(url+"querystate", params);
		System.out.println(ss);
	}
	
	public static void queryAllState() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
//		params.put("name", "sunquan");
		String ss=Client.sendPost(url+"queryallstate", params);
		System.out.println(ss);
	}

}
