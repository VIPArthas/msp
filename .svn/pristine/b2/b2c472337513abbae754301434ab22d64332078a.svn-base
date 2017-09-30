package com.wh.service.rgpp;

import com.wh.entity.TbWchatuser;
import com.wh.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface WchatuserPlatService {
	
	/**
     * 根据OpenId查询微信用户
     *
     * @param openId
     * @return
     */
     TbWchatuser findWchatUserByOpenId(String openId);
    /**
     * 根据OpenId查询真实用户Id
     *
     * @param openId
     * @return
     */
     String queryUserIdByOpenId(String openId);
    /**
     * 根据用户OpenId查询所属平台Id
     *
     * @param openId
     * @return
     */
     String queryPlatformIdByOpenId(String openId);
    /**
     * 根据用户OpenId查询平台类型
     * @param openId
     * @return
     */
     String queryPlatformTypeByOpenId(String openId);
    /**
     * 更新用户信息
     *
     * @param tbWchatuser
     */
     void update(TbWchatuser tbWchatuser);
    /**
     * 添加一个微信用户
     *
     * @param tbWchatuser 微信用户
     * @param appAccout   平台微信账户
     */
     void add(TbWchatuser tbWchatuser, String appAccout);

    /**
     * 必须包括platformId
     *
     * @param tbWchatuser
     */
     void add(TbWchatuser tbWchatuser);

    /**
     *
     * @param tbWchatuser
     */
    void saveOrAddBySns(TbWchatuser tbWchatuser);
    /**
     * 根据真实用户Id查询下所有的微信用户信息
     * @param user_id
     * @return
     */
     public List<TbWchatuser> queryByUserId(String user_id);
     
     /**
      * 查询wchatUser中用户的地理位置信息
      * @return
      * @author 徐优优
      * @date 2016年8月22日
      */
     List<Map<String, Object>> selectWchatUserAddress();

    void saveOrUpdate(TbWchatuser tbWchatuser, String appAccout);

    /**
     * 更新没有资料的用户
     */
    void updateMemberInfo();

    /**
     * 根据openid批量更新状态
     * @param tbWchatuser
     */
    void updateStatusByOpenId(TbWchatuser tbWchatuser);


    /**
     * 根据真实用户Id查询他的微信用户信息
     * @param user_id
     * @return
     */
    public TbWchatuser searchByUserId(String user_id);
    
    void deleteUserByUserId(String userId);
    
	void deleteUserByOpenId(String openId);
	/**
	 * 
	 * @param wchatUsers
	 */
	void updateOpenIdNull(String openId) throws ServiceException;
}
