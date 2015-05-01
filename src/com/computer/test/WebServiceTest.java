package com.computer.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.computer.entity.*;

import org.apache.cxf.transport.jms.util.TestReceiver;
import org.apache.http.client.ClientProtocolException;
 


import org.mozilla.intl.chardet.nsDetector;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.computer.net.Client;

public class WebServiceTest {
	
	static String url="http://localhost:8080/GrabDataFromTaobao/user/";
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
//		此时ss是UTF-16编码，
		String ss="我是谭奇栋";
//		将此字符串转化为GBK格式的字节流，再实例化成GBK格式的字符串，此时ss1的编码格式为GBK
		String ss1=new String(ss.getBytes("GBK"),"GBK");
		System.out.println(ss1);
//		输出的乱码，getBytes()，默认是UTF-8编码，获取到的是UTF-8的字节流，再用GBK的编码读取字节流，出现乱码
		System.out.println("-->"+new String(ss1.getBytes(),"GBK"));
		
		System.out.println("-->"+new String(ss1.getBytes("UTF-16"),"GBK"));
		String ss2=new String(ss1.getBytes("GBK"),"GBK");
		System.out.println(ss2);
		testRegister();
		testlogin();
		//testUserExist();
//	
		
	}
	public static void testRegister() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
 		params.put("name", "tqd");
 	 	params.put("password", "123456");
 	 	params.put("userType", "1");
 		String ss=Client.sendPost(url+"register", params);
 		
 		
 		System.out.println(ss);
	}
	public static void testlogin() throws ClientProtocolException, IOException
	{
		Map<String, String> params=new HashMap<String, String>();
		params.put("name", "tqd");
	 	params.put("password", "123456");
	 	params.put("userType", "1");
		String ss=Client.sendPost(url+"login", params);
		
		
		
		System.out.println(ss);
		 
		Response res=JSONObject.parseObject(ss, Response.class);
		 
	 
		//JSONObject.getObject(ss, Response<User>.class);
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
