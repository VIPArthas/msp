package com.wh.service.xlwapp;

import java.util.List;

import com.wh.dto.system.UserSearchDto;
import com.wh.entity.UserRecharge;
import com.wh.util.wxpay.protocol.JSAPIProtocol.JSAPIResData;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.OrderQueryReq;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.OrderQueryRes;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.UnifiedOrderReqData;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.UnifiedOrderResData;

/**
 * Created by Administrator on 2016-4-28.
 */
public interface UserRechargeService {
	
	/**
	 * 新增一条充值记录
	 * @param userRecharge
	 * @throws Exception
	 * @author 徐优优
	 * @date 2016年5月10日
	 */
	public UnifiedOrderResData saveRecharge(UserRecharge userRecharge, UnifiedOrderReqData unifiedOrderReqData) throws Exception;
	
	/**
	 * 统一支付接口返回数据数据库处理
	 * @param jSAPIResData
	 * @author 徐优优
	 * @date 2016年5月10日
	 */
	public void updateJSAPIRechargeResult(JSAPIResData jSAPIResData);
	
	/**
	 * 更新一条充值记录
	 * @param userRecharge
	 * @throws Exception
	 * @author 徐优优
	 * @date 2016年5月10日
	 */
	public void updateRecharge(UserRecharge userRecharge) throws Exception;
	
	/**
	 * 更新一条充值记录
	 * @param userRecharge
	 * @throws Exception
	 * @author 徐优优
	 * @date 2016年5月10日
	 */
	public void updateRechargeSelective(UserRecharge userRecharge) throws Exception;

	/**
	 * 统一支付接口返回数据数据库处理（不处理校币）
	 * @param
	 * @author 李亚坤
	 * @date 2016年11月16日
	 */
	void updateCashJSAPIRechargeResult(JSAPIResData jsapiResData) throws Exception;

    /**
     * 根据参数查询交易明细列表， 带分页
     * @param userSearchDto
     * @return
     */
    public List<Object> selectByActiveListPage(UserSearchDto userSearchDto);
    
    public UserRecharge selectByPrimaryKey(String id);

	/**
	 *@Author:liping
	 *@Description:
	 *@Date:2017年3月24日14:36:58
	 */
	public OrderQueryRes orderQuery(OrderQueryReq orderQueryReq);

	public void updateWxZhiFuResult(JSAPIResData jSAPIResData);
}
