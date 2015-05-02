package com.computer.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.computer.entity.User;

public class DbTest {
	
	public static void main(String[] args) {
		
		
		DBTools<User> db=new DBTools<User>();
		
		User user=new User();
		//user.setUserName("谭奇栋%");
		//user.setId(3);
		//user.setPassword("123456qwe");
		Map<String,String> map=new HashMap<String,String>();
		
		map.put("userName", "like");
		map.put("id", "=");
		
		List<User> list=db.findObjects(user,map,"id",false);
	//	int result=db.update(user);
	//	list=db.findObjects(user);
		
		for(User u:list)
		{
			System.out.println(u.getId());
		}
	}

}
