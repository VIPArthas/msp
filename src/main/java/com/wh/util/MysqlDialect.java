package com.wh.util;

/**
 * @Title:			MysqlDialect.java
 * @Description: 	TODO
 */
public class MysqlDialect implements Dialect {

	public String getLimitString(String sql, boolean hasOffset) {
		// TODO Auto-generated method stub
		return new StringBuffer(sql.length() + 150).append(
				"select * from (select a.*,rownum row_num from (").append(
				trim(sql)).append(
				hasOffset ? ") a ) b where b.row_num > ? and b.row_num<= ?"
						: "where b.row_num <= ?").toString();
	}

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		// TODO Auto-generated method stub
		sql = trim(sql);
		StringBuffer sb = new StringBuffer(sql.length() + 50);
		sb.append(sql);  
		sb.append(" limit " + offset + "," + limit);  
	    return sb.toString();  
	}

	@Override
	public String getLimitTotal(String sql) {
		sql = trim(sql);
		String sqlbus = trim(sql.toLowerCase());
		StringBuffer sb = new StringBuffer(sql.length()+100);
		sb.append("select count(1) ");
		if (sqlbus.lastIndexOf("order")==-1) {
			sb.append(sql.substring(sqlbus.indexOf("from")));
		}else{
			sb.append(sql.substring(sqlbus.indexOf("from"),sqlbus.lastIndexOf("order")).trim());
		}
		
		return sb.toString();
	}

    public static void main (String [] args){
    	MysqlDialect m = new MysqlDialect();
    	System.out.println(m.getLimitTotal("select * from t_user a  where id = '20' order by id "));
    }
	@Override
	public boolean supportsLimit() {
		return true;
	}

	private String trim(String sql) {
		sql = sql.trim();
		return sql;
	}}
