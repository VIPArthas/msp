package com.wh.util;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.wh.entity.SmsVerify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * 手机验证码操作
 * 阿里大鱼接口
 * Created by danding on 2016/3/24.
 */
public class MessageCode {
    private static final String SEND_CODE = "https://api.netease.im/sms/sendcode.action";
    private static final String VERIFY_CODE = "https://api.netease.im/sms/verifycode.action";
    private Logger log = LoggerFactory.getLogger(this.getClass());
    public static SmsVerify sendCode(SmsVerify smsVerify){
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23334365", "3d4b1f76516cf61f5ffbd2c80d13e363");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend(smsVerify.getVerifyCode());
        req.setSmsType("normal");//
        req.setSmsFreeSignName("身份验证");
        req.setSmsParamString("{\"code\":\""+smsVerify.getVerifyCode()+"\",\"product\":\"校联网\"}");
        req.setRecNum(smsVerify.getPhone());
        req.setSmsTemplateCode("SMS_6690684");
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
            //将验证信息存入数据库中
            smsVerify.setSendContent("身份验证");
            smsVerify.setSendInterface("阿里大鱼");
            return smsVerify;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }


    public SmsVerify sendTemplaMsg(SmsVerify smsVerify) {

        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23334365", "3d4b1f76516cf61f5ffbd2c80d13e363");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        req.setSmsFreeSignName("系统监控");
        req.setSmsTemplateCode("SMS_16235086");
        req.setSmsParamString("{\"content\":\"" + smsVerify.getVerifyCode() + "\"}");
        req.setRecNum(smsVerify.getPhone());
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
            log.info("发送短信返回内容{}", rsp.getBody().toString());
            //将验证信息存入数据库中
            smsVerify.setSendContent("系统监控");
            smsVerify.setSendInterface("阿里大鱼");
            smsVerify.setReturnStatus(rsp.getBody().toString());
            return smsVerify;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 推送消息中发短信
     */
    public SmsVerify sendSMS(SmsVerify smsVerify) {

        TaobaoClient client = new DefaultTaobaoClient(Constants.A_LI_DA_YU_SERVER_Url,Constants.A_LI_DA_YU_APPKEY,Constants.A_LI_DA_YU_APPSECRET);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType(Constants.SMS_TYPE);
        req.setSmsFreeSignName(Constants.SMS_FREE_SIGN_NAME);
        req.setSmsTemplateCode(Constants.SMS_TEMPLATE_CODE);
        req.setSmsParamString("{\"content\":\""+smsVerify.getVerifyCode()+"\"}");
        req.setRecNum(smsVerify.getPhone());
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
            log.info("发送短信返回内容{}", rsp.getBody().toString());
            //将验证信息存入数据库中
            smsVerify.setSendContent(smsVerify.getVerifyCode());
            smsVerify.setSendInterface("阿里大鱼");
            smsVerify.setReturnStatus(rsp.getBody().toString());
            return smsVerify;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 生成手机验证码
     * @return
     * @author 徐优优
     * @date 2016年7月21日
     */
    public static String genVerifyCode(){
    	Random random = new Random();
        int x = random.nextInt(899999) + 100000;
        String code = x + "";
        return code;
    }
}
