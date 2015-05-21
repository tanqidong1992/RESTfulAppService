package com.computer.test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.computer.entity.*;

import org.apache.cxf.transport.jms.util.TestReceiver;
import org.apache.http.client.ClientProtocolException;
 


import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.mozilla.intl.chardet.nsDetector;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.computer.net.Client;

public class WebServiceTest {
	
	static String url="http://localhost:8080/GrabDataFromTaobao/user/";
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
/*//		此时ss是UTF-16编码，
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
*/		testFileUpload();
	}
	
	
	private static void testFileUpload() {
		// TODO Auto-generated method stub
		String url123="http://localhost:8080/GraduationDesigned/image/addSample";
		MultipartEntityBuilder meb=MultipartEntityBuilder.create();
		meb.build();
		
		MultipartEntity mpe=new MultipartEntity();
		File file=new File("orange1.jpg");
		FileBody fb=new FileBody(file);
		mpe.addPart("image", fb);
		StringBody sb = null;
		try {
			sb = new StringBody("123");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			  
		mpe.addPart("identifier", sb);
		
		
		StringBody sb1 = null;
		try {
			sb1 = new StringBody("123");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			  
		mpe.addPart("fileSize", sb1);
		
		HttpPost hp=new HttpPost(url123);
		hp.setEntity(mpe);
		try {
			Client.getHttpClient().execute(hp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
