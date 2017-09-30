package com.wh.util.msp;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.wh.entity.User;

/**
 * 
 * JDBC工具类，回去数据库连接和释放连接
 */
public class JdbcUtils {
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	// 加载驱动，获取数据库连接信息
	static {
		try {
			// 加载配置文件
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
			Properties properties = new Properties();
			properties.load(in);
			driver = properties.getProperty("driverClass2");
			url = properties.getProperty("jdbcUrl2");
			username = properties.getProperty("user2");
			password = properties.getProperty("password2");
			// 加载驱动
			Class.forName(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 获取数据库连接
	 * 
	 * @throws SQLException
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 释放资源
	 * 
	 * @param connection
	 * @param preparedStatement
	 * @param resultSet
	 */
	public static void releaseDB(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将结果集转换成实体对象集合
	 * 
	 * @param res
	 *            结果集
	 * @param c
	 *            实体对象映射类
	 * @return
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static List rsToClass(ResultSet rs, Class cc)
			throws SQLException, InstantiationException, IllegalAccessException {

		// 结果集 中列的名称和类型的信息
		ResultSetMetaData rsm = rs.getMetaData();
		int colNumber = rsm.getColumnCount();
		List list = new ArrayList();
		Field[] fields = cc.getDeclaredFields();

		// 遍历每条记录
		while (rs.next()) {
			// 实例化对象
			Object obj = cc.newInstance();
			// 取出每一个字段进行赋值
			for (int i = 1; i <= colNumber; i++) {
				Object value = rs.getObject(i);
				// 匹配实体类中对应的属性
				for (int j = 0; j < fields.length; j++) {
					Field f = fields[j];
					if (f.getName().equals(rsm.getColumnName(i))) {
						boolean flag = f.isAccessible();
						// 字段是否私有
						f.setAccessible(true);
						f.set(obj, value);
						f.setAccessible(flag);
						break;
					}
				}

			}
			list.add(obj);
		}
		return list;
	}

	public static List<Map<String, Object>> rsToMap(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException {

		// 结果集 中列的名称和类型的信息
		ResultSetMetaData rsm = rs.getMetaData();
		int colNumber = rsm.getColumnCount();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 遍历每条记录
		while (rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 实例化对象
			// 取出每一个字段进行赋值
			for (int i = 1; i <= colNumber; i++) {
				String key = rsm.getColumnName(i);
				Object value = rs.getObject(i);
				map.put(key, value);

			}
			list.add(map);
		}
		return list;
	}
	
	
	/**
	 * 返回结果只有一条数据
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Map<String,Object> rsToOneMap(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException {

		// 结果集 中列的名称和类型的信息
		ResultSetMetaData rsm = rs.getMetaData();
		int colNumber = rsm.getColumnCount();
		Map<String, Object> map = new HashMap<String, Object>();
		// 遍历每条记录
		while (rs.next()) {
			// 实例化对象
			// 取出每一个字段进行赋值
			for (int i = 1; i <= colNumber; i++) {
				String key = rsm.getColumnName(i);
				Object value = rs.getObject(i);
				map.put(key, value);
			}
		}
		return map;
	}
	
	
	
	

	/*
	 * public static void main(String[] args) throws InstantiationException,
	 * IllegalAccessException { Connection conn = JdbcUtils.getConnection();
	 * //JDBCUtils 自己定义的一个类 PreparedStatement pre = null; ResultSet res = null;
	 * String sql = "select * from user where username=?"; try { pre =
	 * conn.prepareStatement(sql); pre.setString(1,"wqq"); res =
	 * pre.executeQuery(); //调用将结果集转换成实体对象方法 List list = JdbcUtils.Populate(res,
	 * User.class); //循环遍历结果 for(int i=0;i<list.size();i++){ User user = (User)
	 * list.get(i); System.out.println("[username = "+ user.getUsername()+
	 * ",passwd = "+ user.getPassword()+"]"); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } }
	 */

}