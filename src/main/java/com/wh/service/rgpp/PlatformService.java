package com.wh.service.rgpp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wh.entity.TbPlatform;

public interface PlatformService {

    /**
     * 更新微信access_token
     * @param appId
     * @param access_token
     */
     void updateAccessTokenByAppId(String appId,String access_token,String js_ticket);

    /**
     * 根据微信平台id获得平台信息
     * @param platformId
     * @return
     */
     TbPlatform getPlatFromById(String platformId);

    /**
     * 根据微信公众平台appId获得平台信息
     * @param appId
     * @return
     */
     TbPlatform getPlatFormByAppId(String appId);
    /**
     * 根据微信平台Id获得微信配置信息
     * @param platformId
     * @return
     */
     Map getPlatFromInfoByPlatformId(String platformId);

    /**
     * 根据微信账户获得微信配置信息
     * @param appAccout
     * @return
     */
     Map getPlatFromInfoByAccout(String appAccout);

    /**
     * 通过网络更新最新的AccessToken信息，并更新到数据库中
     */
     void updateAccessTokenByNetwork();

    /**
     * 返回所有平台信息
     */
     List queryAll();
}
