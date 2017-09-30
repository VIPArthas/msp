package com.wh.controller.msp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.controller.common.BaseController;
import com.wh.mspentity.MspPlatform;
import com.wh.service.msp.MspPlatformService;
import com.wh.util.msp.Constants;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/msp/qiyeAsyn")
public class QiyeAsynController extends BaseController{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private MspPlatformService mspPlatformService;
	/** 
     * 上传csv文件到腾讯服务器 
     * @param content  cvs文件内容 
     * @return 
     * @throws Exception 
     */  
	@RequestMapping(value = "/web/sendCVSFile.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
    public  JSONObject sendCVSFile( String content) throws Exception {
		
		StringBuffer cvs_contacts=new StringBuffer("部门名称,部门ID,父部门ID,排序").append("\n");
		//注意换行用\n不要\R\N
		cvs_contacts.append("万华教育,1,0,1").append("\n");
		cvs_contacts.append("测试组,2,1,2").append("\n");
		content=cvs_contacts.toString();
        String result = null;    
        MspPlatform mspPlatform = mspPlatformService.load(Constants.AGENTID);
        String token = mspPlatform.getAccessToken();
        System.out.println("token:"+token);  
        /**  
        * 第一部分  
        */    
        URL urlObj = new URL("https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token="+ token   
                + "&type=file");   
                                
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();    
        con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式    
        con.setDoInput(true);    
        con.setDoOutput(true);    
        con.setUseCaches(false); // post方式不能使用缓存    
        // 设置请求头信息    
        con.setRequestProperty("Connection", "Keep-Alive");    
        con.setRequestProperty("Charset", "UTF-8");    
        // 设置边界    
        String BOUNDARY = "----------" + System.currentTimeMillis();    
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);    
        // 请求正文信息    
        // 第一部分：    
        StringBuilder sb = new StringBuilder();    
        sb.append("--"); // 必须多两道线    
        sb.append(BOUNDARY);    
        sb.append("\r\n");    
        sb.append("Content-Disposition: form-data;name=\"media\";filename=\""+ "e:\\info.csv" + "\"\r\n");    
        sb.append("Content-Type:application/octet-stream\r\n\r\n");    
        byte[] head = sb.toString().getBytes("utf-8");    
        // 获得输出流    
        OutputStream out = new DataOutputStream(con.getOutputStream());    
        // 输出表头    
        out.write(head);    
        // 文件正文部分    
        // 把文件已流文件的方式 推入到url中    
        DataInputStream in = new DataInputStream(new  ByteArrayInputStream(content.getBytes()));    
        int bytes = 0;    
        byte[] bufferOut = new byte[1024];    
        while ((bytes = in.read(bufferOut)) != -1) {    
        out.write(bufferOut, 0, bytes);    
        }    
        in.close();    
        // 结尾部分    
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线    
        out.write(foot);    
        out.flush();    
        out.close();    
        StringBuffer buffer = new StringBuffer();    
        BufferedReader reader = null;    
        try {    
        // 定义BufferedReader输入流来读取URL的响应    
        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));    
        String line = null;    
        while ((line = reader.readLine()) != null) {    
        //System.out.println(line);    
        buffer.append(line);    
        }    
        if(result==null){    
        result = buffer.toString();    
        }    
        } catch (IOException e) {    
        System.out.println("发送POST请求出现异常！" + e);    
        e.printStackTrace();    
        throw new IOException("数据读取异常");    
        } finally {    
        if(reader!=null){    
        reader.close();    
        }    
        }    
        JSONObject jsonObj =JSONObject.fromObject(result);    
        return jsonObj;    
    }
    
    
    /** 
     * 执行异步任务 
     * @param mediaId  上传的CVS文件 
     * @param url  微信服务地址"https://qyapi.weixin.qq.com/cgi-bin/batch/replaceuser?access_token="+token; 
     * @return 
     */  
	@RequestMapping(value = "/web/sendCVSData.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
    public boolean sendCVSData(String mediaId,String url){  
		mediaId="34_HnKZVfXAhYSVmiuTEtDpixFw65zsLRSXjmA3FPEjc";
        String jsonContext="{"+  
                "\"media_id\":\""+mediaId+"" + "}"; 
        System.out.println(jsonContext);
        MspPlatform mspPlatform = mspPlatformService.load(Constants.AGENTID);
        String token = mspPlatform.getAccessToken();
        url="https://qyapi.weixin.qq.com/cgi-bin/batch/replaceparty?access_token="+token;
        //发送消息  
        //消息json格式  
          
        boolean flag=false;  
        try {  
             CloseableHttpClient httpclient = HttpClients.createDefault();  
             HttpPost httpPost= new HttpPost(url);  
             //发送json格式的数据  
             StringEntity myEntity = new StringEntity(jsonContext,   
                       ContentType.create("text/plain", "UTF-8"));  
              
             httpPost.setEntity(myEntity);  
             // Create a custom response handler  
            ResponseHandler<JSONObject> responseHandler = new ResponseHandler<JSONObject>() {  
              
                public JSONObject handleResponse(  
                        final HttpResponse response) throws ClientProtocolException, IOException {  
                    int status = response.getStatusLine().getStatusCode();  
                    if (status >= 200 && status < 300) {  
                        HttpEntity entity = response.getEntity();  
                        if(null!=entity){  
                            String result= EntityUtils.toString(entity);  
                            //根据字符串生成JSON对象  
                            JSONObject resultObj = JSONObject.fromObject(result);  
                            return resultObj;  
                        }else{  
                            return null;  
                        }  
                    } else {  
                        throw new ClientProtocolException("Unexpected response status: " + status);  
                    }  
                }  
              
            };  
            //返回的json对象  
            JSONObject responseBody = httpclient.execute(httpPost, responseHandler);  
            System.out.println(responseBody);  
            int result= (Integer) responseBody.get("errcode");  
            if(0==result){  
                flag=true;  
            }else{  
                flag=false;  
            }  
            httpclient.close();  
        } catch (Exception e) {  
        // TODO Auto-generated catch block  
            e.printStackTrace();  
            return false;  
        }  
        return true;  
    }
    
    /** 
     * 获得异步任务结果 
     */  
    public JSONObject getResultByjobid(String jobid){  
        //消息json格式  
        //获得token  
        String token="";
         try {  
             CloseableHttpClient httpclient = HttpClients.createDefault();  
             HttpPost httpPost= new HttpPost("https://qyapi.weixin.qq.com/cgi-bin/batch/getresult?access_token="+token+"&jobid="+jobid);  
             // Create a custom response handler  
            ResponseHandler<JSONObject> responseHandler = new ResponseHandler<JSONObject>() {  
  
                public JSONObject handleResponse(  
                        final HttpResponse response) throws ClientProtocolException, IOException {  
                    int status = response.getStatusLine().getStatusCode();  
                    if (status >= 200 && status < 300) {  
                        HttpEntity entity = response.getEntity();  
                        if(null!=entity){  
                            String result= EntityUtils.toString(entity);  
                            //根据字符串生成JSON对象  
                            JSONObject resultObj = JSONObject.fromObject(result);  
                            return resultObj;  
                        }else{  
                            return null;  
                        }  
                    } else {  
                        throw new ClientProtocolException("Unexpected response status: " + status);  
                    }  
                }  
  
            };  
          //返回的json对象  
            JSONObject responseBody = httpclient.execute(httpPost, responseHandler);  
            System.out.println(responseBody.toString());  
            return responseBody;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    
}
