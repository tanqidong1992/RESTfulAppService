/**
 * author:tanqidong
 * create Time:2015年4月27日,上午9:05:16
 * description:
 * fileName:CommentInfo.java	
 */
package com.computer.entity;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.mvc.annotation.Attr;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.xml.internal.txw2.annotation.XmlElement;
 
@XmlElement
public class CommentInfo {
	/**
	 * id
	 */
	@Id(auto=true)
	private Integer id;
	/**
	 * 评论时间，其实这里可以不用String，应该用Date类型，好很多
	 */
	private String date;
	/**
	 * 评论内容
	 */
	@ColDefine(type=ColType.TEXT)
	private String content;
	
	private String taoBaoId;
	
	private Integer score;
	
	private Integer operateUserId;
	
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getOperateUserId() {
		return operateUserId;
	}
	public void setOperateUserId(Integer operateUserId) {
		this.operateUserId = operateUserId;
	}
	public String getTaoBaoId() {
		return taoBaoId;
	}
	public void setTaoBaoId(String taoBaoId) {
		this.taoBaoId = taoBaoId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public CommentInfo(String date, String content) {
		super();
		this.date = date;
		this.content = content;
	}
	
	/**
	 * 
	 */
	public CommentInfo() {
		// TODO Auto-generated constructor stub
	}
}
