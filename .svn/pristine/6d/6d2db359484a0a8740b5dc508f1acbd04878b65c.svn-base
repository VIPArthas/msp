package com.wh.util;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created by danding on 2015/9/28.
 */
public class PropertiesUtil {
    private static final Properties prop = new Properties();
    /**
     * 加载配置文件
     * 目录默认为webapp下的根目录
     * @param path
     */
    private static void loadPropertiesFile(String path){
        String pathStr;
        try {
            pathStr = PropertiesUtil.class.getClassLoader().getResource("").toURI().getPath();
            pathStr = pathStr.substring(0, pathStr.indexOf("WEB-INF"));
            // 把文件读入文件输入流，存入内存中
            FileInputStream fis = new FileInputStream(new File(pathStr + path));
            //加载文件流的属性
            prop.load(fis);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropertie(String path,String key){
        boolean isLoad = false;
        if(prop==null){
            loadPropertiesFile(path);
            isLoad = true;
        }
        String value = prop.getProperty(key,"");
        if("".equals(value)&& isLoad==false){
            loadPropertiesFile(path);
            value = prop.getProperty(key,"");
        }
        return value;
    }
}
