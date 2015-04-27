/**
 * author:tanqidong
 * create Time:2015��4��27��,����3:45:34
 * description:
 * fileName:PropertyUtils.java	
 */
package com.computer.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * @author tanqidong
 *
 */
public class PropertyUtils {

	private static Log log=Log.getLog(PropertyUtils.class);
	private static final String defaultPath="db.properties";
	public static void main(String[] args) throws IOException {
		
		putProperties("test2", "gg");
		Properties p=loadProperties();
	}
	
	public static void putProperties(String key,String value)
	{
		log.i("store key :"+key);
		Properties properties=loadProperties();
 
		properties.put(key, value);
	 
		FileWriter writer = null;
		try {
			writer = new FileWriter(defaultPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.i(e.getMessage());
			e.printStackTrace();
		}
		if(writer==null)
			log.i("open file "+defaultPath+" error");
		try {
			properties.store(writer, "update in "+System.currentTimeMillis());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.i(e.getMessage());
			e.printStackTrace();
		}
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.i(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	public static  Properties loadProperties()
	{
		log.i("start to load properties");
		Properties p=new Properties();
		Reader inStream = null;
		try {
			inStream = new FileReader(defaultPath);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(inStream==null)
		{
			log.i("read file "+defaultPath+" fail");
		}
		try {
			p.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}

	/**
	 * @param p
	 */
	public static void save(Properties p) {
		// TODO Auto-generated method stub
		FileWriter writer = null;
		try {
			writer = new FileWriter(defaultPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.i(e.getMessage());
			e.printStackTrace();
		}
		if(writer==null)
			log.i("open file "+defaultPath+" error");
		try {
			p.store(writer, "update in "+System.currentTimeMillis());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.i(e.getMessage());
			e.printStackTrace();
		}
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.i(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}
