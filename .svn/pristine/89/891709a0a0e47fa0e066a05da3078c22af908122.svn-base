package com.wh.service.xlwapp.impl;

import com.wh.constants.Constants;
import com.wh.dao.xlwapp.UserMapper;
import com.wh.dao.xlwapp.UserPayMapper;
import com.wh.dao.xlwapp.UserRechargeMapper;
import com.wh.dao.xlwapp.WxRechargeMapper;
import com.wh.dto.system.UserSearchDto;
import com.wh.entity.*;
import com.wh.service.wv.WvService;
import com.wh.service.xlwapp.UserRechargeService;
import com.wh.service.xlwapp.UserService;
import com.wh.util.BaseModel;
import com.wh.util.PaginationInterceptor;
import com.wh.util.UUIDUtil;
import com.wh.util.wxpay.WxPayUtil;
import com.wh.util.wxpay.protocol.JSAPIProtocol.JSAPIResData;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.OrderQueryReq;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.OrderQueryRes;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.UnifiedOrderReqData;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.UnifiedOrderResData;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-4-28.
 */
@Service
public class UserRechargeServiceImpl implements UserRechargeService {
    @Autowired
    private UserRechargeMapper userRechargeMapper;
    
    @Autowired
    private WxRechargeMapper wxRechargeMapper;
    
    @Autowired
    private UserService userService;

	@Autowired
	private UserMapper userMapper;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WvService  wvService;
	@Autowired
	private UserPayMapper userPayMapper;
    @Override
	public UnifiedOrderResData saveRecharge(UserRecharge userRecharge, UnifiedOrderReqData unifiedOrderReqData) throws Exception {
    	UnifiedOrderResData unifiedOrderResData = null;
    	//1,新增一条充值记录
    	//2,获取统一订单信息
    	//3,新增一条wxRecharge记录
    	log.info("saveRecharge--------------------1");
    	WxRecharge wxRecharge = new WxRecharge();
    	wxRecharge.setId(UUIDUtil.getUUID());
    	wxRecharge.setRechargeId(userRecharge.getId());
    	wxRecharge.setRechargeType(1);
    	wxRecharge.setOrderTime(new Date());
		wxRecharge.setOpenId(userRecharge.getOpenId());
		log.info("saveRecharge--------------------1");
    	try{
    		unifiedOrderResData = WxPayUtil.requestUnifiedOrder(unifiedOrderReqData);
    		log.info("saveRecharge--------------------2");
        	if(null != unifiedOrderResData){
        		String returnCode = unifiedOrderResData.getReturn_code();
        		String resultCode = unifiedOrderResData.getResult_code();
        		log.info("saveRecharge--------------------3");
        		if((!StringUtils.isEmpty(returnCode) && returnCode.equals("SUCCESS")) && (!StringUtils.isEmpty(resultCode) && resultCode.equals("SUCCESS"))){
        			wxRecharge.setUnifiedOrder(unifiedOrderResData.getPrepay_id());
        		}else{
        			log.info("saveRecharge--------------------4");
        			wxRecharge.setCallbackRes("支付失败");
        			JSONObject remarkObj = new JSONObject();
        			remarkObj.put("result_code", unifiedOrderResData.getResult_code());
        			remarkObj.put("return_code", unifiedOrderResData.getReturn_code());
        			remarkObj.put("return_msg", unifiedOrderResData.getReturn_msg());
        			remarkObj.put("err_code", unifiedOrderResData.getErr_code());
        			remarkObj.put("err_code_des", unifiedOrderResData.getErr_code_des());
        			wxRecharge.setCallbackRemark(remarkObj.toString());
        			
        			userRecharge.setRechargeStatus(2);
        			userRecharge.setRechargeRemark(remarkObj.toString());
        		}
        	}
    	}catch(Exception e){
    		wxRecharge.setCallbackRes("程序发送请求报错");
    		userRecharge.setRechargeStatus(2);
    		userRecharge.setRechargeRemark("程序发送请求报错");
    		e.printStackTrace();
    	}finally{
    		wxRechargeMapper.insert(wxRecharge);
        	userRechargeMapper.insert(userRecharge);
    	}
    	return unifiedOrderResData;
	}

	@Override
	public void updateRecharge(UserRecharge userRecharge) throws Exception {
		userRechargeMapper.updateByPrimaryKey(userRecharge);
	}

	@Override
	public void updateRechargeSelective(UserRecharge userRecharge) throws Exception {
		userRechargeMapper.updateByPrimaryKeySelective(userRecharge);
	}
	
	

    /**
     * 根据参数查询交易明细列表， 带分页
     * @param userSearchDto
     * @return
     */
    @Override
    public List<Object> selectByActiveListPage(UserSearchDto userSearchDto) {
        int pageSize = Constants.pageSize;
        int startNum = pageSize * (userSearchDto.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(userSearchDto.getCurrentpage(), userSearchDto.getRscount());

        List<Map<String, Object>> listMaps = this.userRechargeMapper.selectByActiveListPage(userSearchDto,rowBounds);

        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> poList = new ArrayList<Object>();
        poList.add(listMaps);
        poList.add(baseModel.getCurrentpage());
        poList.add(baseModel.getRscount());
        return poList;
    }

	@Override
	public void updateJSAPIRechargeResult(JSAPIResData jSAPIResData) {
		UserRecharge ur = new UserRecharge();
		ur.setId(jSAPIResData.getOut_trade_no());
		ur.setReplyTime(jSAPIResData.getTime_end());
		
		WxRecharge wxr = new WxRecharge();
		wxr.setRechargeId(jSAPIResData.getOut_trade_no());
		wxr.setCallbackTime(jSAPIResData.getTime_end());
		
		if(jSAPIResData.getReturn_code().equals("SUCCESS") && jSAPIResData.getResult_code().equals("SUCCESS")){
			ur.setRechargeStatus(1);
			ur.setRechargeRemark("支付成功");
			
			wxr.setCallbackRes("支付成功");
			//增加账户余额
			Double money = Double.valueOf(jSAPIResData.getTotal_fee());
			String openId = jSAPIResData.getOpenid();

			User user = userMapper.selectUserByOpenId(openId);
			Double schoolMoney = 0.00;
			if(null != user && null != user.getSchoolMoney()){
				schoolMoney = user.getSchoolMoney();
				money += schoolMoney;
			}
			user.setSchoolMoney(money);
			userMapper.updateByPrimaryKeySelective(user);
		}else{
			ur.setRechargeStatus(2);
			JSONObject remarkObj = new JSONObject();
			remarkObj.put("result_code", jSAPIResData.getResult_code());
			remarkObj.put("return_code", jSAPIResData.getReturn_code());
			remarkObj.put("return_msg", jSAPIResData.getReturn_msg());
			remarkObj.put("err_code", jSAPIResData.getErr_code());
			remarkObj.put("err_code_des", jSAPIResData.getErr_code_des());
			ur.setRechargeRemark(remarkObj.toString());
			
			wxr.setCallbackRes("支付失败");
			wxr.setCallbackRemark(remarkObj.toString());
			

		}
		userRechargeMapper.updateByPrimaryKeySelective(ur);
		wxRechargeMapper.updateByUserRechargeIdSelective(wxr);
	}


	@Override
	public void updateCashJSAPIRechargeResult(JSAPIResData jSAPIResData) {
		UserRecharge ur = new UserRecharge();
		WvApply wvApply = new WvApply();
		ur.setId(jSAPIResData.getOut_trade_no());
		ur.setReplyTime(jSAPIResData.getTime_end());

		WxRecharge wxr = new WxRecharge();
		wxr.setRechargeId(jSAPIResData.getOut_trade_no());
		wxr.setCallbackTime(jSAPIResData.getTime_end());
		String openId = jSAPIResData.getOpenid();
		if(jSAPIResData.getReturn_code().equals("SUCCESS") && jSAPIResData.getResult_code().equals("SUCCESS")){
			ur.setRechargeStatus(1);
			ur.setRechargeRemark("支付成功");
			wxr.setCallbackRes("支付成功");
			// 处理寒假工业务逻辑
			wvApply.setOpenid(openId);
			wvApply.setPayStatus("1");
			wvApply.setPayMoney(((float)jSAPIResData.getCash_fee())/(float)100.0);
		}else{
			ur.setRechargeStatus(2);
			JSONObject remarkObj = new JSONObject();
			remarkObj.put("result_code", jSAPIResData.getResult_code());
			remarkObj.put("return_code", jSAPIResData.getReturn_code());
			remarkObj.put("return_msg", jSAPIResData.getReturn_msg());
			remarkObj.put("err_code", jSAPIResData.getErr_code());
			remarkObj.put("err_code_des", jSAPIResData.getErr_code_des());
			ur.setRechargeRemark(remarkObj.toString());

			wxr.setCallbackRes("支付失败");
			wxr.setCallbackRemark(remarkObj.toString());
			wvApply.setOpenid(openId);
			wvApply.setPayStatus("2");
			wvApply.setPayMoney((float) 50);

		}
		userRechargeMapper.updateByPrimaryKeySelective(ur);
		wxRechargeMapper.updateByUserRechargeIdSelective(wxr);
		try {
			wvService.updateByOpenId(wvApply);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Override
	public UserRecharge selectByPrimaryKey(String id) {
		if(!StringUtils.isEmpty(id)){
			return userRechargeMapper.selectByPrimaryKey(id);
		}else{
			return null;
		}
	}

	@Override
	public OrderQueryRes orderQuery(OrderQueryReq orderQueryReq) {
		OrderQueryRes orderQueryRes= null;
		try {
			orderQueryRes = WxPayUtil.oderQueryResData(orderQueryReq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderQueryRes;
	}

	@Override
	public void updateWxZhiFuResult(JSAPIResData jSAPIResData) {
		UserRecharge ur = new UserRecharge();
		ur.setId(jSAPIResData.getOut_trade_no());
		ur.setReplyTime(jSAPIResData.getTime_end());

		WxRecharge wxr = new WxRecharge();
		wxr.setRechargeId(jSAPIResData.getOut_trade_no());
		wxr.setCallbackTime(jSAPIResData.getTime_end());
		if(jSAPIResData.getReturn_code().equals("SUCCESS") && jSAPIResData.getResult_code().equals("SUCCESS")){
			ur.setRechargeStatus(1);
			ur.setRechargeRemark("支付成功");
			wxr.setCallbackRes("支付成功");




			UserPay userPay=userPayMapper.selectByPrimaryKey(jSAPIResData.getOut_trade_no());
			if(null!=userPay){
				userPay.setId(jSAPIResData.getOut_trade_no());
				userPay.setPayStatus(Constants.PAY_STATUS_SUCESS);
				userPay.setPayRemark("支付成功");
				if(userPay.getPayType()==Constants.PAY_TYPE_CZ){
					//增加账户余额
					Double money = Double.valueOf(jSAPIResData.getTotal_fee());
					String openId = jSAPIResData.getOpenid();

					User user = userMapper.selectUserByOpenId(openId);
					Double schoolMoney = 0.00;
					if(null != user && null != user.getSchoolMoney()){
						schoolMoney = user.getSchoolMoney();
						money += schoolMoney;
					}
					user.setSchoolMoney(money);
					userMapper.updateByPrimaryKeySelective(user);
					userPay.setSchoolMoney(money);
				}
				userPayMapper.updateByPrimaryKeySelective(userPay);
			}

		}else{
			ur.setRechargeStatus(2);
			JSONObject remarkObj = new JSONObject();
			remarkObj.put("result_code", jSAPIResData.getResult_code());
			remarkObj.put("return_code", jSAPIResData.getReturn_code());
			remarkObj.put("return_msg", jSAPIResData.getReturn_msg());
			remarkObj.put("err_code", jSAPIResData.getErr_code());
			remarkObj.put("err_code_des", jSAPIResData.getErr_code_des());
			ur.setRechargeRemark(remarkObj.toString());

			wxr.setCallbackRes("支付失败");
			wxr.setCallbackRemark(remarkObj.toString());

		}
		userRechargeMapper.updateByPrimaryKeySelective(ur);
		wxRechargeMapper.updateByUserRechargeIdSelective(wxr);

//		UserPay userPay=new UserPay();
	}
}
