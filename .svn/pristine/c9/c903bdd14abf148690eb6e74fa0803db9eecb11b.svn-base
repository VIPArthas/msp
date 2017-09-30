package com.wh.util.wxpay.protocol.unifiedOrderProtocol;

import com.wh.util.wxpay.RandomStringGenerator;
import com.wh.util.wxpay.Signature;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2017/3/24.
 */
public class OrderQueryReq {
    private String appid;
    private String mch_id;
    private String out_trade_no;
    private String nonce_str;
    private String sign;
     public OrderQueryReq(UnifiedOrderReqData data){
         setAppid(data.getAppid());
         setMch_id(data.getMch_id());
         setOut_trade_no(data.getOut_trade_no());
         //随机字符串，不长于32 位
         setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
         //根据API给的签名规则进行签名
         String sign = Signature.getSign(toMap());
         setSign(sign);//把签名数据设置到Sign这个属性中
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

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
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
}
