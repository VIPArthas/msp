package com.wh.util.msp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.NoSuchMechanismException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 此基础实现JDBC模板 升级工具类 采用占位符形式传参 格式 为 对象字段 #{name}　与入参　MAP 自动替换  提供日常用到的 增 删 改 查 对数据库操作
 * @version 1.1
 * @author   tansixiang
 * @category 此方法主要升级SQL执行模版 升级 采用占位符形式传参  传入对象为 Map<String,Object> 通过占位符形式自动替换成相应值
 *
 */
@SuppressWarnings("resource")
public class JdbcTempletZoneYet {
	private static  Logger log = LoggerFactory.getLogger(JdbcTempletZoneYet.class.getName());
	/**
	 * 查询返回Map对象 
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * @param sql 待执的SQL语言
	 * @param Map<String,Object> 与SQL里面相对应占位符 格式 ：#{name} Map的key 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 * 
	 */
	public Map<String,Object> queryDateTempletForMap(String sql,Map<String,Object> map,String ...mes){
		Map<String,Object> returnMap=new HashMap<String, Object>();
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
		ResultSet rs=null;
		try {
				conn=cm.getConnection(mes);
				try {
				 String[] muilStr=	StringUtil.sqlPatternRender(sql);
				 	if(muilStr==null){
				 		stmt=conn.prepareStatement(sql);
				 	}else{
					stmt = conn.prepareStatement(muilStr[0]);
					String[] obj = muilStr[1].split(";");
					if (obj != null && obj.length > 0)
						for (int i = 0; i < obj.length; i++) {
							if (map.containsKey(obj[i]))
								stmt.setObject(i + 1, map.get(obj[i]));
							else
								throw new Exception(
										"SQL语句与相应参数不对应 Map 缺少相应Key :" + obj[i]);
						} 
				 	}
					stmt.setQueryTimeout(9);
					rs =stmt.executeQuery();
					ResultSetMetaData md = rs.getMetaData();
					int columns=md.getColumnCount();					  
					while(rs.next())
					{
						for(int i=1;i<=columns;i++){ 
							String str=md.getColumnName(i);
							Object object=rs.getObject(i);
							object=rs.getObject(i)==null?"":rs.getObject(i);
							returnMap.put(str, object);
						}
					}
				} catch (Exception e) {
					 log.error("数据库 获取异常：{}", StringUtil.getMysqlExceptionMes(e));
				}	
			
		} catch (Exception e) {
			 log.error("数据库 获取异常：{}", StringUtil.getMysqlExceptionMes(e));
		} finally{
			try {
				if(rs != null){rs.close();}   

			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(stmt != null){stmt.close();}   
			} catch (Exception e2) {			
				e2.printStackTrace();

			}
			try {
				if(conn != null){conn.close();}  	

			} catch (Exception e2) {	
				e2.printStackTrace();

			}	
		}

		return returnMap;
	}
	/**
	 * 返回一个List集合对象
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * @param sql 待执的SQL语言
	 * @param Map<String,Object> 与SQL里面相对应占位符 格式 ：#{name} Map的key 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public List<Map<String,Object>> queryDateTempletForList(String sql,Map<String,Object> map,String ...mes)  {
		List<Map<String,Object>>list =new ArrayList<Map<String,Object>>();
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
		ResultSet rs=null;
		try{
			conn=cm.getConnection(mes);
		try {
			 String[] muilStr=	StringUtil.sqlPatternRender(sql);
			 	if(muilStr==null){
			 		stmt=conn.prepareStatement(sql);
			 	}else{
				stmt = conn.prepareStatement(muilStr[0]);
				String[] obj = muilStr[1].split(";");
				if (obj != null && obj.length > 0)
					for (int i = 0; i < obj.length; i++) {
						if (map.containsKey(obj[i])) {
							stmt.setObject(i + 1, map.get(obj[i]));
						} else {
							throw new NoSuchMechanismException(
									"SQL语句与相应参数不对应 Map 缺少相应Key :" + obj[i]);
						}
					} 
			 	}
		 			stmt.setQueryTimeout(9);
					rs =stmt.executeQuery();
					ResultSetMetaData md = rs.getMetaData();
					int columns=md.getColumnCount();					  
					while(rs.next())
					{
						Map<String,Object> returnmap=new HashMap<String, Object>();
						for(int i=1;i<=columns;i++){  
							String str=md.getColumnName(i);
							Object object=rs.getObject(i);
							object=rs.getObject(i)==null?"":rs.getObject(i);
							returnmap.put(str, object);
						}
						list.add(returnmap);
					}

				}catch (Exception e) {
					 log.error("数据库 获取异常：{}", StringUtil.getMysqlExceptionMes(e));
				}
		} catch (Exception e) {
			 log.error("数据库 获取异常：{}", StringUtil.getMysqlExceptionMes(e));
		}finally{
			try {
				if(rs != null){rs.close();}   

			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(stmt != null){stmt.close();}   
			} catch (Exception e2) {			
				e2.printStackTrace();

			}
			try {
				if(conn != null){conn.close();}  	

			} catch (Exception e2) {	
				e2.printStackTrace();

			}
		}

		return list;
	}

	/**
	 * 查询总数
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * @param sql 待执的SQL语言
	 * @param Map<String,Object> 与SQL里面相对应占位符 格式 ：#{name} Map的key 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public int queryDataCountTemplet(String sql,Map<String,Object> map,String ...mes) {

		int count=0;
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
		ResultSet rs=null;
		try {
			conn=cm.getConnection(mes);
			String[] muilStr=	StringUtil.sqlPatternRender(sql);
		 	if(muilStr==null){
		 		stmt=conn.prepareStatement(sql);
		 	}else{
			stmt = conn.prepareStatement(muilStr[0]);
			String[] obj = muilStr[1].split(";");
			if (obj != null && obj.length > 0)
				for (int i = 0; i < obj.length; i++) {
					if (map.containsKey(obj[i])) {
						stmt.setObject(i + 1, map.get(obj[i]));
					} else {
						throw new Exception(
								"SQL语句与相应参数不对应 Map 缺少相应Key :" + obj[i]);
					}
				} 
		 	}
			rs =stmt.executeQuery();
			while(rs.next()) 
				count=  rs.getInt(rs.getMetaData().getColumnName(1));

		} catch (Exception e) {
			 log.error("数据库 获取异常：{}", StringUtil.getMysqlExceptionMes(e));	 
		}finally{
			try {
				if(rs != null){rs.close();}   

			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(stmt != null){stmt.close();}   
			} catch (Exception e2) {			
				e2.printStackTrace();

			}
			try {
				if(conn != null){conn.close();}  	

			} catch (Exception e2) {	
				e2.printStackTrace();

			}
		}

		return count;
	}

	/**
	 * 变更数据数据 此方法适用于 插入 更新 删除数据
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * @param sql 待执的SQL语言
	 * @param Map<String,Object> 与SQL里面相对应占位符 格式 ：#{name} Map的key 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public  boolean changeDataTemplet(String sql,Map<String,Object> map,String ...mes) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
		boolean flag=false;
		try {
			conn= cm.getConnection(mes);
			try {
				conn.setAutoCommit(false);
				 
				String[] muilStr=	StringUtil.sqlPatternRender(sql);
			 	if(muilStr==null){
			 		stmt=conn.prepareStatement(sql);
			 	}else{
				stmt = conn.prepareStatement(muilStr[0]);
				String[] obj = muilStr[1].split(";");
				if (obj != null && obj.length > 0)
					for (int i = 0; i < obj.length; i++) {
						if (map.containsKey(obj[i])) {
							stmt.setObject(i + 1, map.get(obj[i]));
						} else {
							throw new Exception(
									"SQL语句与相应参数不对应 Map 缺少相应Key :" + obj[i]);
						}
					} 
			 	}
				
				int temp=stmt.executeUpdate();
				conn.commit();
				if(temp>=1) {flag=true;}
			} catch (Exception e) {
				conn.rollback();
				 log.error("数据库 数据回滚 异常信息：{}", StringUtil.getMysqlExceptionMes(e));
			}
			}
			catch (Exception e) {
				 log.error("数据库 链接 获取异常：{}", StringUtil.getMysqlExceptionMes(e));
			}
			finally{
			try {
				if(stmt != null){stmt.close() ; }   

			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn != null){conn.close() ;}  
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}


		return flag;
	}


	/**
	 * 用于批量操作数据-此方法适用于 插入 更新 删除数据
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * @param sql 待执的SQL语言
	 * @param obj 与SQL里面相对应占位符？ 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public  boolean  changeDataTempletBatch(String sql,List<Map<String,Object>> list,String ...mes) {
		Map<String,Object> returnMap=new HashMap<String, Object>();
		boolean flag=false;
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
			try {
				conn= cm.getConnection(mes);
				try {
					conn.setAutoCommit(false);
					String[] muilStr=	StringUtil.sqlPatternRender(sql);
				 	if(muilStr==null){
				 		stmt=conn.prepareStatement(sql);//没有参数的SqL执行
				 	}else{
					stmt = conn.prepareStatement(muilStr[0]);//改成替换后的数据
					String[] obj = muilStr[1].split(";");
				       Iterator<Map<String,Object>> it = list.iterator();
				       while(it.hasNext()){
				    	   Map<String,Object> map=it.next();
				    		if (obj != null && obj.length > 0)
								for (int i = 0; i < obj.length; i++) {
									if (map.containsKey(obj[i])) {
										stmt.setObject(i + 1, map.get(obj[i]));
									} else {
										throw new NullPointerException(
												"SQL语句与相应参数不对应 Map 缺少相应Key :" + obj[i]);
									}
								}   
				    		stmt.addBatch();
				       }
				
				 	}
					stmt.executeBatch();
					conn.commit();
					flag=true;
				} catch (Exception e) {
					conn.rollback();
					e.printStackTrace();
					log.error("入库异常：数据回滚：{}",JsonTool.mapToJson(returnMap));
				}
			} catch (Exception e) {
				log.error("数据库链接获取异常：{}", JsonTool.mapToJson(returnMap));
			} finally{
			try {
				if(stmt != null){stmt.close() ; }   
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn != null){conn.close() ;}  
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return flag;
	}
	/**
	 * 用于批量操作数据-此方法适用于 插入 更新 删除数据
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * @param states 待执的SQL语言List
	 * Map<String,Object> 与SQL里面相对应占位符 格式 ：#{name} Map的key 与之对应参数 
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public boolean batchSaveRelation(List<String> states,Map<String,Object>map,String ... mes)   {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		boolean flag = false;
		PreparedStatement stmt = null;
		try {
			conn = cm.getConnection(mes);
			try {
				int temp=0;
				conn.setAutoCommit(false);
				 for (String sql : states) {//遍历待执行的SQL语句
						//stmt = conn.prepareStatement(sql);
						String[] muilStr=	StringUtil.sqlPatternRender(sql);
					 	if(muilStr==null){//是否含有替换展占位符
					 		stmt=conn.prepareStatement(sql);
					 	}else{
						stmt = conn.prepareStatement(muilStr[0]);
						String[] obj = muilStr[1].split(";");
						if (obj != null && obj.length > 0) {
							//遍历 执行占位符数据
							for (int n = 0; n < obj.length; n++) {
								if (map.containsKey(obj[n])) {
									stmt.setObject(n + 1, map.get(obj[n]));
								} else {
									throw new NullPointerException(
											"SQL语句与相应参数不对应 Map 缺少相应Key :" + obj[n]);
								}
							}	
						}
						 
					 	}
						temp=temp+ stmt.executeUpdate();
				}
				 conn.commit();
				//if(temp==states.size()){
					flag=true;
				//}
			} catch (Exception e) {
				 log.error("数据库 获取异常：{}", StringUtil.getMysqlExceptionMes(e));
			}
		} catch (Exception e) {
			log.error("数据库 获取链接异常：{}", StringUtil.getMysqlExceptionMes(e));
		}finally{
			try {
				if(stmt != null){stmt.close() ; }   
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn != null){conn.close() ;}  
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		 

		return flag;
	}
	
	
	
	
	
	
	/**
	 * 用于批量操作数据-此方法适用于 插入 更新 删除数据
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * @param sql 待执的SQL语言
	 * @param obj 与SQL里面相对应占位符？ 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public boolean batchSaveRelation(List<Map<String, Object>> states,String ... mes)   {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		boolean flag = false;
		PreparedStatement stmt = null;
		try {
			conn = cm.getConnection(mes);
			
			try {
				int temp=0;
				conn.setAutoCommit(false);
				String sql ="";
				Map<String, Object>   map1= null;
				for (int i = 0; i < states.size(); i++) {
					//循环每条待执行的 SQL和Map 
					Map<String, Object> map = states.get(i);
					sql = (String) map.get("sql");
					map1 = (Map<String, Object>) map.get("objs");
					//stmt = conn.prepareStatement(sql);
					String[] muilStr=	StringUtil.sqlPatternRender(sql);
				 	if(muilStr==null){//遍历出每条SQL含有占位符
				 		stmt=conn.prepareStatement(sql);
				 	}else{
					stmt = conn.prepareStatement(muilStr[0]);
					String[] obj = muilStr[1].split(";");
					if (obj != null && obj.length > 0){
						for (int n = 0; n < obj.length; n++) {
							if (map1.containsKey(obj[i])) {
								stmt.setObject(n + 1, map1.get(obj[n]));
							} else {
								throw new  NullPointerException(
										"SQL语句与相应参数不对应 Map 缺少相应Key :" + obj[i]);
							}
						} 
					}
				 	}
					temp=temp+ stmt.executeUpdate();
				 }	
				 conn.commit();
				//if(temp==states.size()){
					flag=true;
				//}
			} catch (Exception e) {
				 log.error("数据库 获取异常：{}", StringUtil.getMysqlExceptionMes(e));
			}
		} catch (Exception e) {
			log.error("数据库 获取链接异常：{}", StringUtil.getMysqlExceptionMes(e));
		}finally{
			try {
				if(stmt != null){stmt.close() ; }   
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn != null){conn.close() ;}  
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		 

		return flag;
	}
	
	
	/**
	 * 查询一个String字符串。
	 * Author： Huang fengge
	 * Date:2015年12月31日
	 * 用于批量操作数据-此方法适用于 插入 更新 删除数据
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * @param sql 待执的SQL语言
	 * @param Map<String,Object> 与SQL里面相对应占位符 格式 ：#{name} Map的key 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public  String queryDataStringTemplet(String sql,Map<String,Object> map,String ... mes){
		String str = null;
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
		ResultSet rs=null;
		try {
			conn=cm.getConnection(mes);
			 String[] muilStr=	StringUtil.sqlPatternRender(sql);
			 	if(muilStr==null){
			 		stmt=conn.prepareStatement(sql);
			 	}else{
				stmt = conn.prepareStatement(muilStr[0]);
				String[] obj = muilStr[1].split(";");
				if (obj != null && obj.length > 0)
					for (int i = 0; i < obj.length; i++) {
						if (map.containsKey(obj[i]))
							stmt.setObject(i + 1, map.get(obj[i]));
						else
							throw new NullPointerException(
									"SQL语句与相应参数不对应 Map 缺少相应Key :" + obj[i]);
					} 
			 	} 
			rs =stmt.executeQuery();
			while(rs.next()) {
				if(rs.getObject(1)==null || rs.getObject(1).equals("")){
					str = "";
				}else{
					str = (String) rs.getObject(1);
				}
			}

		} catch (Exception e) {
			 log.error("数据库 获取异常：{}", StringUtil.getMysqlExceptionMes(e));
		}finally{
			try {
				if(rs != null){rs.close();}   

			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(stmt != null){stmt.close();}   
			} catch (Exception e2) {			
				e2.printStackTrace();

			}
			try {
				if(conn != null){conn.close();}  	

			} catch (Exception e2) {	
				e2.printStackTrace();

			}
		}
		return str;
	}
	
	/**
	 * 查询一个int字符串。
	 * Author： Huang fengge
	 * Date:2015年12月31日
	 * 用于批量操作数据-此方法适用于 插入 更新 删除数据
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * @param sql 待执的SQL语言
	 * @param Map<String,Object> 与SQL里面相对应占位符 格式 ：#{name} Map的key 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public  int queryDataIntTemplet(String sql,Map<String,Object> map,String ... mes){
		int str = 0;
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
		ResultSet rs=null;
		try {
			conn=cm.getConnection(mes);
			 String[] muilStr=	StringUtil.sqlPatternRender(sql);
			 	if(muilStr==null){
			 		stmt=conn.prepareStatement(sql);
			 	}else{
				stmt = conn.prepareStatement(muilStr[0]);
				String[] obj = muilStr[1].split(";");
				if (obj != null && obj.length > 0)
					for (int i = 0; i < obj.length; i++) {
						if (map.containsKey(obj[i]))
							stmt.setObject(i + 1, map.get(obj[i]));
						else
							throw new NullPointerException(
									"SQL语句与相应参数不对应 Map 缺少相应Key :" + obj[i]);
					} 
			 	} 
			rs =stmt.executeQuery();
			while(rs.next()) {
					str = rs.getInt(1);
			}

		} catch (Exception e) {
			 log.error("数据库 获取异常：{}", StringUtil.getMysqlExceptionMes(e));
		}finally{
			try {
				if(rs != null){rs.close();}   

			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(stmt != null){stmt.close();}   
			} catch (Exception e2) {			
				e2.printStackTrace();

			}
			try {
				if(conn != null){conn.close();}  	

			} catch (Exception e2) {	
				e2.printStackTrace();

			}
		}
		return str;
	}
	
	/**
	 * 返回一个List集合对象
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * @param sql 待执的SQL语言
	 * @param Map<String,Object> 与SQL里面相对应占位符 格式 ：#{name} Map的key 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public List<String> queryStringTempletForList(String sql,Map<String,Object> map,String ...mes)  {
		List<String>list =new ArrayList<String>();
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
		ResultSet rs=null;
		try{
			conn=cm.getConnection(mes);
		try {
			 String[] muilStr=	StringUtil.sqlPatternRender(sql);
			 	if(muilStr==null){
			 		stmt=conn.prepareStatement(sql);
			 	}else{
				stmt = conn.prepareStatement(muilStr[0]);
				String[] obj = muilStr[1].split(";");
				if (obj != null && obj.length > 0)
					for (int i = 0; i < obj.length; i++) {
						if (map.containsKey(obj[i]))
							stmt.setObject(i + 1, map.get(obj[i]));
						else
							throw new NullPointerException(
									"SQL语句与相应参数不对应 Map 缺少相应Key :" + obj[i]);
					} 
			 	}
					rs =stmt.executeQuery();
					while(rs.next()){
						String s = null;
						if(rs.getObject(1)==null || rs.getObject(1).equals("")){
							s = "";
						}else{
							s = (String) rs.getObject(1);
						}
						list.add(s);
					}

				}catch (Exception e) {
					 log.error("数据库 获取异常：{}", StringUtil.getMysqlExceptionMes(e));
				}
		} catch (Exception e) {
			 log.error("数据库 获取异常：{}", StringUtil.getMysqlExceptionMes(e));
		}finally{
			try {
				if(rs != null){rs.close();}   

			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(stmt != null){stmt.close();}   
			} catch (Exception e2) {			
				e2.printStackTrace();

			}
			try {
				if(conn != null){conn.close();}  	

			} catch (Exception e2) {	
				e2.printStackTrace();

			}
		}

		return list;
	}
}
