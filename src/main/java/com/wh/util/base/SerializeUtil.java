package com.wh.util.base;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

public class SerializeUtil {
	
	

	/**
	 * 转换json字符串 JSON也是一种序列化。
	 * @param object
	 * @return
	 */
	public static String jsonSerialize(Object object) {
	     return new Gson().toJson(object);
	}
	
	/**
	 * 字符串转对象
	 * @param object
	 * @param classOfT
	 * @return
	 */
	public static <T> T unJsonSerialize(String object,Class<T> classOfT) {
    	T t = null;
    	t=new Gson().fromJson(object, classOfT);
		return t;	
	}
	
	

	/**
	 * 转换json字符串 JSON也是一种序列化。
	 * @param object
	 * @return
	 */
	public static String fastJsonSerialize(Object object) {
	     return JSON.toJSONString(object);
	}
	
	/**
	 * 字符串转对象
	 * @param object
	 * @param classOfT
	 * @return
	 */
	public static <T> T unFastJsonSerialize(String object,Class<T> classOfT) {
		T t = null;
        try {
            t = JSON.parseObject(object, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
	}

	
}