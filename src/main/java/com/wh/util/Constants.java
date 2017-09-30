package com.wh.util;

/**
 * Created by danding on 2015/8/22.
 */
public class Constants {
    public static final String ACCOUNT_PROPERTIES_PATH = "WEB-INF/classes/config.properties";

    //    public static final String MESSAGE_PATH = "WEB-INF/classes/messageTemplate.properties";
    static {
        BASE_SERVER = PropertiesUtil.getPropertie(ACCOUNT_PROPERTIES_PATH, "base_server");
        PROJECT_LOCATION = PropertiesUtil.getPropertie(ACCOUNT_PROPERTIES_PATH, "projectLocation");
        QR_CODE_PATH = PropertiesUtil.getPropertie(ACCOUNT_PROPERTIES_PATH, "qr_code_path");
    }

    public static String BASE_SERVER;
    public static String QR_CODE_PATH;//岗位二维码保存路径
    public static String PROJECT_LOCATION;//项目绝对路径
    /**
     * 微信服务号类型（用户类型）
     */
    public static final String PLATFORM_TYPE_COMPANY = "company";//企业
    public static final String PLATFORM_TYPE_STUDENT = "student";//学生
    public static final String PLATFORM_TYPE_SCHOOL = "school";//学校

    /**
     * 最后一题序号
     */
    public static final Integer LASTITEM = 6;

    public static String noTypeCareerId = "9a56dd54f55b11e5a619000c2903d605";


    /**
     * 微信服务号上的模板消息id 相关
     */
    //绑定通知
    public static String TONG_ZHI_ID = "lI7M--MVYEnyuX4rdBpR_pLbiH2VA3HAMaJi_JFOV8Q";
    //签到通知
    public static String QIAN_DAO_ID = "XEgC-l6Odtpei15twRgBDq0G5_fYSUM5Gkj3a2KcVjM";
    //订阅的任务通知
    public static String DING_YUE_ID = "OHg6LSgPj6BThOA9rJbSyHL2iPeFiiQPw56fFEWU9J4";
    
   /* //会议通知
    public static String HUI_YI_ID = "L0RQfyE4OOOkk7-NIEDSJT892QKisNZFh3_jV1XHWVc";
    //薪资发放
    public static String XIN_ZI_ID = "IxLf_HSodwHG3XkiENewkX2iBu-feMhciCePmMaIVaU";*/

    //微门户迁移至新公众号,模板id改变
    //会议通知
    public static String HUI_YI_ID = "wsYrlVWpWe-G7uHps2Q2YFNqQHSnhKVQxMQsyMcVULs";
    //薪资发放
    public static String XIN_ZI_ID = "8ryjK1GRISWiT-PiZyGMP5cT5pHa9tZ_vD5Lxa14pxw";
    //周师修改日志通知模板
    public static String ZS_XGRZ_ID = "x9Gc05rbwDc-liqwqa35sUvChjgXvq9J2-48HGNMGqw";
    
    //阿里大鱼短信相关
    //短信的服务地址
    public static String A_LI_DA_YU_SERVER_Url = "http://gw.api.taobao.com/router/rest";
    //创建的应用的appkey
    public static String A_LI_DA_YU_APPKEY = "23334365";
    //创建的应用的App Secret
    public static String A_LI_DA_YU_APPSECRET = "3d4b1f76516cf61f5ffbd2c80d13e363";
    //模板类型
    public static String SMS_TYPE = "normal";
    //配置的短信签名名称
    public static String SMS_FREE_SIGN_NAME = "微门户";
    //配置的短信模板ID
    public static String SMS_TEMPLATE_CODE = "SMS_16235086";

    //微信服务号上客服人员的OpenId 郭强
    public static String FW_OPEN_Id = "okLTXt5NYfjvb26m1baZdeKfn6kU";
    //设定的默认的平台客服，负责转发消息
    public static String DEFAULT_Kf = "kf2006@jhkj_xlw";


    //支付宝支付的公钥，密钥，商户ID
    //开发者应用私钥
    public static String APP_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALmvpJwWUY9yBzcm5CLqZTZVbQsHRXncmiLyCepHW+yZ5Zj7wDR1KtRKx1R+qDWJ1gCHJStpzvS/G0erNghqQ5RfSwD4TfHqzxn6Yv0jAt+GZFbm4vUdXO8XkMLVc/9UbtEnE/rAvKLFrpwB5vZbf73EJqSLCvX1yOy20RckqEYVAgMBAAECgYBMhCJy54eIctaiVXZhJPG8KAudJcvtYYhqqg6H6gvpPamtFuCnPTj+7imhmee62CfFY+tfLmTH2hBlwhmr8g4SXFWX5g24K1/mkWQxN4VxjQdz4VG5x2q06yHTriPMyDS6mj1h9d770akOjKigyl+eUJy2j8Sb4KRCoc5nMCyyKQJBAN0Xl1vLS+DbHK7CLrh+1KIsdGV051o4UG27+GyPAHoJkkqdPQnnYwDebiWNtSblSZfqZ1YYT488v828jSS6wDsCQQDXAPYKzAkp75KVVSLcbsK64PNG2eF2vBP7TxEgTm9y9hBTxXmQhwJfz8dt5xv63ryX0QQnx4WvVDJ2+gZ6Nn3vAkEA1UdtvqJ11zroB3nRQi8vhiZmPn0wtOrAQ6th8a9doVxcYFggys4IxozMKrPx/cyQWtlHyFj5Xs8WpCxw5+6zgwJAeOiCdOnvEVU3sqDw/NOQ0LUmOibXAwWbmw0IzYkuZZD495Mq3Nr/u8/GEAkSlA7kMeAKHTC3/jQ0OPI76EhyowJBAMz68tv22gWFWxaDOkOWKv3wnAVqhrfYXchbld7aSGD08BdL/ZM33mArAynAY92T1jjcBE3eFM5o+GcFPd0A4ow=";
    //支付宝公匙
    public static String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
    //商户ID
    public static String PID = "2016041301294529";

    //发邮件
    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    public static String MY_EMAIL_ACCOUNT = "15343826020@163.com";
    public static String MY_EMAIL_PASSWORD= "xlw12345";

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String MY_EMAIL_SMTP_HOST= "smtp.163.com";
}

