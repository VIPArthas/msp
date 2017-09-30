package com.wh.util;

import com.wh.controller.xlwapp.SendMsgController;
import com.wh.entity.SmsVerify;
import com.wh.entity.TbPlatform;
import com.wh.message.custom.BaseMessage;
import com.wh.message.custom.CustomAccount;
import com.wh.pojo.AccessToken;
import com.wh.pojo.MemberInfo;
import com.wh.pojo.Menu;
import com.wh.service.rgpp.PlatformService;

import net.sf.json.JSON;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信多公众平台相关接口调用
 * Created by danding on 2015/8/17.
 */
public class WeiXinPlatformUtil {
    public static String headImageLocation = PropertiesUtil.getPropertie("WEB-INF/classes/config.properties", "headImageLocation");
    //微信平台信息管理
    public static PlatformService platformService;

    private static Logger log = LoggerFactory.getLogger(WeiXinPlatformUtil.class);
    // 获取access_token的接口地址（GET） 限200（次/天）
    private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // 获取用户基本信息
    private final static String REQUEST_MEMBER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    //拉取用户信息(需scope为 snsapi_userinfo)
    private final static String SNSAPI_MEMBER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    // 菜单创建（POST） 限100（次/天）
    private final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    // 菜单删除
    private final static String MENU_DEL_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    // 客服向用户发送消息
    private final static String CUSTOM_SEND_MSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
    // 添加客服
    private final static String CUSTOM_ADD_URL = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
    // 通过用户授权，获得用户openId
    private final static String REQUEST_OPENID_CODE = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    // 刷新access_token的URL（如果需要）
    private final static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    // 获得素材列表
    private final static String BATCHGET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
    // 群发图文消息
    private final static String SENDALL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
    // JS-SDK使用权限签名算法
    private final static String GETTICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    // 创建分组
    private final static String GROUP_CREATE = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
    // 查询所有分组
    private final static String GROUPS_QUERY = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
    // 查询用户所在分组
    private final static String GROUP_QUERT_BY_USER = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
    // 更新分组名称
    private final static String GROUP_UPDATE = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
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
    // 获得音频或图片文件
    private final static String MEDIA_GET = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
    //获取企业号成员信息
    private final static String REQUEST_USERINFO_QIYE = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
    //userid转换openid接口
    private final static String USERID_CONVERT_TO_OPENID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token=ACCESS_TOKEN";
    //获取用户列表
    private final static String GET_GROUP_USER = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
    //静默授权
    private final static String snsapi_base = "snsapi_base";
    //页面点击授权
    private final static String snsapi_userinfo = "snsapi_userinfo";
    /**
     * 获取access_token的接口地址（GET方式）限2000（次/天）
     *
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;
        String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null, appid, appsecret);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
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
     *
     * @return
     */
    public static MemberInfo getMemberInfo(String openid, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");
        MemberInfo memberInfo = null;
        String requestUrl = REQUEST_MEMBER_INFO.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);

        JSONObject jsonObject = httpRequest(requestUrl, "GET", null, appId, appsecret);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                if (1 == jsonObject.getInt("subscribe")) {
                    memberInfo = new MemberInfo();
                    memberInfo.setSubscribe(jsonObject.getInt("subscribe"));
                    memberInfo.setOpenid(jsonObject.getString("openid"));//用户的唯一标识，对当前公众号唯一
                    memberInfo.setNickname(jsonObject.getString("nickname"));
                    memberInfo.setSex(jsonObject.getInt("sex"));
                    memberInfo.setCountry(jsonObject.getString("country"));
                    memberInfo.setProvince(jsonObject.getString("province"));
                    memberInfo.setCity(jsonObject.getString("city"));
                    memberInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
                    memberInfo.setSubscribeTime(jsonObject.getLong("subscribe_time"));
                }else{
                    log.error("获取用户信息失败，失败原因：该用户未关注校联网公众平台，拉取不到该用户的信息");
                }
            } catch (JSONException e) {
                memberInfo = null;
                // 获取用户信息失败
                log.error("获取用户信息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return memberInfo;
    }

    /**
     * 获取用户基本信息(页面点击授权，未关注)
     *
     * @return
     */
    public static MemberInfo getMemberInfoBySNS(String openid, String platformId, String access_token) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");
        MemberInfo memberInfo = null;
        String requestUrl = SNSAPI_MEMBER_INFO.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);

        JSONObject jsonObject = httpRequest(requestUrl, "GET", null, appId, appsecret);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                if (!StringUtils.isEmpty(jsonObject.getString("openid"))) {
                    memberInfo = new MemberInfo();
                    memberInfo.setSubscribe(-1);
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
        return memberInfo;
    }

    public static JSONObject getGroupUser(String platformId, String nextOpenId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        int result = 0;
        //拼装创建菜单的url
        String url = GET_GROUP_USER.replace("ACCESS_TOKEN", access_token).replace("NEXT_OPENID", nextOpenId);
        JSONObject jsonObject = httpRequest(url, "GET", null, appId, appsecret);

        return jsonObject;
    }
    /**
     * 添加客服账户
     *
     * @param account
     * @return
     */
    public static int addCustomAccount(CustomAccount account, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        int result = 0;
        //拼装创建菜单的url
        String url = CUSTOM_ADD_URL.replace("ACCESS_TOKEN", access_token);
        //将菜单对象转换成json字符串
        String json = JSONObject.fromObject(account).toString();
        log.info("customAccount:{}", json);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", json, appId, appsecret);
        if (jsonObject != null) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("添加客服失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 群发消息
     *
     * @return
     */
    public static void sendAll(final BaseMessage message, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        String url = SENDALL.replace("ACCESS_TOKEN", access_token);
        String jsonMsg = JSONObject.fromObject(message).toString();
        JSONObject jsonObject = httpRequest(url, "POST", jsonMsg, appId, appsecret);
        if (jsonObject != null) {
            if (0 != jsonObject.getInt("errcode")) {
                log.error("群发消息失败 errorcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
    }

    /**
     * 通过客服向用户发送消息
     *
     * @param message
     * @return
     */
    public static void sendCustomMsg(final BaseMessage message, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        final String access_token = (String) map.get("access_token");
        final String appId = (String) map.get("appId");
        final String appsecret = (String) map.get("appSecret");

        new Thread() {
            @Override
            public void run() {
                log.info("当前access_token:{}", access_token);
                //拼装创建菜单的url
                String url = CUSTOM_SEND_MSG.replace("ACCESS_TOKEN", access_token);
                //将菜单对象转换成json字符串
                String jsonMsg = JSONObject.fromObject(message).toString();
                log.info("sendCustomMessage:{}", jsonMsg);
                //调用接口创建菜单
                JSONObject jsonObject = httpRequest(url, "POST", jsonMsg, appId, appsecret);
                if (jsonObject != null) {
                    if (0 != jsonObject.getInt("errcode")) {
                        log.error("客服发送消息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
                    }
                }
            }
        }.start();
    }

    /**
     * 根据用户授权后的code获得用户openId
     *
     * @param code
     * @return
     */
    public static JSONObject requestOpenIdByCode(String code, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appid = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        //拼装创建菜单的url
        String url = REQUEST_OPENID_CODE.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "GET", null, appid, appsecret);

        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                log.error("网页授权请求openId失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return jsonObject;
    }

    /**
     * 根据用户的授权后的code获得用户openId
     *
     * @param code
     * @param platform
     * @return
     * @author 徐优优
     * @date 2016年5月9日
     */
    public static JSONObject getOpenIdStrByCode(String code, TbPlatform platform) {

        //调用接口创建菜单
        JSONObject jsonObject = requestOpenIdByCode(code, platform.getId());
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                log.error("网页授权请求openId失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }

        }
        return jsonObject;
    }

    /**
     * 创建菜单
     *
     * @return
     */
    public static int createMenu(Menu menu, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        int result = 0;
        //拼装创建菜单的url
        String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", access_token);
        //将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        log.info("menu:{}", jsonMenu);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu, appId, appsecret);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 创建菜单(json)
     *
     * @return
     */
    public static int createMenuJson(JSONObject menu, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        int result = 0;
        //拼装创建菜单的url
        String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", access_token);
        //将菜单对象转换成json字符串
        String jsonMenu = menu.toString();
        log.info("menu:{}", jsonMenu);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu, appId, appsecret);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 删除所有菜单
     *
     * @return
     */
    public static int menuDel(String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        int result = 0;
        //拼装删除菜单的url
        String url = MENU_DEL_URL.replace("ACCESS_TOKEN", access_token);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "GET", "", appId, appsecret);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 创建个性化菜单
     *
     * @return
     */
    public static int addMenuAddConditional(Menu menu, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        int result = 0;
        //拼装创建菜单的url
        String url = Menu_ADDCONDITIONAL_ADD.replace("ACCESS_TOKEN", access_token);
        //将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        log.info("AddconditonMenu:{}", jsonMenu);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu, appId, appsecret);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("创建个性化菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 删除个性化菜单
     *
     * @return
     */
    public static int deleteMenuAddConditional(String menuid, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        int result = 0;
        //拼装创建菜单的url
        String url = MENU_ADDCONDITIONAL_DEL.replace("ACCESS_TOKEN", access_token);
        //将菜单对象转换成json字符串
        String jsonMenu = "{\"menuid\":\"" + menuid + "\"}";
        log.info("AddconditonMenu:{}", jsonMenu);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu, appId, appsecret);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("删除个性化菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 获得图文素材信息
     *
     * @param offset
     * @param count
     * @return
     */
    public static JSONObject getWchatServerNews(int offset, int count, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        //拼装请求图文素材信息的url
        String url = BATCHGET_MATERIAL.replace("ACCESS_TOKEN", access_token);
        //调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", "{\"type\":\"news\",\"offset\":" + offset + ",\"count\":" + count + "}", appId, appsecret);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                log.error("请求获得图文素材信息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return jsonObject;
    }

    /**
     * JS-SDK使用权限签名算法
     *
     * @return
     */
    public static String getticket(String access_token, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        String url = GETTICKET.replace("ACCESS_TOKEN", access_token);
        JSONObject jsonObject = httpRequest(url, "GET", null, appId, appsecret);
        if (jsonObject != null) {
            if (0 != jsonObject.getInt("errcode")) {
                log.error("JS-SDK使用权限签名算法 errorcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
            if (jsonObject.containsKey("ticket")) {
                String jsapi_ticket = jsonObject.getString("ticket");
                log.info("JS-SDK使用权限签名算法,获得ticket:{}", jsapi_ticket);
                return jsapi_ticket;
            }
        }
        return null;
    }

    /**
     * 创建分组
     *
     * @param groupName 分组名称
     * @return 如：{"group": {"id": 107,"name": "test"}}
     */
    public static JSONObject groupCreate(String groupName, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        String url = GROUP_CREATE.replace("ACCESS_TOKEN", access_token);
        log.info("groupCreate:{}", groupName);
        //调用接口创建分组
        String groupJson = "{\"group\":{\"name\":\"" + groupName + "\"}}";
        JSONObject jsonObject = httpRequest(url, "POST", groupJson, appId, appsecret);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                log.error("分组创建失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            } else {
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 查询所有用户分组信息
     *
     * @return 如：
     * {
     * "groups": [
     * {
     * "id": 0,
     * "name": "未分组",
     * "count": 72596
     * },
     * {
     * "id": 1,
     * "name": "黑名单",
     * "count": 36
     * },
     * {
     * "id": 2,
     * "name": "星标组",
     * "count": 8
     * },
     * {
     * "id": 104,
     * "name": "华东媒",
     * "count": 4
     * },
     * {
     * "id": 106,
     * "name": "★不测试组★",
     * "count": 1
     * }
     * ]
     * }
     */
    public static JSONObject groupsQuery(String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        String url = GROUPS_QUERY.replace("ACCESS_TOKEN", access_token);
        log.info("查询所有用户分组信息");
        //调用接口创建分组
        JSONObject jsonObject = httpRequest(url, "GET", null, appId, appsecret);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                log.error("查询所有用户分组失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            } else {
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 查询用户所在分组
     *
     * @param openId
     * @return 如：{"groupid": 102}
     */
    public static JSONObject groupQueryByUser(String openId, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        String url = GROUP_QUERT_BY_USER.replace("ACCESS_TOKEN", access_token);
        log.info("查询用户所在分组");
        //调用接口创建分组
        JSONObject jsonObject = httpRequest(url, "POST", "{\"openid\":\"" + openId + "\"}", appId, appsecret);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                log.error("查询用户所在分组失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            } else {
                return jsonObject;
            }
        }
        return null;
    }

    /**
     * 更新分组名称
     *
     * @param groupId   分组id
     * @param groupName 分组名称
     */
    public static void groupUpdate(String groupId, String groupName, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        String url = GROUP_UPDATE.replace("ACCESS_TOKEN", access_token);
        log.info("更新分组名称");
        //调用接口创建分组
        JSONObject jsonObject = httpRequest(url, "POST", "{\"group\":{\"id\":" + groupId + ",\"name\":\"" + groupName + "\"}}", appId, appsecret);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                log.error("更新分组名称失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
    }

    /**
     * 移动用户分组
     *
     * @param openId  用户id
     * @param groupId 分组id
     */
    public static void groupMoveMember(String openId, int groupId, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        String url = GROUP_MOVE_USER.replace("ACCESS_TOKEN", access_token);
        log.info("移动用户分组,openId:{},groupId:{}", openId, groupId);
        //调用接口创建分组
        JSONObject jsonObject = httpRequest(url, "POST", "{\"openid\":\"" + openId + "\",\"to_groupid\":" + groupId + "}", appId, appsecret);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                log.error("移动用户分组失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
    }

    /**
     * 批量移动用户分组
     *
     * @param openIds 用户id数组（数组长度最长为50）
     * @param groupId 分组id
     */
    public static void groupMoveMembers(String[] openIds, String groupId, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        String url = GROUP_MOVE_USERS.replace("ACCESS_TOKEN", access_token);
        log.info("批量移动用户分组");
        StringBuilder openIdBuilder = new StringBuilder();
        for (String openId : openIds) {
            openIdBuilder.append(",\"" + openId + "\"");
        }
        String openIdTemp = "";
        if (openIdBuilder.toString().length() > 0) {
            openIdTemp = openIdBuilder.substring(1);
        }
        //调用接口创建分组
        JSONObject jsonObject = httpRequest(url, "POST", "{\"openid_list\":[" + openIdTemp + "],\"to_groupid\":" + groupId + "}", appId, appsecret);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                log.error("批量移动用户分组失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
    }

    /**
     * 删除用户分组
     *
     * @param groupId 分组id
     */
    public static void groupDelete(String groupId, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String appId = (String) map.get("appId");
        String appsecret = (String) map.get("appSecret");

        String url = GROUP_DELETE.replace("ACCESS_TOKEN", access_token);
        log.info("删除用户分组");
        //调用接口创建分组
        JSONObject jsonObject = httpRequest(url, "POST", "{\"group\":{\"id\":" + groupId + "}}", appId, appsecret);
        if (jsonObject != null) {
            if (jsonObject.containsKey("errcode") && 0 != jsonObject.getInt("errcode")) {
                log.error("删除用户分组失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
    }

    /**
     * 下载多媒体文件
     *
     * @param media
     * @param platformId
     */
    public static String media_download(String media, String platformId) {
        Map map = platformService.getPlatFromInfoByPlatformId(platformId);
        String access_token = (String) map.get("access_token");
        String name = "";
        String urlPath = MEDIA_GET.replace("ACCESS_TOKEN", access_token).replace("MEDIA_ID", media);
        log.info("下载多媒体文件,media:{},platformId:{}", media, platformId);
        try {
            URL url = new URL(urlPath);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(5000);

            InputStream is = conn.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            String disposition = conn.getHeaderField("Content-disposition");
            name = System.currentTimeMillis() + disposition.substring(disposition.indexOf("."));
            name = name.substring(0, name.length() - 1);
            File sf = new File(headImageLocation);
            if (!sf.exists()) {
                sf.mkdirs();
            }
            OutputStream os = new FileOutputStream(sf.getPath() + "\\" + name);
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return name;
    }

    /**
     * 发https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject
     */
    private static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr, String appId, String appsecret) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
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
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());

            if (jsonObject != null) {
                if (jsonObject.containsKey("errcode") && (40001 == jsonObject.getInt("errcode") || 40014 == jsonObject.getInt("errcode") || 42001 == jsonObject.getInt("errcode"))) {
                    TbPlatform tbPlatform = platformService.getPlatFormByAppId(appId);
                    Date lasttokentime = tbPlatform.getLasttokentime();
                    Long duringSeconds = DateUtil.secondsBetween(lasttokentime, new Date());
                    if (duringSeconds < 60) {
                        log.info("微信服务器返回数据：{}", jsonObject);
                        return jsonObject;
                    }
                    //   log.error("微信平台:{},access_token使用过程中出现无效现象， errcode:{} errmsg:{}",appId,jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
                    AccessToken accessToken = getAccessToken(appId, appsecret);
                    String js_ticket = getticket(accessToken.getToken(), tbPlatform.getId());
                    platformService.updateAccessTokenByAppId(appId, accessToken.getToken(), js_ticket);
                    //发送失败重新进行发送
                    //jsonObject = httpRequest(requestUrl,requestMethod,outputStr);
                    requestUrl = replaceNewAccessToken(requestUrl, accessToken.getToken());
                    httpRequest(requestUrl, requestMethod, outputStr, appId, appsecret);
                }
                log.info("微信服务器返回数据：{}", jsonObject);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * JS签名
     *
     * @return
     */
    public static String getJS_signature(String noncestr, String timestamp, String url, String jsapi_ticket) {
        String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
        log.info("getJS_signature----input--->:{}", str);
        String result = EncoderHandler.sha1(str);
        result = result.toLowerCase();
        log.info("getJS_signature:{}", result);
        return result;
    }

    public static void reqeustCode(ServletResponse response, HttpServletRequest req, TbPlatform tbPlatform)
            throws UnsupportedEncodingException, IOException {
    	String requestPath = WebUtil.getRequestPath(req);
        String share = req.getParameter("share");
        String req_uri = req.getRequestURI();
        String param = req.getQueryString();
        if (param != null && !"".equals(param.trim())) {
            req_uri = req_uri + "?" + param;
        }
			 
        //授权登录，不需要关注
        if (!StringUtils.isEmpty(share) && "share".equals(share)) {     
            req_uri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + tbPlatform.getAppid()
                        + "&redirect_uri=" + URLEncoder.encode(Constants.BASE_SERVER + req_uri, "utf-8")
                        + "&response_type=code&scope=snsapi_userinfo#wechat_redirect";
        
        }else { 
            //snsapi_userinfo
            req_uri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + tbPlatform.getAppid()
                    + "&redirect_uri=" + URLEncoder.encode(Constants.BASE_SERVER + req_uri, "utf-8")
                    + "&response_type=code&scope=snsapi_base#wechat_redirect";
        }
        
        ((HttpServletResponse) response).sendRedirect(req_uri);
    }

    /**
     * 将url中老的accessToken替换为新的accessToken
     *
     * @param url
     * @param accessToken
     * @return
     * @author 徐优优
     * @date 2016年5月9日
     */
    public static String replaceNewAccessToken(String url, String accessToken) {
        int a = url.indexOf("access_token=");
        int b = url.indexOf("&", a);
        String access_tokenOld = url.substring(a + "access_token=".length(), b);
        url = url.replace(access_tokenOld, accessToken);
        return url;
    }

    /**
     * 发送模板消息
     * tempType 1:绑定通知
     *
     * @param temMap
     * @param tempType
     * @param platformId
     * @return
     * @autor lyk
     * @date 2016年10月12日
     */
    public static String sendTemplate(Map<String, Object> temMap, String tempType, String platformId) {
        try {
            if (StringUtils.isEmpty(platformId)) {
                platformId = "1";
            }
            Map map = platformService.getPlatFromInfoByPlatformId(platformId);

            String access_token = (String) map.get("access_token");
            String appId = (String) map.get("appId");
            String appsecret = (String) map.get("appSecret");
            JSONObject dataMap = new JSONObject();
            dataMap.put("touser", temMap.get("toOpenId"));
            dataMap.put("url", temMap.get("url"));
            dataMap.put("topcolor", "#FF0000");
            /** 会议通知
             */
            if (!StringUtils.isEmpty(tempType) && tempType.equals("5")) {
                // 测试的模板 BcUCwiBjasBcms4chggNth1ufO3F9gxvhnojz4lyXNM
                //正式的 ZIa7ktq9DO6R6JQEn8Xj6HQwXWfKvysHnClFlgg3kFU
                dataMap.put("template_id", Constants.HUI_YI_ID);
                JSONObject first = new JSONObject();
                JSONObject data = new JSONObject();
                JSONObject keyword1 = new JSONObject();
                JSONObject keyword2 = new JSONObject();
                JSONObject keyword3 = new JSONObject();
                JSONObject keyword4 = new JSONObject();
                JSONObject remark = new JSONObject();


                keyword1.put("value",temMap.get("parm2"));//会议名称
                keyword2.put("value",temMap.get("parm3")); //会议时间
                keyword3.put("value",temMap.get("parm4")); //会议地点
                keyword4.put("value",temMap.get("parm5")); //会议介绍
                remark.put("value","请准时到会，不要迟到");
                first.put("value", "您有一个会议需要参加");

                data.put("first", first);
                data.put("keyword1", keyword1);
                data.put("keyword2", keyword2);
                data.put("keyword3", keyword3);
                data.put("keyword4", keyword4);
                data.put("remark", remark);
                dataMap.put("data", data);
            }
            /**薪资发放
             */
            if (!StringUtils.isEmpty(tempType) && tempType.equals("6")) {
                //测试的	zHoaLTGHeg0Wb7agNw9Nlu2Rga-V0S5z6vzoXmPlCy4
                //正式的 SzSuUdn7g4wXIItg-hTJDTewlvS_fOo1MT-__Gf-5Co
                dataMap.put("template_id",Constants.XIN_ZI_ID);
                JSONObject first = new JSONObject();
                JSONObject data = new JSONObject();
                JSONObject keyword1 = new JSONObject();
                JSONObject keyword2 = new JSONObject();
                JSONObject keyword3 = new JSONObject();
                JSONObject keyword4 = new JSONObject();
                JSONObject remark = new JSONObject();


                keyword1.put("value",temMap.get("parm2")+"份工行工资");//名称
                keyword2.put("value",temMap.get("parm3")); //应发工资
                keyword3.put("value",temMap.get("parm4")); //实发工资
                keyword4.put("value",DateUtil.format(new Date(), "yyyy年MM月dd日 HH:mm")); //时间
                remark.put("value","如有疑问请咨询各财务中心");
                first.put("value","您好，您的工资已经发放");

                data.put("first", first);
                data.put("keyword1", keyword1);
                data.put("keyword2", keyword2);
                data.put("keyword3", keyword3);
                data.put("keyword4", keyword4);
                data.put("remark", remark);
                dataMap.put("data", data);
            }
            /**周师修改日志通知模板
             */
            if (!StringUtils.isEmpty(tempType) && tempType.equals("7")) {
                //测试的	zHoaLTGHeg0Wb7agNw9Nlu2Rga-V0S5z6vzoXmPlCy4
                //正式的 SzSuUdn7g4wXIItg-hTJDTewlvS_fOo1MT-__Gf-5Co
                dataMap.put("template_id",Constants.ZS_XGRZ_ID);
                JSONObject first = new JSONObject();
                first.put("value","您好，"+temMap.get("parm1")+"提交了信息变更申请");
                
                JSONObject keyword1 = new JSONObject();
                keyword1.put("value",temMap.get("parm2"));//基本信息-个人资料-性别
                
                JSONObject keyword2 = new JSONObject();
                keyword2.put("value",temMap.get("parm3")); //“男”改为“女”
                
                JSONObject remark = new JSONObject();
                remark.put("value","请及时处理，谢谢");
                
                JSONObject data = new JSONObject();
                data.put("first", first);
                data.put("keyword1", keyword1);
                data.put("keyword2", keyword2);
                data.put("remark", remark);
                dataMap.put("data", data);
            }
            String retUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
            log.info("发送模板数据--->{}", dataMap.toString());
            JSONObject object = httpRequest(retUrl, "POST", dataMap.toString(), appId, appsecret);
            Integer errcode = object.getInt("errcode");
            if (errcode != 0) {
                SmsVerify smsVerify = new SmsVerify("", "15039006107", dataMap.toString(), "", null, "", null);
                new MessageCode().sendTemplaMsg(smsVerify);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
