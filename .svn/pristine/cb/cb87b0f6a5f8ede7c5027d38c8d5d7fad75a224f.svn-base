package com.wh.util.wxpay.protocol.unifiedOrderProtocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.wh.util.wxpay.Configure;
import com.wh.util.wxpay.RandomStringGenerator;
import com.wh.util.wxpay.Signature;

/**
 * 统一下单接口请求参数对象
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
 * 接口地址链接：https://api.mch.weixin.qq.com/pay/unifiedorder
 * 是否需要证书：不需要
 * @author 徐优优
 * @date 2016年5月10日
 */
public class UnifiedOrderReqData {
	private String appid;
    private String mch_id;
    private String device_info;
    private String nonce_str;
    private String sign;
    private String body;
    private String attach;
    private String out_trade_no;
    private int total_fee;
    private String spbill_create_ip;
    private String time_start;
    private String time_expire;
    private String notify_url;
    private String trade_type;
    private String openid;

    public UnifiedOrderReqData(){
    	
    }
	public UnifiedOrderReqData(String body, String out_trade_no, int total_fee, String spbill_create_ip, String notify_url, String trade_type, String openId) {
		super();
		setAppid(Configure.getAppid());
		setMch_id(Configure.getMchid());
		setBody(body);
		setOut_trade_no(out_trade_no);
		setTotal_fee(total_fee);
		setSpbill_create_ip(spbill_create_ip);
		setNotify_url(notify_url);
		setTrade_type(trade_type);
		setOpenid(openId);
		//随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());
        setSign(sign);//把签名数据设置到Sign这个属性中
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
