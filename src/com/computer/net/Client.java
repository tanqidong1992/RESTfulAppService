package com.computer.net; 

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class Client {
	public static HttpClient httpClient=new DefaultHttpClient();
	private static Cookie mCookie=null;
	private static CookieStore mCookieStore=null;
	protected static HttpClient getHttpClient()
	{
		if(httpClient==null)
		{
			httpClient=new DefaultHttpClient();
			httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BEST_MATCH);
		 
		//	User-Agent	Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; .NET4.0E; Shuame)
			httpClient.getParams().setParameter("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; .NET4.0E; Shuame)");
			/*
			 // ��֤������
		    // ��������Ϲд�ģ������ʵ�������д
			 httpClient.getCredentialsProvider().setCredentials(new AuthScope("10.60.8.20", 8080),
		        new UsernamePasswordCredentials("username", "password"));
		    // ���ʵ�Ŀ��վ�㣬�˿ں�Э��
		    HttpHost targetHost = new HttpHost("www.google.com", 443, "https");
		    // ���������?
		    HttpHost proxy = new HttpHost("10.60.8.20", 8080);
		    httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		    // Ŀ����?
		    HttpGet httpget = new HttpGet("/adsense/login/zh_CN/?");
		    System.out.println("Ŀ��: " + targetHost);
		    System.out.println("����: " + httpget.getRequestLine());
		    System.out.println("����: " + proxy);
		    */
		}
		return httpClient;
	}
	public static byte[] sendGet_byte(String url)
	{
		getHttpClient();
		byte[] data=null;
		 httpClient=new DefaultHttpClient();
		HttpGet httpGet=new HttpGet(url);
		InputStream is=null;
		HttpContext httpContext=new BasicHttpContext();
		if(mCookieStore==null)
		{
			mCookieStore=new BasicCookieStore();
		}
		httpContext.setAttribute(ClientContext.COOKIE_STORE, mCookieStore);
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpGet,httpContext);
			HttpEntity httpEntity=httpResponse.getEntity();
			
			if(httpEntity!=null)
			{
				is=httpEntity.getContent();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(is!=null)
		{
			data=readStream(is);
		}
		
		return data;
		
		
		
	}
	public static String sendGet(String url) throws ClientProtocolException, IOException{
		getHttpClient();
		String result = null;
		 httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		InputStream in = null;
		
		HttpContext context = new BasicHttpContext();
		if(mCookieStore==null)
	     mCookieStore = new BasicCookieStore();
	    context.setAttribute(ClientContext.COOKIE_STORE, mCookieStore); 
		try {
		//	long t1=System.currentTimeMillis();
			HttpResponse response = httpClient.execute(get,context);
		//	System.out.println(System.currentTimeMillis()-t1);
			  List<Cookie> cookies = mCookieStore.getCookies();
			   if (!cookies.isEmpty()) {
			         for (int i = cookies.size(); i > 0; i --) {
							Cookie cookie = cookies.get(i - 1);
							         
							if (cookie.getName().equalsIgnoreCase("IRSSessionID")) 
							{
								mCookie=cookie;
									System.out.println(cookie.getValue());
							}
			                                               
			
			         }
			   }
			
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				entity = new BufferedHttpEntity(entity);
				in = entity.getContent();
				byte[] read = new byte[1024];
				byte[] all = new byte[0];
				int num;
				while ((num = in.read(read)) > 0) {
					byte[] temp = new byte[all.length + num];
					System.arraycopy(all, 0, temp, 0, all.length);
					System.arraycopy(read, 0, temp, all.length, num);
					all = temp;
				}
				result = new String(all, "UTF-8");
			}
		} finally {
			if (in != null) in.close();
			get.abort();
		}
		
		return result;
	}
	public static String sendGet(String url,String charset) throws ClientProtocolException, IOException{
		getHttpClient();
		String result = null;
		 httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		InputStream in = null;
		
		HttpContext context = new BasicHttpContext();
		if(mCookieStore==null)
	     mCookieStore = new BasicCookieStore();
	    context.setAttribute(ClientContext.COOKIE_STORE, mCookieStore); 
		try {
		//	long t1=System.currentTimeMillis();
			HttpResponse response = httpClient.execute(get,context);
		//	System.out.println(System.currentTimeMillis()-t1);
			  List<Cookie> cookies = mCookieStore.getCookies();
			   if (!cookies.isEmpty()) {
			         for (int i = cookies.size(); i > 0; i --) {
							Cookie cookie = cookies.get(i - 1);
							         
							if (cookie.getName().equalsIgnoreCase("IRSSessionID")) 
							{
								mCookie=cookie;
									System.out.println(cookie.getValue());
							}
			                                               
			
			         }
			   }
			
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				entity = new BufferedHttpEntity(entity);
				in = entity.getContent();
				byte[] read = new byte[1024];
				byte[] all = new byte[0];
				int num;
				while ((num = in.read(read)) > 0) {
					byte[] temp = new byte[all.length + num];
					System.arraycopy(all, 0, temp, 0, all.length);
					System.arraycopy(read, 0, temp, all.length, num);
					all = temp;
				}
				result = new String(all, charset);
			}
		} finally {
			if (in != null) in.close();
			get.abort();
		}
		
		return result;
	}
	
	public static String sendPost(String url, Map<String, String> params) throws ClientProtocolException, IOException{
		
		
		String result = null;
		
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpPost get = new HttpPost(url);
		
		// �����������б�  
		List<NameValuePair> qparams = new ArrayList<NameValuePair>(); 
		Set<String> keys = params.keySet();
		for (String key : keys) {
			qparams.add(new BasicNameValuePair(key, params.get(key)));
		}
		
		// ����  
		get.setEntity(new UrlEncodedFormEntity(qparams,"GBK"));
		
		HttpContext httpContext=new BasicHttpContext();
		if(mCookieStore==null)
	     mCookieStore = new BasicCookieStore();
	//	mCookieStore.addCookie(mCookie);
	    httpContext.setAttribute(ClientContext.COOKIE_STORE, mCookieStore); 
	    
	      
		
		HttpResponse response = httpClient.execute(get,httpContext);
		HttpEntity entity = response.getEntity();
		
		
		if (entity != null) {
			entity = new BufferedHttpEntity(entity);
			
			InputStream in = entity.getContent();
		
			//byte[] all=	readStream(in);
			
			byte[] read = new byte[1024];
			byte[] all = new byte[0];
			int num;
			while ((num = in.read(read)) > 0) {
				byte[] temp = new byte[all.length + num];
				System.arraycopy(all, 0, temp, 0, all.length);
				System.arraycopy(read, 0, temp, all.length, num);
				all = temp;
			}
			
			result = new String(all,"UTF-8");
			if (null != in) {
				in.close();
			}
		}
		get.abort();
		
		return result;
	}
	
	
public static String sendPost(String url, Map<String, String> params,String charset) throws ClientProtocolException, IOException{
		
		
		String result = null;
		
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpPost get = new HttpPost(url);
		
		// �����������б�  
		List<NameValuePair> qparams = new ArrayList<NameValuePair>(); 
		
		if(params!=null)
			
		{
			Set<String> keys = params.keySet();
			for (String key : keys) {
				qparams.add(new BasicNameValuePair(key, params.get(key)));
			}
		}
		// ����  
		get.setEntity(new UrlEncodedFormEntity(qparams,charset));
		//get.addHeader("Content-Type", "application/json; charset=UTF-8");
		HttpContext httpContext=new BasicHttpContext();
		if(mCookieStore==null)
	     mCookieStore = new BasicCookieStore();
	//	mCookieStore.addCookie(mCookie);
	    httpContext.setAttribute(ClientContext.COOKIE_STORE, mCookieStore); 
	    
	      
		
		HttpResponse response = httpClient.execute(get,httpContext);
		HttpEntity entity = response.getEntity();
		
		
		if (entity != null) {
			entity = new BufferedHttpEntity(entity);
			
			InputStream in = entity.getContent();
		
			//byte[] all=	readStream(in);
			
			byte[] read = new byte[1024];
			byte[] all = new byte[0];
			int num;
			while ((num = in.read(read)) > 0) {
				byte[] temp = new byte[all.length + num];
				System.arraycopy(all, 0, temp, 0, all.length);
				System.arraycopy(read, 0, temp, all.length, num);
				all = temp;
			}
			
			result = new String(all,charset);
			if (null != in) {
				in.close();
			}
		}
		get.abort();
		
		return result;
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
	
	
	
	
	
	
	public static   String sendGet(String url, Map<String, String> params) throws ClientProtocolException, IOException {
		Set<String> keys = params.keySet();
		StringBuilder urlBuilder = new StringBuilder(url + "?");
		for (String key : keys) {
			urlBuilder.append(key).append("=").append(params.get(key)).append("&");
		}
		urlBuilder.delete(urlBuilder.length() - 1, urlBuilder.length());
		return  sendGet(urlBuilder.toString());
	}
	
	/*
	  public static HttpClient getHttpClient(Context paramContext)
	  {
	    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
	    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 50000);
	    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 200000);
	    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
	    if (!((WifiManager)paramContext.getSystemService("wifi")).isWifiEnabled())
	    {
	      Uri localUri = Uri.parse("content://telephony/carriers/preferapn");
	      Cursor localCursor = paramContext.getContentResolver().query(localUri, null, null, null, null);
	      if ((localCursor != null) && (localCursor.moveToFirst()))
	      {
	        String str = localCursor.getString(localCursor.getColumnIndex("proxy"));
	        if ((str != null) && (str.trim().length() > 0))
	        {
	          HttpHost localHttpHost = new HttpHost(str, 80);
	          localDefaultHttpClient.getParams().setParameter("http.route.default-proxy", localHttpHost);
	        }
	        localCursor.close();
	      }
	    }
	    return localDefaultHttpClient;
	  }
	  */
}