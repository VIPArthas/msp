package com.wh.util.base;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wh.util.StringUtil;



public class HttpClientUtils {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static String get(String url, String param) {
    	return post(url+"?"+param, "","");
    }
	
    
    public static String get(String serverUrl, Map<String, String> params) {
    	String data="";
    	if(params!=null&&params.size()>0){
    		for (String key:params.keySet()) {
    			data+=key+"="+params.get(key)+"&";
			}
    		data=data.substring(0,data.length()-1);
    	}
    	return post(serverUrl+"?"+data,"","");
    }

	public static String post(String serverUrl,String data,String type){
		 StringBuilder responseBuilder = null;
	        BufferedReader reader = null;
	        OutputStreamWriter wr = null;

	        URL url;
	        try {
	            url = new URL(serverUrl);
	            URLConnection conn = url.openConnection();
	            if(StringUtil.isNotEmpty(type)){
	            	 conn.setRequestProperty("Content-type", type);
	            }
	            
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setConnectTimeout(1000 * 5);
	            conn.setReadTimeout(60*1000);
	            
	            if(StringUtil.isNotEmpty(data)){
	            	System.out.println(data);
	            	
	            	wr = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
	 	            wr.write(data);
	 	            wr.flush();
	            }

	            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	            responseBuilder = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                responseBuilder.append(line).append("\n");
	            }
	            logger.debug(responseBuilder.toString());
	            return responseBuilder.toString();
	        } catch (IOException e) {
	        	logger.error("发送http请求error||url:"+serverUrl+"||data:"+data+"||type:"+type+"||异常信息:",e);
	        	return "";
	        } finally {

	            if (wr != null) {
	                try {
	                    wr.close();
	                } catch (IOException e) {
	                	logger.error("close error", e);
	                }
	            }

	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
	                	logger.error("close error", e);
	                }
	            }

	        }
	}
	
	
	
    public static String post(String serverUrl, Map<String, String> params) {
    	String data="";
    	if(params!=null&&params.size()>0){
    		for (String key:params.keySet()) {
    			data+=key+"="+params.get(key)+"&";
			}
    		data=data.substring(0,data.length()-1);
    	}
    	return post(serverUrl, data,null);
    	
       
    }
    
    
    
    public static String postJson(String serverUrl, Map<String, String> params,String type) {
    	String data=SerializeUtil.jsonSerialize(params);
    	return post(serverUrl, data, type);
    }
    
    
    public static String postJsonStr(String serverUrl, String data,String type) {
    	return post(serverUrl, data, type);
    }
    
    public static String post(String serverUrl, Map<String, String> params,String type) {
    	String data="";
    	if(params!=null&&params.size()>0){
    		for (String key:params.keySet()) {
    			data+=key+"="+params.get(key)+"&";
			}
    		data=data.substring(0,data.length()-1);
    	}
    	return post(serverUrl, data,type);
    	
       
    }
   
    /**
     * 调用中心接口,发送POST请求
     * 
     */
    public static String postMerge(String serverUrl,Map<String, String> params){
    	String data=SerializeUtil.jsonSerialize(params);
    	PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(serverUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.addRequestProperty("Content-Type", "json");
            
            String remoteIP = "1.1.1.1";
            if(StringUtil.isNotEmpty(params.get("ip"))){
            	remoteIP = params.get("ip");
            }
            conn.addRequestProperty("remoteIP",remoteIP );
            conn.addRequestProperty("reqeustUUID", UUID.randomUUID().toString());
            conn.addRequestProperty("terminal", "PC");
            conn.addRequestProperty("identityKey", "PC");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(data);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
	}
}
