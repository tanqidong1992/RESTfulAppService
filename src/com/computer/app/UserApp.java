package com.computer.app;

import com.computer.entity.User;
import com.computer.util.Log;

public class UserApp extends BaseApp{
	protected Log log=Log.getLog(UserApp.class);
	public UserApp() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 用户登录
	 * @param name 用户名
	 * @param password 用户密码
	 * @return
	 */
	public User login(String name,String password)
	{
		User user=(User) db.findByName(User.class, name);
		
		if(user.getPassword().equals(password))
		{
			return user;
		}
		
		return null;
	}
	
	/**
	 * 用户注册
	 * @param name 用户名
	 * @param password 用户密码
	 * @param userType 用户类型
	 * @return 
	 */
	public User register(String name,String password,Integer userType)
	{
		
		User user=(User) db.findByName(User.class, name);
		if(user!=null)
		{
			log.i("用户名已存在，无法注册");
			return null;
		}
		else
			user=new User();
		
		user.setPassword(password);
		user.setUserName(name);
		user.setUserType(userType);
 
		return (User) db.insert(user);
		
	}
	
	/**
	 * 修改用户密码
	 * @param id 用户id
	 * @param password 新的用户密码
	 * @return 
	 */
	public Integer updatePassword(Integer id,String password,String oldPassword)
	{
		
		User user=(User) db.findById(User.class, id);
		
		if(user!=null && user.getPassword().equals(oldPassword))
		{
			user.setPassword(password);
			int updateResult=db.update(user);
			return  updateResult;
		}
		else
		{
			log.i("用户密码不一致");
		}
 
		return  -1;
	}
	
	public boolean isExistUserName(String name)
	{
		User user=(User) db.findByName(User.class, name);
		
		if(user==null)
			return false;
		else
			return true;
	}
	
	public static void main(String[] args) {
		UserApp ua=new UserApp();
		
		//ua.register("tqd", "123456", 1);
		
		User user=ua.login("tqd", "123456");
		if(user!=null)
		{
			System.out.println(user.getPassword());
		}
		
		ua.updatePassword(user.getId(), "123456Aa", "123456");
		
		
		
		
		
	}
	
}
