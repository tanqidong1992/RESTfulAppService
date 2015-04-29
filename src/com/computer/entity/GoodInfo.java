/**
 * author:tanqidong
 * create Time:2015年4月27日,下午3:39:15
 * description:
 * fileName:GoodInfo.java	
 */
package com.computer.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.nutz.dao.entity.annotation.Id;

import com.colobu.fastjson.FastJsonType;
import com.sun.xml.internal.txw2.annotation.XmlElement;

/**
 * @author tanqidong
 *
 */
@FastJsonType
@XmlRootElement
public class GoodInfo {

	@Id(auto=true)
	private Integer id;
	private String taoBaoId;
	private String goodName;
	private Date insertTime;
	private Integer goodTypeId;
	/**
	 * 是否评分 未评分 0  已评 1
	 */
	private int isMarked;
}
