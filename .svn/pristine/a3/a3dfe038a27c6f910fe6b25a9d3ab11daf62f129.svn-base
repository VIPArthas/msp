package com.wh.util;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.wh.util.base.SerializeUtil;

/**
 * @Title: BaseModel.java
 */
public class BaseModel implements Serializable {

	private static final long serialVersionUID = -4351496918355376145L;

	/**
	 * 当前页
	 */
	protected int currentpage = 1;

	/**
	 * 总记录数
	 */
	private int rscount;

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getRscount() {
		return rscount;
	}

	public void setRscount(int rscount) {
		this.rscount = rscount;
	}
	/**
	 * java自带的toString()方法,获取的是对象类的hashcode,是个内存地址,无意义
	 * 重写类的toString()方法,在查看日志时,便于获取对象类的信息
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/**
	 * 将当期对象转换为json
	 * @return json
	 */
	public String toJson(){
		return SerializeUtil.fastJsonSerialize(this);
	}
	
	
}
