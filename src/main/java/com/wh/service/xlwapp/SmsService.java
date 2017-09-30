package com.wh.service.xlwapp;

import com.wh.entity.SmsVerify;

/**
 * 短信发送service
 * 
 * @author 徐优优
 * @date 2016年7月21日
 */
public interface SmsService {

	/**
	 * 新增短信
	 * @param smsVerify
	 * @throws Exception
	 * @author 徐优优
	 * @date 2016年7月21日
	 */
	public void saveSms(SmsVerify smsVerify) throws Exception;

}
