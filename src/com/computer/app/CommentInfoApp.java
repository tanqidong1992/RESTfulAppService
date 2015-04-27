/**
 * author:tanqidong
 * create Time:2015年4月27日,下午4:41:49
 * description:
 * fileName:CommentInfoApp.java	
 */
package com.computer.app;

import com.computer.db.DBTools;
import com.computer.entity.CommentInfo;

/**
 * @author tanqidong
 *
 */
public class CommentInfoApp extends BaseApp{
	

	/**
	 * 
	 */
	
	public CommentInfo addComment(CommentInfo ci)
	{
		return	  (CommentInfo) db.insert(ci);
	}
	

}
