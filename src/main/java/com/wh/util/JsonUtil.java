package com.wh.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.json.JSONException;

import java.sql.Statement;





/**
 * @Description 
 * @param
 * @return
 * @date 2015年11月7日上午10:38:27
 * @author 郑爽
 */
public class JsonUtil {
	
	@SuppressWarnings("rawtypes")
    public static String list2Json(List list){
		JSONArray jsonArray = JSONArray.fromObject(list == null || list.isEmpty() ? new ArrayList() : list);
		
		return jsonArray.toString();
	}
	
	public static String str2Json(String str){
		JSONArray json = JSONArray.fromObject(str); 
		/*if(json.size()>0){
			   for(int i=0;i<json.size();i++){
			     JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			    System.out.println(job.get('name')+'=') ;  // 得到 每个对象中的属性值
			  }

		}*/
		return json.toString();
	}
	
	@SuppressWarnings("rawtypes")
    public static String map2Json(Map map){
		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map == null || map.isEmpty() ? new HashMap() : map);
		return jsonObject.toString();
	}
	
	/**
	 * 将json数组转换成list
	 * @param jsonArr
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public static List<Object> jsonArr2List(JSONArray jsonArr) {
		List<Object> list = new ArrayList<Object>();
		list = (List<Object>) JSONArray.toCollection(jsonArr);
		return list;
	}
	
	/**
	 * 将json字符串转换成list
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public static List<Object> str2List(String str) {
		JSONArray jsonArr = JSONArray.fromObject(str);
        List<Object> list = (List<Object>)JSONArray.toCollection(jsonArr);
		return list;
	}
	
	  /**
     * 将Json对象转换成Map
     * 
     * @param jsonObject
     *            json对象
     * @return Map对象
     * @throws JSONException
     */
    public static Map toMap(String jsonString) throws Exception {

        org.json.JSONObject jsonObject = new org.json.JSONObject(jsonString);
        
        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;
        
        while (iterator.hasNext()) {

            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);

        }
        return result;

    }
	
	public static void main(String[] args) {
		String string = "[['00','安徽省'],,['01','澳门特别行政区'],,['02','北京'],,['03','重庆'],,['04','福建省'],,['05','甘肃省'],,['06','广东省'],,['07','广西壮族自治区'],,['08','贵州省'],,['09','海南省'],,['10','河北省'],,"+
"['11','河南省'],,['12','黑龙江省'],,['13','湖北省'],,['14','湖南省'],,['15','吉林省'],,['16','江苏省'],,['17','江西省'],,['18','辽宁省'],,['19','内蒙古自治区'],,['20','宁夏回族自治区'],,['21','青海省'],,['22','山东省'],,"+
"['23','山西省'],,['24','陕西省'],,['25','上海'],,['26','四川省'],,['27','台湾省'],,['28','天津'],,['29','西藏自治区'],,['30','香港特别行政区'],,['31','新疆维吾尔自治区'],,['32','云南省'],,['33','浙江省'],,['99','其它']]";
		
		/*System.out.println(school);
		 List<Object> schoolList = str2List(school);
		 System.out.println(schoolList);
		 /*Connection connection = JDBCUtil.getConnection();
		 Statement statement = null;
		 PreparedStatement ps = null;*/
		String[] Arr = string.split(",,");	
		 for (int i = 0; i < Arr.length; i++) {
			String arr1 = Arr[i];
			
				String proCode = arr1.substring(2, 4);
				String proName = arr1.substring(7, arr1.length()-2);
			/*
			 try {
				 String sql = "insert into province_test(id,province_code,province_name) values(?,?,?)";
				 
				statement  = connection.prepareStatement(sql);
				statement.setString(1, UUIDUtil.getUUID());
				statement.setString(2, proCode);
				statement.setString(3, proName);
				statement.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			 
			
		}
		 
		 
		 
		/*try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from school");
			String sql = "update school set school_code=? where id=?";
			statement = connection.createStatement();
			while(rs.next()){
				System.out.println(rs.getRow()+"："+rs.getString("id"));
				statement.executeUpdate("update school set school_code="+rs.getRow()+" where id='"+rs.getString("id")+"'");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 
	}
}
