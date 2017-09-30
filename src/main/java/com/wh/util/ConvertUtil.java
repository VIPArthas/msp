package com.wh.util;

public class ConvertUtil {

	/**
	 * 功能描述：Object类转换为String，避免在Object为null时，直接toString()出错<BR>
	 * @param obj
	 * @return
	 */
	public static String obj2Str(Object obj) {
		return obj == null ? null : obj.toString();
	}
	public static String obj2StrBlank(Object obj) {
		return obj == null ? "" : obj.toString();
	}
	public static Integer obj2Integer(Object obj){
		return obj == null || obj.toString().trim().equals("")  ? null : Integer.parseInt(obj.toString()); 
	}
	public static int obj2Int(Object obj){
		return obj == null || obj.toString().trim().equals("")  ? null : Double.valueOf(obj.toString()).intValue(); 
	}
	
	public static Long obj2Long(Object obj){
		return obj == null || obj.toString().trim().equals("") ? null : Long.parseLong(obj.toString()); 
	}
	
	public static Long obj2Long(Object obj,boolean filter){
		if (!filter) {
			return obj == null || obj.toString().trim().equals("") ? null : Long.parseLong(obj.toString()); 
		} else {
			return obj == null || obj.toString().trim().equals("") ? null : Long.parseLong(obj.toString().replaceAll("[^0-9]", "")); 
		}
	}

}
