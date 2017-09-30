package com.wh.service.rgpp.impl;

import com.wh.dao.wchat.TbPlatformMapper;
import com.wh.entity.TbPlatform;
import com.wh.pojo.AccessToken;
import com.wh.service.rgpp.PlatformService;
import com.wh.util.WeiXinPlatformUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danding on 2016/3/16.
 */
@Service
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    private TbPlatformMapper tbPlatformMapper;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 更新微信access_token和js_ticket
     * @param appId
     * @param access_token
     * @param js_ticket
     */
    public void updateAccessTokenByAppId(String appId,String access_token,String js_ticket){
        TbPlatform tbPlatform = this.tbPlatformMapper.queryByAppId(appId);
        if(tbPlatform!=null){
            tbPlatform.setAccessToken(access_token);
            tbPlatform.setJsapiTicket(js_ticket);
            tbPlatform.setLasttokentime(new Date());
			this.tbPlatformMapper.updateByPrimaryKey(tbPlatform);
        }
    }
    public TbPlatform getPlatFromById(String platformId){
        return this.tbPlatformMapper.queryById(platformId);
    }

    public TbPlatform getPlatFormByAppId(String appId){
        return this.tbPlatformMapper.queryByAppId(appId);
    }
    /**
     * 根据微信平台Id获得微信配置信息
     * @param platformId
     * @return
     */
    public Map getPlatFromInfoByPlatformId(String platformId){
        TbPlatform platform = this.tbPlatformMapper.queryById(platformId);
        if(platform!=null){
            Map map = new HashMap();
            map.put("access_token",platform.getAccessToken());
            map.put("appId", platform.getAppid());
            map.put("appSecret", platform.getAppsecret());
            map.put("id", platform.getId());
            return map;
        }
        return null;
    }

    /**
     * 根据微信账户获得微信配置信息
     * @param appAccout
     * @return
     */
    public Map getPlatFromInfoByAccout(String appAccout){
        TbPlatform platform = this.tbPlatformMapper.queryByAccout(appAccout);
        if(platform!=null){
            Map map = new HashMap();
            map.put("access_token",platform.getAccessToken());
            map.put("appId", platform.getAppid());
            map.put("appSecret", platform.getAppsecret());
            map.put("id", platform.getId());
            return map;
        }
        return null;
    }
    /**
     * 通过网络更新最新的AccessToken信息，并更新到数据库中
     */
    public void updateAccessTokenByNetwork(){
    	 List<TbPlatform> platformList = this.tbPlatformMapper.queryAll();
         Date time = new Date();
         time.setTime(System.currentTimeMillis() - 6600000);
         for(TbPlatform platform:platformList){
             //如果上次获得AccessToken时间，有效期剩余10分钟以内，则重新获得新的AccessToken
             if(platform.getLasttokentime()==null || platform.getLasttokentime().before(time)){
                 log.info("校验微信平台:{},AccessToken已过期,正在重新获取...",platform.getAppaccout());
                 AccessToken accessToken = WeiXinPlatformUtil.getAccessToken(platform.getAppid(), platform.getAppsecret());
                 if(accessToken!=null){
                     String jsTicket = WeiXinPlatformUtil.getticket(accessToken.getToken(),platform.getId());
                     platform.setAccessToken(accessToken.getToken());
                     platform.setLasttokentime(new Date());
                     if(jsTicket!=null && !"".equals(jsTicket)){
                         platform.setJsapiTicket(jsTicket);
                     }
                     this.tbPlatformMapper.updateByPrimaryKey(platform);
                     log.info("AccessToken重新获取成功");
                 }

             }
         }
    }

    /**
     * 返回所有平台信息
     */
    public List queryAll(){
       return this.tbPlatformMapper.queryAll();
    }
}
