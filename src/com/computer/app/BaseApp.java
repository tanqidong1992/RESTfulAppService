package com.computer.app;

import com.computer.db.DBTools;
import com.computer.util.Log;

public class BaseApp {

	protected DBTools db;
	
	
	public BaseApp() {
		// TODO Auto-generated constructor stub
	 db=	new DBTools();
	}
}
