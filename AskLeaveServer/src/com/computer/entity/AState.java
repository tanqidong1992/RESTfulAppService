package com.computer.entity;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;

import com.alibaba.fastjson.FastJsonType;

/**
 * @author sq
 *  id states
 */
@FastJsonType
public class AState {
	/**
	 * id
	 */
	@Id(auto=true)
	private Integer id;
	/**
	 * 审核状态 @ 标签一定要写
	 */
	@Name
	private String states;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public AState() {}
	public AState(String states) {
		super();
		this.states = states;
	}
	
}
