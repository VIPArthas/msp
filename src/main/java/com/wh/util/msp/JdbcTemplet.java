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
import org.apache.log4j.Logger;

/**
 * 此基础实现JDBC模板  提供日常用到的 增 删 改 查 对数据库操作
 * @version 1.0
 * @author  
 *
 */
public class JdbcTemplet {
	private static org.apache.log4j.Logger log = Logger.getLogger(JdbcTemplet.class);

	/**
	 * 查询返回Map对象 
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * @param sql 待执的SQL语言
	 * @param obj 与SQL里面相对应占位符？ 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public Map<String,Object> queryDateTempletForMap(String sql,Object[] obj,String ...mes){
		Map<String,Object> map=new HashMap<String, Object>();
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
		ResultSet rs=null;
		try {
				conn=cm.getConnection(mes);
				try {
					stmt=conn.prepareStatement(sql);
					stmt.setQueryTimeout(9);
					if(obj!=null && obj.length>0)
					for(int i=0;i<obj.length;i++){  
						stmt.setObject(i+1, obj[i]);  
					}  
					rs =stmt.executeQuery();
					ResultSetMetaData md = rs.getMetaData();
					int columns=md.getColumnCount();					  
					while(rs.next())
					{
						for(int i=1;i<=columns;i++){ 
							String str=md.getColumnName(i);
							Object object=rs.getObject(i);
							object=rs.getObject(i)==null?"":rs.getObject(i);
							map.put(str, object);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.error("数据查询异常："+e.getMessage()); 
				}	
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("数据库链接获取异常"+e.getMessage());
			// MQProductHelper.produce("GaGaLogRecord", "sdfsdfsdfsdfsdf");
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

		return map;
	}
	/**
	 * 返回一个List集合对象
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * 查询条件使用必须SQL 语句封装占位符必须与入参顺序一直
	 * @param sql 待执的SQL语言
	 * @param obj 与SQL里面相对应占位符？ 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public List<Map<String,Object>> queryDateTempletForList(String sql,Object[] obj,String ...mes) throws Exception{
		List<Map<String,Object>>list =new ArrayList<Map<String,Object>>();
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
		ResultSet rs=null;
		try{
			conn=cm.getConnection(mes);
		try {
					stmt=conn.prepareStatement(sql);
					stmt.setQueryTimeout(9);
					if(obj!=null && obj.length>0)
					for(int i=0;i<obj.length;i++){  
						stmt.setObject(i+1, obj[i]);  
					}  
					rs =stmt.executeQuery();
					ResultSetMetaData md = rs.getMetaData();
					int columns=md.getColumnCount();					  
					while(rs.next())
					{
						Map<String,Object> map=new HashMap<String, Object>();
						for(int i=1;i<=columns;i++){  
		
							String str=md.getColumnName(i);
							Object object=rs.getObject(i);
							object=rs.getObject(i)==null?"":rs.getObject(i);
							map.put(str, object);
						}
						list.add(map);
					}

				}catch (Exception e) {
					e.printStackTrace();
				}
		} catch (Exception e) {
			log.error("数据库链接获取异常："+e.getMessage());
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
	 * @param obj 与SQL里面相对应占位符？ 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public  int queryDataCountTemplet(String sql,Object[] obj,String ...mes)throws Exception{

		int count=0;
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
		ResultSet rs=null;
		try {
			conn=cm.getConnection(mes);
			stmt=conn.prepareStatement(sql);
			stmt.setQueryTimeout(9);
			if(obj!=null && obj.length>0)
			for(int i=0;i<obj.length;i++){  
				stmt.setObject(i+1, obj[i]);  
			}  
			rs =stmt.executeQuery();
			while(rs.next()) 
				count=  rs.getInt(rs.getMetaData().getColumnName(1));

		} catch (Exception e) {
			e.printStackTrace();
			log.error("数据库查询数量："+e.getMessage());
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
	 * @param obj 与SQL里面相对应占位符？ 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public  boolean changeDataTemplet(String sql,Object[] obj,String ...mes)throws Exception{
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
		boolean flag=false;
		try {
			conn= cm.getConnection(mes);
			try {
				conn.setAutoCommit(false);
				stmt=conn.prepareStatement(sql);
				stmt.setQueryTimeout(9);
				if(obj!=null && obj.length>0)
				for(int i=0;i<obj.length;i++){  
					stmt.setObject(i+1, obj[i]);  
				}  
				int temp=stmt.executeUpdate();
				conn.commit();
				if(temp>=1) {flag=true;}
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
				log.error("入口异常：数据回滚："+ JsonTool.mapToJson(obj));
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				log.error("数据库获取链接"+ JsonTool.mapToJson(obj));
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
	public  boolean  changeDataTempletBatch(String sql,List<Object[]> list,String ...mes)throws Exception{
		boolean flag=false;
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
			try {
				conn= cm.getConnection(mes);
				try {
					stmt= conn.prepareStatement(sql);
					stmt.setQueryTimeout(9);
					conn.setAutoCommit(false);
					Iterator<Object[]> it = list.iterator();
					while(it.hasNext()){
						Object[]   obj=it.next();
						for(int i=0;i<obj.length;i++)
							stmt.setObject(i+1, obj[i]); 
						stmt.addBatch();
					}
					stmt.executeBatch();
					conn.commit();
					flag=true;
				} catch (Exception e) {
					conn.rollback();
					e.printStackTrace();
					log.error("入库异常：数据回滚："+JsonTool.mapToJson(list));
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("数据库链接获取异常："+JsonTool.mapToJson(list));
				
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
	 * @param sql 待执的SQL语言
	 * @param obj 与SQL里面相对应占位符？ 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public boolean batchSaveRelation(List<Map<String, Object>> states,String ... mes) throws Exception {
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
				Object[] objs= null;
				for (int i = 0; i < states.size(); i++) {
					Map<String, Object> map = states.get(i);
					sql = (String) map.get("sql");
					objs = (Object[]) map.get("objs");
					stmt = conn.prepareStatement(sql);
					stmt.setQueryTimeout(9);
				 	for (int j = 0; j < objs.length; j++) {
						stmt.setObject(j + 1, objs[j]);
					} 
					temp=temp+ stmt.executeUpdate();
				 }	
				conn.commit();
				//if(temp==states.size()){
					flag=true;
				//}
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			
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
	 * @param obj 与SQL里面相对应占位符？ 与之对应参数
	 * @param mes R 展示类和非时效性，W 总业务判断的主库（数据变更操作）
	 * @return
	 */
	public  String queryDataStringTemplet(String sql,Object[] obj,String ... mes){
		String str = null;
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = null;
		PreparedStatement  stmt =  null;
		ResultSet rs=null;
		try {
			conn=cm.getConnection(mes);
			stmt=conn.prepareStatement(sql);
			stmt.setQueryTimeout(9);
			for(int i=0;i<obj.length;i++){  
				stmt.setObject(i+1, obj[i]);  
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
			e.printStackTrace();
			log.error("数据库查询数量："+e.getMessage());
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
	
}
