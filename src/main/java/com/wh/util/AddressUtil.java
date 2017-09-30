package com.wh.util;

import com.wh.constants.Constants;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liyakun on 2016-8-4.
 * 根据百度地图api获取地址详细信息
 */
public class AddressUtil {
    private Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * 根据经纬度返回格式化的地址
     * @param lng
     * @param lat
     * @return
     */
    public static String getAddressByXY(Double lng, Double lat){
        String geoUrl = "http://api.map.baidu.com/geocoder/v2/";
        String param = "ak="+Constants.BDWebAK+"&output=json&location="+lat+","+lng;
        return HttpClient.sendGet(geoUrl, param);
    }

    /**
     * 根据ip去百度定位(高精度IP定位API·Web服务API)
     * @param ip
     * @return
     */
    public static String getAddressByIp(String ip) {
        String geoUrl = "https://api.map.baidu.com/highacciploc/v1";
        String param = "ak="+Constants.BDWebAK+"&coord=bd09ll&qterm=mb&qcip="+ip;
        return HttpClient.sendGet(geoUrl, param);
    }

    /**
     * 根据ip去百度定位(普通IP定位API·Web服务API)
     * @param ip
     * @return
     */
    public static String getAddressByIp2(String ip) {
        String geoUrl = "https://api.map.baidu.com/location/ip";
        String param = "ak="+Constants.BDWebAK+"&coord=bd09ll&ip="+ip;
        return HttpClient.sendGet(geoUrl, param);
    }


    /**
     * 根据ip获取精确地址，如果获取不到则去ip库中匹配
     * @param ip
     * @return
     */
    public String getAddressDetailByIp(String ip) {
        String sr = AddressUtil.getAddressByIp(ip);
        JSONObject object = JSONObject.fromObject(sr);
        String addressDetail = "";
        Integer error =  object.getJSONObject("result").getInt("error");
        if (error == 161) {  //获取到精确ip地址

            Double lng = object.getJSONObject("content").getJSONObject("location").getDouble("lng");
            Double lat = object.getJSONObject("content").getJSONObject("location").getDouble("lat");
            String result = AddressUtil.getAddressByXY(lng, lat);
            JSONObject jsStr = JSONObject.fromObject(result);
            Integer status = jsStr.getInt("status");
            if (status == 0) {
                addressDetail = jsStr.getJSONObject("result").getString("formatted_address");
                log.info("获取到精确ip地址{}", addressDetail);
            }
        }else {//获取到普通ip地址
            String sr2 = AddressUtil.getAddressByIp2(ip);
            JSONObject object1 = JSONObject.fromObject(sr2);
            if (0 == object1.getInt("status")) {
                addressDetail = object1.getJSONObject("content").getString("address");
                log.info("获取到普通ip地址{}", addressDetail);
            }
        }
        return addressDetail;
    }

    /**
     * 根据ip获取经纬度
     * @param ip
     * @return
     */
    public static Map<String, Double> getLatAndLngByIp(String ip) {
        String sr = AddressUtil.getAddressByIp(ip);
        JSONObject object = JSONObject.fromObject(sr);
        String addressDetail = "";
        Integer error =  object.getJSONObject("result").getInt("error");
        Map<String,Double> map= new HashMap<String,Double>();
        if (error == 161) {  //获取到精确ip地址

            Double lng = object.getJSONObject("content").getJSONObject("location").getDouble("lng");
            Double lat = object.getJSONObject("content").getJSONObject("location").getDouble("lat");
            map.put("lng", lng);
            map.put("lat", lat);
        }
        return map;
    }
}
