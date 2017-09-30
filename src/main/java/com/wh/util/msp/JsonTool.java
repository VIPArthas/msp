package com.wh.util.msp;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Json转换工具
 * @author Administrator
 *
 */
public class JsonTool {
	
	/**
	 * json 转换为泛型对象
	 * @param jsonString
	 * @return
	 */
	public static  Map<String,Object>  jsonToObject(String jsonString){
	    Gson gson = new GsonBuilder().enableComplexMapKeySerialization()  
                .create();  
		  return  gson.fromJson(jsonString,new TypeToken<Map<String, Object>>() {}.getType()); 
	}
	/**
	 * 转换为Map类型
	 * @param jsonString
	 * @return
	 */
	public static List<Map<String,Object>>  jsonToListMap(String jsonString){
	    Gson gson = new GsonBuilder().enableComplexMapKeySerialization()  
                .create();  
	    List<Map<String,Object>> retMap = gson.fromJson(jsonString,  
	                new TypeToken<List<Map<String,Object>>>() {}.getType()); 
		 	return retMap;
	}
	
	/**
	 * Map 转换为Json 对象
	 * @param fromMap
	 * @return
	 */
	 public static String mapToJson(Object object){
		return new GsonBuilder().enableComplexMapKeySerialization()  
         .create().toJson(object);
		 
	 }
	 public static <T> T jsonToObject(String json, Class<T> clazz){
		 Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		 return gson.fromJson(json, clazz);
	 }
	
}
