package com.wh.message.custom;

/**
 * Created by danding on 2015/8/19.
 */
public class BaseMessage {
    private String touser;//用户openId
    private String msgtype;//消息类型
    private Customservice customservice;//指定客服

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Customservice getCustomservice() {
        return customservice;
    }

    public void setCustomservice(Customservice customservice) {
        this.customservice = customservice;
    }
}
