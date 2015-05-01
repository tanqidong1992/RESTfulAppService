/**
 * author:tanqidong
 * create Time:2015年4月27日,上午9:05:16
 * description:
 * fileName:CommentInfo.java	
 */
package com.computer.entity;

import java.sql.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Id;

import com.alibaba.fastjson.FastJsonType;
import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * @author sq id, uid,tid, sdate，edate ，reason,states, statemsg, reqdate reserve
 */
@FastJsonType
@XmlElement
public class Absence {
	/**
	 * id
	 */
	@Id(auto = true)
	private Integer id;
	/**
	 * 用户uid
	 */
	private Integer uid;
	/**
	 * 用户tid
	 */
	private Integer tid;
	/**
	 * 请假开始时间
	 */
	private Date sdate;
	/**
	 * 请假结束时间
	 */
	private Date edate;

	/**
	 * 申请时间
	 */
	private Date reqdate;

	@ColDefine(type = ColType.TEXT)
	private String reason;

	private Integer states;

	/**
	 * 批准状态
	 */
	@ColDefine(type = ColType.TEXT)
	private String statemsg;
	/**
	 * 保留字段
	 */
	private String reserve;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Date getReqdate() {
		return reqdate;
	}

	public void setReqdate(Date reqdate) {
		this.reqdate = reqdate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getStates() {
		return states;
	}

	public void setStates(Integer states) {
		this.states = states;
	}

	public String getStatemsg() {
		return statemsg;
	}

	public void setStatemsg(String statemsg) {
		this.statemsg = statemsg;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public Absence(Integer uid, Integer tid, Date sdate, Date edate,
			Date reqdate, String reason, Integer states, String statemsg,
			String reserve) {
		super();
		this.uid = uid;
		this.tid = tid;
		this.sdate = sdate;
		this.edate = edate;
		this.reqdate = reqdate;
		this.reason = reason;
		this.states = states;
		this.statemsg = statemsg;
		this.reserve = reserve;
	}

	/**
	 * 
	 */
	public Absence() {
		// TODO Auto-generated constructor stub
	}
}
