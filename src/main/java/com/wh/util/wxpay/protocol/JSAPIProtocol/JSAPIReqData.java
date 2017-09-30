package com.wh.util.wxpay.protocol.JSAPIProtocol;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.wh.util.wxpay.Configure;
import com.wh.util.wxpay.RandomStringGenerator;
import com.wh.util.wxpay.Signature;

public class JSAPIReqData {
	private String appId;
	private Long timeStamp;
	private String nonceStr;
	private String packageStr;
	private String signType;
	private String paySign;
	public JSAPIReqData(){
		
	}
	public JSAPIReqData(String packageStr, String signType){
		setAppId(Configure.getAppid());
		setTimeStamp(new Date().getTime());
		//随机字符串，不长于32 位
        setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
		setPackageStr(packageStr);
		setSignType(signType);
        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());
        setPaySign(sign);//把签名数据设置到Sign这个属性中
	}
	
	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                	if(field.getName().equals("packageStr")){
                		map.put("package", obj);
                	}else{
                		map.put(field.getName(), obj);
                	}
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getPackageStr() {
		return packageStr;
	}
	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
}
