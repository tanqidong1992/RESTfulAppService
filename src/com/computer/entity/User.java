/**
 * author:tanqidong
 * create Time:2015年4月27日,下午3:34:22
 * description:
 * fileName:User.java	
 */
package com.computer.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;

import com.colobu.fastjson.FastJsonType;
import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * @author tanqidong
 *
 */
@FastJsonType
@XmlElement
public class User {
	
	@Id(auto=true)
	private Integer id;
	
 
	@Name
	private String userName;
	
	private String password;
	
	/**
	 *用户类型 ldap 0  标准用户1 
	 */
	private Integer userType;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	

}
