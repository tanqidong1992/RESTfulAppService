/**
 * author:tanqidong
 * create Time:2015��4��27��,����3:55:59
 * description:
 * fileName:Log.java	
 */
package com.computer.util;

/**
 * @author tanqidong
 *
 */
public class Log {
	
	/**
	 * @param tag 
	 * 
	 */
	protected Log(String tag) {
		// TODO Auto-generated constructor stub
		defaultTag=tag;
	}
	private  String defaultTag = "Log";
	
	public  void i(String ss)
	{
		i(defaultTag,ss);
	}
	
	public static void i(String tag,String ss)
	{
		System.out.println(tag+": "+ss);
	}
	
	public static Log getLog(Class<?> cls)
	{
		return new Log(cls.getSimpleName());
		
	}

	public void error(String string) {
		// TODO Auto-generated method stub
		
		i(string);
		
	}
}
