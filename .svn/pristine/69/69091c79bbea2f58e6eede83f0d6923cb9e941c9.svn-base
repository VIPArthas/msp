/*package com.lydream.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ProConfig {

    private static Configuration config = null;

    private static String fileName = "mobile.properties";

    private static void init() {
        // System.out.println("根据配置文件获取信息");
        try {
            config = new PropertiesConfiguration(ProConfig.fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println("成功加载配置文件");
    }

    public static String getPropertiesByFileNameAndKey(String fileName, String name) {

        if (ProConfig.config == null || !fileName.equals(ProConfig.fileName)) {
            ProConfig.fileName = fileName;
            ProConfig.init();
        }

        return ProConfig.config.getString(name);
    }

    public static String getPropertiesByKey(String name) {
        if (ProConfig.fileName != null && !"".equals(ProConfig.fileName))
            ProConfig.init();
        if (ProConfig.config != null) {
            return ProConfig.config.getString(name);
        } else
            return null;
    }

    public static void main(String[] args) {
        System.out.println("设备管理员--" + ProConfig.getMenu("1"));
        System.out.println("设备领用人--" + ProConfig.getMenu("2"));
        System.out.println("供应商管理员--" + ProConfig.getMenu("3"));
        System.out.println("供应商工程师--" + ProConfig.getMenu("4"));
    }

    public static String getMenu(String post) {
        String result = ProConfig.getPropertiesByKey("MENU_RESOURCE_STRING");
        String index = null;
        String action = null;
        
         * if(post.equals(Constants.storageManage)){
         * //设备管理员-首页
         * index = ProConfig.getPropertiesByKey("MOBILE_INDEX_ADMIN");
         * //设备管理员发起
         * action = ProConfig.getPropertiesByKey("MOBILE_INIT_ADMIN");
         * }else if(post.equals(Constants.storageUser)){
         * //设备领用人-首页
         * index = ProConfig.getPropertiesByKey("MOBILE_INDEX_PEOPLE");
         * //设备领用人发起
         * action = ProConfig.getPropertiesByKey("MOBILE_INIT_PEOPLE");
         * }else if(post.equals(Constants.supplierManage)||post.equals(Constants.serviceManage)){
         * //供应商管理员-首页
         * index= ProConfig.getPropertiesByKey("MOBILE_INDEX_SERVICE_ADMIN");
         * //供应商管理员发起
         * action = ProConfig.getPropertiesByKey("MOBILE_INIT_SERVICE_ADMIN");
         * }else if(post.equals(Constants.supplierEngineer)||post.equals(Constants.serviceEngineer)){
         * //供应商工程师-首页
         * index = ProConfig.getPropertiesByKey("MOBILE_INDEX_SERVICE_ENGINEER");
         * //供应商工程师发起
         * action = ProConfig.getPropertiesByKey("MOBILE_INIT_SERVICE_ENGINEER");
         * }else if(post.equals(Constants.supplierEngineer)||post.equals(Constants.serviceEngineer)){
         * //维保商管理员-首页
         * index = ProConfig.getPropertiesByKey("MOBILE_INDEX_SERVICE_ADMIN");
         * //维保商管理员发起
         * action = ProConfig.getPropertiesByKey("MOBILE_INIT_SERVICE_ADMIN");
         * }else if(post.equals(Constants.supplierEngineer)||post.equals(Constants.serviceEngineer)){
         * //维保商工程师-首页
         * index = ProConfig.getPropertiesByKey("MOBILE_INDEX_SERVICE_ENGINEER");
         * //维保商工程师发起
         * action = ProConfig.getPropertiesByKey("MOBILE_INIT_SERVICE_ENGINEER");
         * }
         
        if (index != null && index.length() > 0 && index.trim().length() > 0) {
            String arg[] = index.split("#");
            for (int i = 0; i < arg.length; i++) {
                if (arg[i].equals("wating")) {
                    result = result.replace("\"wating\":false", "\"wating\":true");
                }
                if (arg[i].equals("maintenance")) {
                    result = result.replace("\"maintenance\":false", "\"maintenance\":true");
                }
                if (arg[i].equals("my_create")) {
                    result = result.replace("\"my_create\":false", "\"my_create\":true");
                }
                if (arg[i].equals("comment")) {
                    result = result.replace("\"comment\":false", "\"comment\":true");
                }
                if (arg[i].equals("create")) {
                    result = result.replace("\"create\":false", "\"create\":true");
                }
                if (arg[i].equals("comment_list")) {
                    result = result.replace("\"create\":false", "\"comment_list\":true");
                }
            }
        }
        if (action != null && action.length() > 0 && action.trim().length() > 0) {
            String arg1[] = action.split("#");
            for (int i = 0; i < arg1.length; i++) {
                if (arg1[i].equals("1")) {
                    result = result.replace("\"fun_draws\":false", "\"fun_draws\":true");
                }
                if (arg1[i].equals("2")) {
                    result = result.replace("\"fun_allot\":false", "\"fun_allot\":true");
                }
                if (arg1[i].equals("3")) {
                    result = result.replace("\"fun_scrapped\":false", "\"fun_scrapped\":true");
                }
                if (arg1[i].equals("4")) {
                    result = result.replace("\"fun_repair\":false", "\"fun_repair\":true");
                }
                if (arg1[i].equals("5")) {
                    result = result.replace("\"fun_mainten\":false", "\"fun_mainten\":true");
                }
                if (arg1[i].equals("6")) {
                    result = result.replace("\"fun_storage\":false", "\"fun_storage\":true");
                }
                if (arg1[i].equals("7")) {
                    result = result.replace("\"fun_inventory\":false", "\"fun_inventory\":true");
                }
                if (arg1[i].equals("8")) {
                    result = result.replace("\"fun_barter\":false", "\"fun_barter\":true");
                }
            }
        }
        result = result.replaceAll("#", ",");
        return result.toString();
    }
}*/