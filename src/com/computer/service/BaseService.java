package com.computer.service;

import com.computer.db.DBTools;
import com.computer.util.Log;

public class BaseService {

	protected DBTools db;
	
	
	public BaseService() {
		// TODO Auto-generated constructor stub
	 
	}
	

	public DBTools getDb() {
		return db;
	}


	public void setDb(DBTools db) {
		this.db = db;
	}
	
	
}
