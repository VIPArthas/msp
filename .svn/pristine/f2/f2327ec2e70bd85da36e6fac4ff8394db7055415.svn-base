package com.wh.util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wh.message.custom.BaseMessage;
import com.wh.message.custom.CustomAccount;
import com.wh.pojo.AccessToken;
import com.wh.pojo.MemberInfo;
import com.wh.pojo.Menu;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 微信相关接口调用
 * Created by danding on 2015/8/17.
 */
public class WeiXinUtil {
    public static String appid;//凭证
    public static String appsecret;//凭证密钥
    //    public static String createNonceStr;
    public static String jsapi_ticket;
    //    public static String createTimeStamp; 
    private static String access_token;
    private static int count;
    public static JSONObject groups;//微信分组信息
    private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);
    // 获取access_token的接口地址（GET） 限200（次/天）
    private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // 获取用户基本信息
    private final static String REQUEST_MEMBER_INFO= "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    // 菜单创建（POST） 限100（次/天）
    private final static String MENU_CREATE_URL= "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    // 菜单删除
    private final static String MENU_DEL_URL="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    // 客服向用户发送消息
    private final static String CUSTOM_SEND_MSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
    // 添加客服
    private final static String CUSTOM_ADD_URL= "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
    // 通过用户授权，获得用户openId
    private final static String REQUEST_OPENID_CODE = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    // 获得素材列表
    private final static String BATCHGET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
    // 群发图文消息
    private final static String SENDALL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
    // JS-SDK使用权限签名算法
    private final static String GETTICKET ="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    // 创建分组
    private final static String GROUP_CREATE = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
    // 查询所有分组
    private final static String GROUPS_QUERY = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
    // 查询用户所在分组
    private final static String GROUP_QUERT_BY_USER = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
    // 更新分组名称
    private final static String GROUP_UPDATE= "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
    // 移动用户分组
    private final static String GROUP_MOVE_USER = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
    // 批量移动用户分组
    private final static String GROUP_MOVE_USERS = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN";
    // 删除分组
    private final static String GROUP_DELETE = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";
    // 创建个性化菜单
    private final static String Menu_ADDCONDITIONAL_ADD = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";
    // 删除个性化菜单
    private final static String MENU_ADDCONDITIONAL_DEL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN";
    /**
     * 获取access_token的接口地址（GET方式）限2000（次/天）
     * @return
     */
    public static AccessToken getAccessToken(){
        count++;
        AccessToken accessToken = null;
        log.info("AccessToken使用次数为-----------------》》》{}",count);
        String requestUrl = ACCESS_TOKEN_URL.replace("APPID",appid).replace("APPSECRET",appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
                access_token = accessToken.getToken();
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }

    /**
     * 获取用户基本信息
     * @return
     */
    public static MemberInfo getMemberInfo(String openid){
        MemberInfo memberInfo = null;
        String requestUrl = REQUEST_MEMBER_INFO.replace("ACCESS_TOKEN", access_token).replace("OPENID",openid);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if(null!=jsonObject){
            try{
                if(1==jsonObject.getInt("subscribe")){
                    memberInfo = new MemberInfo();
                    memberInfo.setSubscribe(jsonObject.getInt("subscribe"));
                    memberInfo.setOpenid(jsonObject.getString("openid"));//用户的唯一标识，对当前公众号唯一
                    memberInfo.setNickname(jsonObject.getString("nickname"));
                    memberInfo.setSex(jsonObject.getInt("sex"));
                    memberInfo.setCountry(jsonObject.getString("country"));
                    memberInfo.setProvince(jsonObject.getString("province"));
                    memberInfo.setCity(jsonObject.getString("city"));
                    memberInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
                }else{
                    log.error("获取用户信息失败，失败原因：该用户未关注校联网公众平台，拉取不到该用户的信息");
                }
            } catch (JSONException e) {
                memberInfo = null;
                // 获取用户信息失败
                log.error("获取用户信息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return  memberInfo;
    }

    /**
     * 添加客服账户
     * @param account
     * @return
     */
    public static int addCustomAccount(CustomAccount account){
        int result = 0;
        //拼装创建菜单的url
        String url = CUSTOM_ADD_URL.replace("ACCESS_TOKEN", access_token);
        //将菜单对象转换成json字符串
        String json = JSONObject.fromObject(account).toString();
        log.info("customAccount:{}",json);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url,"POST",json);
        if(jsonObject!=null){
            if(0!=jsonObject.getInt("errcode")){
                result = jsonObject.getInt("errcode");
                log.error("添加客服失败 errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 群发消息
     * @return
     */
    public static void sendAll(final BaseMessage message){
        String url = SENDALL.replace("ACCESS_TOKEN",access_token);
        String jsonMsg = JSONObject.fromObject(message).toString();
        JSONObject jsonObject = httpRequest(url,"POST",jsonMsg);
        if(jsonObject != null) {
            if (0 != jsonObject.getInt("errcode")) {
                log.error("群发消息失败 errorcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
    }
    /**
     * 通过客服向用户发送消息
     * @param message
     * @return
     */
    public static void sendCustomMsg(final BaseMessage message){
        new Thread(){
            @Override
            public void run() {
                log.info("当前access_token:{}",access_token);
                //拼装创建菜单的url
                String url = CUSTOM_SEND_MSG.replace("ACCESS_TOKEN", access_token);
                //将菜单对象转换成json字符串
                String jsonMsg = JSONObject.fromObject(message).toString();
                log.info("sendCustomMessage:{}",jsonMsg);
                //调用接口创建菜单
                JSONObject jsonObject = httpRequest(url,"POST",jsonMsg);
                if(jsonObject!=null){
                    if(0!=jsonObject.getInt("errcode")){
                        log.error("客服发送消息失败 errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
                    }
                }
            }
        }.start();
    }

    /**
     * 根据用户授权后的code获得用户openId
     * @param code
     * @return
     */
    public static JSONObject requestOpenIdByCode(String code){
        //拼装创建菜单的url
        String url = REQUEST_OPENID_CODE.replace("APPID",appid).replace("SECRET", appsecret).replace("CODE", code);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url,"GET",null);

        if(jsonObject!=null){
            if( jsonObject.containsKey("errcode") && 0!=jsonObject.getInt("errcode")){
                log.error("网页授权请求openId失败 errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
            }
        }
        return jsonObject;
    }
    /**
     * 创建菜单
     * @return
     */
    public static int createMenu(Menu menu){
        int result = 0;
        //拼装创建菜单的url
        String url = MENU_CREATE_URL.replace("ACCESS_TOKEN",access_token);
        //将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        log.info("menu:{}",jsonMenu);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url,"POST",jsonMenu);
        if(jsonObject!=null){
            if(jsonObject.containsKey("errcode") && 0!=jsonObject.getInt("errcode")){
                result = jsonObject.getInt("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 删除所有菜单
     * @return
     */
    public static int menuDel(){
        int result = 0;
        //拼装删除菜单的url
        String url = MENU_DEL_URL.replace("ACCESS_TOKEN", access_token);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url,"GET","");
        if(jsonObject!=null){
            if(jsonObject.containsKey("errcode") && 0!=jsonObject.getInt("errcode")){
                result = jsonObject.getInt("errcode");
                log.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }
    /**
     * 创建个性化菜单
     * @return
     */
    public static int addMenuAddConditional(Menu menu){
        int result = 0;
        //拼装创建菜单的url
        String url = Menu_ADDCONDITIONAL_ADD.replace("ACCESS_TOKEN",access_token);
        //将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        log.info("AddconditonMenu:{}",jsonMenu);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url,"POST",jsonMenu);
        if(jsonObject!=null){
            if(jsonObject.containsKey("errcode") && 0!=jsonObject.getInt("errcode")){
                result = jsonObject.getInt("errcode");
                log.error("创建个性化菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }
    /**
     * 删除个性化菜单
     * @return
     */
    public static int deleteMenuAddConditional(String menuid){
        int result = 0;
        //拼装创建菜单的url
        String url = MENU_ADDCONDITIONAL_DEL.replace("ACCESS_TOKEN",access_token);
        //将菜单对象转换成json字符串
        String jsonMenu = "{\"menuid\":\""+menuid+"\"}";
        log.info("AddconditonMenu:{}",jsonMenu);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url,"POST",jsonMenu);
        if(jsonObject!=null){
            if(jsonObject.containsKey("errcode") && 0!=jsonObject.getInt("errcode")){
                result = jsonObject.getInt("errcode");
                log.error("删除个性化菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }
    /**
     * 获得图文素材信息
     * @param offset
     * @param count
     * @return
     */
    public static JSONObject getWchatServerNews(int offset,int count){
        //拼装请求图文素材信息的url
        String url = BATCHGET_MATERIAL.replace("ACCESS_TOKEN", access_token);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url,"POST","{\"type\":\"news\",\"offset\":"+offset+",\"count\":"+count+"}");
        if(jsonObject!=null){
            if( jsonObject.containsKey("errcode")&& 0!=jsonObject.getInt("errcode")){
                log.error("请求获得图文素材信息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return jsonObject;
    }

    /**
     * JS-SDK使用权限签名算法
     * @return
     */
    public static void getticket(){
        String url = GETTICKET.replace("ACCESS_TOKEN", access_token);
        JSONObject jsonObject = httpRequest(url,"GET",null);
        if(jsonObject != null) {
            if (0 != jsonObject.getInt("errcode")) {
                log.error("JS-SDK使用权限签名算法 errorcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
            if(jsonObject.containsKey("ticket")){
                jsapi_ticket = jsonObject.getString("ticket");
                log.info("JS-SDK使用权限签名算法,获得ticket:{}", jsapi_ticket);
            }
        }
    }

    /**
     * 创建分组
     * @param groupName 分组名称
     * @return 如：{"group": {"id": 107,"name": "test"}}
     */
    public static JSONObject groupCreate(String groupName){
        String url = GROUP_CREATE.replace("ACCESS_TOKEN", access_token);
        log.info("groupCreate:{}",groupName);
        //调用接口创建分组
        String groupJson = "{\"group\":{\"name\":\""+ groupName + "\"}}";
        JSONObject jsonObject = httpRequest(url,"POST",groupJson);
        if(jsonObject!=null){
            if(jsonObject.containsKey("errcode") && 0!=jsonObject.getInt("errcode")){
                log.error("分组创建失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }else{
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 查询所有用户分组信息
     * @return 如：
     * {
    "groups": [
    {
    "id": 0,
    "name": "未分组",
    "count": 72596
    },
    {
    "id": 1,
    "name": "黑名单",
    "count": 36
    },
    {
    "id": 2,
    "name": "星标组",
    "count": 8
    },
    {
    "id": 104,
    "name": "华东媒",
    "count": 4
    },
    {
    "id": 106,
    "name": "★不测试组★",
    "count": 1
    }
    ]
    }
     */
    public static JSONObject groupsQuery(){
        String url = GROUPS_QUERY.replace("ACCESS_TOKEN", access_token);
        log.info("查询所有用户分组信息");
        //调用接口创建分组
        JSONObject jsonObject = httpRequest(url,"GET",null);
        if(jsonObject!=null){
            if(jsonObject.containsKey("errcode") && 0!=jsonObject.getInt("errcode")){
                log.error("查询所有用户分组失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }else{
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 查询用户所在分组
     * @param openId
     * @return 如：{"groupid": 102}
     */
    public static JSONObject groupQueryByUser(String openId){
        String url = GROUP_QUERT_BY_USER.replace("ACCESS_TOKEN", access_token);
        log.info("查询用户所在分组");
        //调用接口创建分组
        JSONObject jsonObject = httpRequest(url,"POST","{\"openid\":\""+openId+"\"}");
        if(jsonObject!=null){
            if(jsonObject.containsKey("errcode") && 0!=jsonObject.getInt("errcode")){
                log.error("查询用户所在分组失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }else{
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 更新分组名称
     * @param groupId 分组id
     * @param groupName 分组名称
     */
    public static void groupUpdate(String groupId,String groupName){
        String url = GROUP_UPDATE.replace("ACCESS_TOKEN", access_token);
        log.info("更新分组名称");
        //调用接口创建分组
        JSONObject jsonObject = httpRequest(url,"POST","{\"group\":{\"id\":" + groupId + ",\"name\":\""+ groupName +"\"}}");
        if(jsonObject!=null){
            if(jsonObject.containsKey("errcode") && 0!=jsonObject.getInt("errcode")){
                log.error("更新分组名称失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
    }

    /**
     * 移动用户分组
     * @param openId 用户id
     * @param groupId 分组id
     */
    public static void groupMoveMember(String openId,int groupId){
        String url = GROUP_MOVE_USER.replace("ACCESS_TOKEN", access_token);
        log.info("移动用户分组,openId:{},groupId:{}",openId,groupId);
        //调用接口创建分组
        JSONObject jsonObject = httpRequest(url,"POST","{\"openid\":\""+openId+"\",\"to_groupid\":"+groupId+"}");
        if(jsonObject!=null){
            if(jsonObject.containsKey("errcode") && 0!=jsonObject.getInt("errcode")){
                log.error("移动用户分组失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
    }
    /**
     * 批量移动用户分组
     * @param openIds 用户id数组（数组长度最长为50）
     * @param groupId 分组id
     */
    public static void groupMoveMembers(String[] openIds,String groupId){
        String url = GROUP_MOVE_USERS.replace("ACCESS_TOKEN", access_token);
        log.info("批量移动用户分组");
        StringBuilder openIdBuilder = new StringBuilder();
        for (String openId:openIds){
            openIdBuilder.append(",\"" + openId + "\"");
        }
        String openIdTemp = "";
        if(openIdBuilder.toString().length()>0){
            openIdTemp = openIdBuilder.substring(1);
        }
        //调用接口创建分组
        JSONObject jsonObject = httpRequest(url,"POST","{\"openid_list\":[" + openIdTemp + "],\"to_groupid\":"+groupId+"}");
        if(jsonObject!=null){
            if(jsonObject.containsKey("errcode") && 0!=jsonObject.getInt("errcode")){
                log.error("批量移动用户分组失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
    }
    /**
     * 删除用户分组
     * @param groupId 分组id
     */
    public static void groupDelete(String groupId){
        String url = GROUP_DELETE.replace("ACCESS_TOKEN", access_token);
        log.info("删除用户分组");
        //调用接口创建分组
        JSONObject jsonObject = httpRequest(url,"POST","{\"group\":{\"id\":"+ groupId +"}}");
        if(jsonObject!=null){
            if(jsonObject.containsKey("errcode") && 0!=jsonObject.getInt("errcode")){
                log.error("删除用户分组失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
    }
    /**
     * 发https请求并获取结果
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject
     */
    private static JSONObject httpRequest(String requestUrl,String requestMethod, String outputStr){

        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try{
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());

            if(jsonObject!=null){
                if( jsonObject.containsKey("errcode") && (40001==jsonObject.getInt("errcode")||42001==jsonObject.getInt("errcode"))){
                    log.error("access_token使用过程中出现无效现象， errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
                    getAccessToken();
                    //发送失败重新进行发送
//                    jsonObject = httpRequest(requestUrl,requestMethod,outputStr);
                }
                log.info("微信服务器返回数据：{}",jsonObject);
            }

        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * JS签名
     * @return
     */
    public static String getJS_signature(String noncestr,String timestamp,String url){
        String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp="+ timestamp +"&url=" + url;
        log.info("getJS_signature----input--->:{}",str);
        String result = EncoderHandler.sha1(str);
        result = result.toLowerCase();
        log.info("getJS_signature:{}",result);
        return result;
    }

    /**
     * 根据组名称获得组id
     * @param groupName
     * @return
     */
    public static int getGroupIdByName(String groupName){
        JSONArray groupArrays = WeiXinUtil.groups.getJSONArray("groups");
        for (Object object:groupArrays){
            JSONObject json = (JSONObject) object;
            if(groupName.equals(json.get("name"))){
                return json.getInt("id");
            }
        }
        return 0;
    }
}
