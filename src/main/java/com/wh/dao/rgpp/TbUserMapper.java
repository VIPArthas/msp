package com.wh.dao.rgpp;

import com.wh.entity.TbUser;

public interface TbUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbUser record);

    /**
     * 添加用户
     * @param record
     * @return
     */
    int saveUser(TbUser record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    TbUser findUserById(String id);
    /**
     * 根据openId查询
     * @param openId
     * @return
     */
    TbUser findUserByOpenId(String openId);
    /**
     * 根据mobile查询
     * @param mobile
     * @return
     */
    TbUser findUserByMobile(String mobile);

    /**
     * 修改用户
     * @param record
     * @return
     */
    int updateUser(TbUser record);

    int updateByPrimaryKey(TbUser record);
}