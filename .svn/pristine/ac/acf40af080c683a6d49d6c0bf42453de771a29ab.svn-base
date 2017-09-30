package com.wh.dao.xlwapp;

import com.wh.entity.WxRecharge;

public interface WxRechargeMapper {
    int deleteByPrimaryKey(String id);

    int insert(WxRecharge record);

    int insertSelective(WxRecharge record);

    WxRecharge selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WxRecharge record);

    int updateByPrimaryKey(WxRecharge record);

    /**
     * 根据userRechargeId 更新微信订单号
     * @param wxRecharge
     * @return
     */
    int updateByUserRechargeIdSelective(WxRecharge wxRecharge);
}