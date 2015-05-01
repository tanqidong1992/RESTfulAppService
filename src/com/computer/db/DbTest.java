package com.computer.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.computer.entity.User;

public class DbTest {
	
	public static void main(String[] args) {
		
		
		DBTools db=new DBTools<User>();
		
		User user=new User();
		user.setUserName("谭奇栋%");
		user.setId(2);
		user.setPassword("123456");
		Map<String,String> map=new HashMap<String,String>();
		
		map.put("userName", "like");
		map.put("id", "=");
		
		List list=db.findObjects(user,map);
		
		list=db.findObjects(user);
	}

}
