/**
 * creator:谭锟芥栋
 * Date: 2014-11-14 
 * Descritiom:
 * Department: 
 *
 *
 *
 */
package com.computer.db;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

 



import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.util.cri.SqlExpression;

import com.computer.entity.CommentInfo;
import com.computer.entity.GoodInfo;
import com.computer.entity.User;
import com.computer.util.Log;
import com.computer.util.PropertyUtils;

 

/**
 * @author tanqidong
 *
 */
public class DBTools <T> {
	
	private static Log log=Log.getLog(PropertyUtils.class);
 
 
	private static final String tag ="MyDataSource";
	private static final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/commentdb?useUnicode=true&characterEncoding=UTF-8";
	private static String user="root";
	private static String password="123456Aa";
	private static String driverClassName="com.mysql.jdbc.Driver";
	private static final String urlKey="db.url";
	private static final String userKey="db.user";
	private static final String passwordKey="db.password";
	private static final String driverClassNameKey="db.driverClassName";
	
	
	private static NutDao mNutDao;
	private static  DataSource getSimpleDataSource()
	{
		SimpleDataSource sds=new SimpleDataSource();
		
		try {
			sds.setDriverClassName(driverClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.i(e.getMessage());
			e.printStackTrace();
		}
		
		sds.setJdbcUrl(url);
		sds.setPassword(password);
		sds.setUsername(user);
		
		return sds;
	}
	
 
	
	private  static  void createTable(Class<?> cls,boolean dropIfexist)
	{
		log.i("create table "+cls.getSimpleName());
		mNutDao.create(cls, dropIfexist);
	}
	
	public static void main(String[] args) {
	 
	}
	
	public List<T> findAll(Class<T> cls)
	{
		
		log.i("find all in table "+cls.getSimpleName());
		List  list=mNutDao.query(cls, null);
		 
		return list;
	}
	
	public T findById(Class<T> cls,Integer id)
	{
		log.i("findById in table "+cls.getSimpleName()+" with id "+id);
		
		T object=mNutDao.fetch(cls, id);
		
		return object;
	}
	
	public T findByName(Class<T> cls,String name)
	{
		log.i("findById in table "+cls.getSimpleName()+" with name "+name);
		
		T object=mNutDao.fetch(cls, name);
		
		return object;
	}
	
	public int update(T object)
	{
		log.i("updateById in table "+object.getClass().getSimpleName());
	
		int result=mNutDao.updateIgnoreNull(object);
		return result;
		
		
	}
	
	public int deleteById(Class<T> cls,Integer id)
	{
		log.i("deleteById in table "+cls.getSimpleName()+" with id "+id);
	
		int result=mNutDao.delete(cls, id);
		return result;
		
		
	}
	
	public T insert(T o)
	{
		log.i("insert in table "+o.getClass().getSimpleName());
		o=mNutDao.insert(o);
		return o;
	}
	
	static Class<?>[] tables=new Class<?>[]{CommentInfo.class,User.class,GoodInfo.class};
	static{
		Properties p=PropertyUtils.loadProperties();
		
//		初始化数据库连接
		
		if(p.containsKey(urlKey))
		{
			
			url=(String) p.getProperty(urlKey);
			
		}else
		{
			p.put(urlKey, url);
		}
		
		if(p.containsKey(driverClassNameKey))
		{
			driverClassName=p.getProperty(driverClassNameKey);
		}
		else
		{
			p.put(driverClassNameKey, driverClassName);
		}
		
		
		if(p.containsKey(userKey))
		{
			user=p.getProperty(userKey);
		}
		else
		{
			p.put(userKey, user);
		}
		
		if(p.containsKey(passwordKey))
		{
			password=p.getProperty(passwordKey);
		}
		else
		{
			p.put(passwordKey, password);
		}
		
		
		
		
		
//		初始化表
		
		mNutDao=new NutDao(getSimpleDataSource());
		
		
		for(int i=0;i<tables.length;i++)
		{
			String tableName=tables[i].getSimpleName();
			if(p.containsKey(tableName))
			{
				String isTableCreated=p.getProperty(tableName);
				
				if(isTableCreated.equals("true"))
					log.i("table "+tableName+" is alrealy created");
				else
				{
					log.i("create table "+tableName);
					Entity e=mNutDao.create(tables[i], true);
					
					if(e!=null)
					{
						p.put(tableName, "true");
					}
					else
					{
						log.i("create table "+tableName+" fail");
					}
				}
			}
			else
			{
				log.i("create table "+tableName);
				Entity e=mNutDao.create(tables[i], true);
				
				if(e!=null)
				{
					p.put(tableName, "true");
				}
				else
				{
					log.i("create table "+tableName+" fail");
				}
			}
			
			
		}
		
		PropertyUtils.save(p);
	}
	
	/**
	 * 多条件查询，
	 * 传入的对象不为NULL的字段都是查询条件 ，为NULL的字段就会忽略
	 * @param o
	 * @return
	 */
	public List  findObject(T o)
	{
		Field[] fields=o.getClass().getDeclaredFields();
		Cnd con=null;
		 
		
		for(int i=0;i<fields.length;i++)
		{
			fields[i].setAccessible(true);
			
			Object fieldValue=null;
			try {
				fieldValue = fields[i].get(o);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(fieldValue!=null)
			{
				if(con==null)
				
					con=Cnd.where(fields[i].getName(), "=", fieldValue);
				else
					con.and(fields[i].getName(), "=", fieldValue);
				
			}
		}
		log.i(con.toString());
	return	  mNutDao.query(o.getClass(), con);
		
	 
		
	}
	
	
	/**
	 * 多条件查询，
	 * 
	 * @param o 传入的对象不为NULL的字段都是查询条件 ，为NULL的字段就会忽略,
	 * @param operations  查询条件的比较形式 主要有 =, <, <,LIKE等，
	 * LIKE模糊查询的话，自己在字段的值赋值时注意哦，不给条件，默认为=
	 * Map<String,String>  key 为字段
	 * @return
	 */
	public List  findObject(T o,Map<String,String> operations)
	{
		Field[] fields=o.getClass().getDeclaredFields();
		Cnd con=null;
		 
		
		for(int i=0;i<fields.length;i++)
		{
			fields[i].setAccessible(true);
			String fieldName=fields[i].getName();
			Object fieldValue=null;
			try {
				fieldValue = fields[i].get(o);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(fieldValue!=null)
			{
				if(con==null)
				
					con=Cnd.where(fieldName,
							operations.get(fieldName)==null?"=":operations.get(fieldName),
							fieldValue);
				else
					con.and(fieldName,
							operations.get(fieldName)==null?"=":operations.get(fieldName),
							fieldValue);
				
			}
		}
		log.i(con.toString());
	return	  mNutDao.query(o.getClass(), con);
		
	 
		
	}
	
	
	private static final String isTableCreated="true";
	
}
