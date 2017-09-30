package com.wh.util;

import java.lang.reflect.Field;

/**
 * @Title:			ReflectUtil.java
 * @Description: 	分页扩展类支持mybatis的foreach标签
 */
public class ReflectUtil {
	
	/**
	  * @Description:	获取obj对象fieldName的Field
	  * @param			
	  * @return			
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
            }
        }
        return null;
    }
	
	/**
	  * @Description:	获取obj对象fieldName的属性值
	  * @param			
	  * @return			
	 */
	public static Object getValueByFieldName(Object obj, String fieldName) throws SecurityException,
    	NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if (field != null) {
		    if (field.isAccessible()) {
		        value = field.get(obj);
		    } else {
		        field.setAccessible(true);
		        value = field.get(obj);
		        field.setAccessible(false);
		    }
		}
		return value;
	}
	/**
	  * @Description:	设置obj对象fieldName的属性值
	  * @param			
	  * @return			
	 */
	public static void setValueByFieldName(Object obj, String fieldName, Object value) throws SecurityException,
	    NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		if (field.isAccessible()) {
		    field.set(obj, value);
		} else {
		    field.setAccessible(true);
		    field.set(obj, value);
		    field.setAccessible(false);
		}
	}
}
