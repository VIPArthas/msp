package com.wh.mspentity;

import java.util.Date;

import com.wh.util.BaseModel;

/**
 * jdbc 测试用 user类
 * 
 * @author Leo
 *
 */
public class User extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer user_id;

	private String user_name;

	private String user_password;

	private Date user_birth;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public Date getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(Date user_birth) {
		this.user_birth = user_birth;
	}
	
	
	
	

	public User() {
		super();
	}

	public User(Integer user_id, String user_name, String user_password, Date user_birth) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_birth = user_birth;
	}
	
	

}
