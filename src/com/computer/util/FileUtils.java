package com.computer.util;

import java.io.File;
import java.net.URI;
import java.net.URL;

public class FileUtils {
	
	
	public static void main(String[] args) {
		
		URL url=FileUtils.class.getResource("");
	//	FileUtils.class.getResource("").get
		System.out.println(url.toString());
		System.out.println(url.getHost());
		System.out.println(url.getPath().substring(1));
//		url.toURI()
//		File file=new File(uri)
		
		 
	}

}
