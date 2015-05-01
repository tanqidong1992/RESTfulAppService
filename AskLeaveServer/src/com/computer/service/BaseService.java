package com.computer.service;

import com.computer.db.DBTools;
import com.computer.util.Log;

public class BaseService {

	protected DBTools db;
	
	
	public BaseService() {
		// TODO Auto-generated constructor stub
	 db=new DBTools();
	}
}
