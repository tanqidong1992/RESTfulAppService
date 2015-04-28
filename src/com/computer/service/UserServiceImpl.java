package com.computer.service;

import org.springframework.stereotype.Service;

 
import com.computer.entity.Response;
import com.computer.entity.User;
import com.computer.util.Log;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	private static final Class<?> userClass=User.class;
	protected Log log=Log.getLog(userClass);
	
	@Override
	public Response<Integer> updatePassword(Integer id, String password,
			String oldPassword) {
		// TODO Auto-generated method stub
		Response<Integer> resp=new Response<Integer>();
		User user=(User) db.findById(userClass, id);
		
		 if(user==null)
		 {
			 log.i("用户不存在");
			 resp.setOperateResult(false);
			 resp.setObject(-1);
			 resp.setDescription("此用户用户不存在");
			 
			 return resp;
		 }
		
		 if(!user.getPassword().equals(oldPassword))
		 {
			 log.i("旧密码错误");
			 resp.setOperateResult(false);
			 resp.setObject(-1);
			 resp.setDescription("旧密码错误");
			 
			 return resp;
		 }
		 else
		 {
			 user.setPassword(password);
			 
			int updateResult= db.update(user);
			
			if(updateResult<=0)
			{
				 log.i("数据库修改失败");
				 resp.setOperateResult(false);
				 resp.setObject(updateResult);
				 resp.setDescription("服务器异常，请稍后再试");
			}
			else
			{
				 log.i("修改成功");
				 resp.setOperateResult(true);
				 resp.setObject(updateResult);
				 resp.setDescription("修改密码成功");
			}
			
			return resp;
		 }
 
	}

	@Override
	public Response<Boolean> isExistUserName(String name) {
		// TODO Auto-generated method stub
		Response<Boolean> resp=new Response<Boolean>();
		
		User user=(User) db.findByName(userClass, name);
		if(user==null)
		{
			resp.setDescription("不存在此用户");
			resp.setObject(false);
			 
		}
		else
		{
			resp.setDescription("存在此用户");
			resp.setObject(true);
		 
		}
		 return resp;
	}

	@Override
	public Response<User> login(String name, String password, int userType) {
		// TODO Auto-generated method stub
		
		Response<User> resp=new Response<User>();
		
		User user=(User) db.findByName(userClass, name);
		
		if(user==null)
		{
			resp.setDescription("不存在此用户");
			resp.setOperateResult(false);
			
			return resp;
		}
		
		if(user.getPassword().equals(password))
		{
			resp.setDescription("登录成功");
			resp.setObject(user);
			 resp.setOperateResult(true);
			
		}
		else
		{
			resp.setDescription("用户密码错误");
			 
			 resp.setOperateResult(false);
		}
		
		
		return resp;
	}

	@Override
	public Response<User> register(String name, String password,
			Integer userType) {
		Response<User> resp=new Response<User>();
		User user=(User) db.findByName(User.class, name);
		if(user!=null)
		{
			log.i("用户名已存在，无法注册");
			resp.setDescription("用户名已存在，无法注册");
			resp.setOperateResult(false);
			return resp;
		}
		else
			user=new User();
		
		user.setPassword(password);
		user.setUserName(name);
		user.setUserType(userType);
 
		 user= (User) db.insert(user);
		
		 if(user==null)
		 {
			 log.i("数据库插入失败");
			 resp.setDescription("服务器异常，无法注册，请稍后再试");
				resp.setOperateResult(false);
			 
		 }
		 else
		 { 
			 resp.setDescription("注册成功");
			resp.setOperateResult(true);
			 resp.setObject(user);
		 }
		 
		return resp;
	}

}
