package com.wh.util.wxpay;

import com.wh.util.wxpay.protocol.unifiedOrderProtocol.OrderQueryReq;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.OrderQueryRes;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.UnifiedOrderReqData;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.UnifiedOrderResData;
/**
 * 微信支付工具类
 * 
 * @author 徐优优
 * @date 2016年5月10日
 */
public class WxPayUtil {
	
	/**
     * 请求同一订单接口，获取微信订单号
     * @param unifiedOrderReqData 请求参数对象
     * @return UnifiedOrderResData 返回参数对象
     * @author 徐优优
     * @date 2016年5月10日
     */
	public static UnifiedOrderResData requestUnifiedOrder(UnifiedOrderReqData unifiedOrderReqData) throws Exception{
		HttpsRequest hr = new HttpsRequest();
		String xmlStr = hr.sendPost(Configure.DO_ORDER, unifiedOrderReqData);
		UnifiedOrderResData unifiedOrderResData = (UnifiedOrderResData) Util.getObjectFromXML(xmlStr, UnifiedOrderResData.class);
		return unifiedOrderResData;
	}

	public static OrderQueryRes oderQueryResData(OrderQueryReq orderQueryReq) throws Exception{
		HttpsRequest hr = new HttpsRequest();
		String xmlStr = hr.sendPost("https://api.mch.weixin.qq.com/pay/orderquery", orderQueryReq);
		OrderQueryRes orderQueryRes = (OrderQueryRes) Util.getObjectFromXML(xmlStr, OrderQueryRes.class);
		return orderQueryRes;
	}
}
