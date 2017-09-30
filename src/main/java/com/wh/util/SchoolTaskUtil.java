package com.wh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author lp
 * @Description: ${todo}(校园任务的公共方法)
 * @date 2016/10/9 15:51
 *
 */
public class SchoolTaskUtil {
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;
    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

   //举报和敏感词汇的分类
    public static List<HashMap<String,String>> exposeTypeList = new ArrayList<>();
    /**
     * @Description: ${todo}(初始化举报类型)
     * @author lp
     * @date 2016年10月31日14:40:13
     * @return ${return_type}    返回类型
     *
     */
    public static  List<String> getExposeTypeList(){
        List<String> list=new ArrayList<>();
        list.add("政治、民族安全");
        list.add("邪教");
        list.add("黄赌毒枪支弹药");
        list.add("广告");
        list.add("其它");
//        map.put("key","政治、民族安全");
//        exposeTypeList.add(map);
//        map=new HashMap<>();
//        map.put("key","邪教");
//        exposeTypeList.add(map);
//        map=new HashMap<>();
//        map.put("key","黄赌毒枪支弹药");
//        exposeTypeList.add(map);
//        map=new HashMap<>();
//        map.put("key","广告");
//        exposeTypeList.add(map);
//        map=new HashMap<>();
//        map.put("key","其它");
//        exposeTypeList.add(map);
        return list;
    }
    /**
     * 
     * @Title: calTimeEquation 
     * @Description: 可以根据当前时间计算出距离某个时间点是几分钟前，还是几小时前，几天前
     * @author lp
     * @Date 2016年10月9日  下午4:45:14 
     * @param time
     * @return
     * @return String    返回类型
     */
    public static String calTimeEquation(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String t = format(date).toString();
        return t;
    }

    public static String format(Date date) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }


    /** 根据生日算出用户的年龄 **/
    public static int GetAge(String birthday) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Date mydate;
        int age = 0;
        try {
            mydate = myFormatter.parse(birthday);
            long day = (date.getTime() - mydate.getTime())
                    / (24 * 60 * 60 * 1000) + 1;
            age = (int) (day / 365f);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return age;
    }
}
