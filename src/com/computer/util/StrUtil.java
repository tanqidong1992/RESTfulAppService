 
 
package com.computer.util;

import java.util.regex.Pattern;


 

/**
 * @author tanqidong
 *
 */
public class StrUtil {

	/*
	  * 判断是否为整数 
	  * @param str 传入的字符串 
	  * @return 是整数返回true,否则返回false 
	*/
	  public static boolean isInteger(String str) {  
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	    return pattern.matcher(str).matches();  
	  }

	  /* 
	   * 判断是否为浮点数，包括double和float 
	   * @param str 传入的字符串 
	   * @return 是浮点数返回true,否则返回false 
	 */  
	   public static boolean isDouble(String str) {  
	     Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");  
	     return pattern.matcher(str).matches();  
	   }

	
}
