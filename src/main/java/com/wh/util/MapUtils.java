package com.wh.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Map转换辅助类
 * Created by danding on 2016/3/10.
 */
public class MapUtils {
    /**
     * 将Map中数据转换为指定的对象并返回
     * @param map
     * @param cls
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static Object mapT(Map<String,Object> map,Class cls) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor ct = cls.getConstructor();
        Object object = ct.newInstance();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if(map.containsKey(name)){
                Method method = cls.getMethod("set"+name.substring(0, 1).toUpperCase() + name.substring(1), field.getType());
                method.invoke(object,map.get(name));
            }
        }
        return object;
    }
}
