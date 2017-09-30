package com.wh.util;

/**
 * @Title:			Dialect.java
 * @Description: 	TODO
 */
public interface Dialect {
	public static enum Type{
		MYSQL,
		SQLSERVER,
		ORACLE
	}
	public boolean supportsLimit();

	/**
	 * 拼接获取记录总数的SQL语句的方法
	 * */
	public String getLimitTotal(String sql);
	/**
	 * 拼接获取分页结果集的SQL语句的方法
	 * */
	public String getLimitString(String sql, boolean hasOffset);
	/**
	 * 拼接获取分页结果集的SQL语句的方法
	 * */
	public String getLimitString(String sql, int offset, int limit);
}
