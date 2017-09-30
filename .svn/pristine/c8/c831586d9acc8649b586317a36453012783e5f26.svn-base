package com.wh.entity;

import java.util.Date;

import com.wh.util.DateUtil;
import com.wh.util.UUIDUtil;

public class SmsVerify {
    private String id;

    private String userId;

    private String phone;

    private Integer status;

    private String verifyCode;

    private String sendContent;

    private String sendInterface;

    private Date overdueTime;

    private String operateIp;

    private Integer operateTerminal;

    private Date createTime;

    private String returnStatus;

    public SmsVerify(String userId, String phone, String verifyCode, String sendContent, String sendInterface, String operateIp, Integer operateTerminal){
    	this.id = UUIDUtil.getUUID();
    	this.userId = userId;
    	this.phone = phone;
    	this.verifyCode = verifyCode;
    	this.sendContent = sendContent;
    	this.sendInterface = sendInterface;
    	this.operateIp = operateIp;
    	this.operateTerminal = operateTerminal;
    	this.id = UUIDUtil.getUUID();
    	
    	Date ct = new Date();
    	Date ot = DateUtil.getTimestampExpiredSecond(ct, 60);
    	this.overdueTime = ot;
    	this.createTime = ct;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode == null ? null : verifyCode.trim();
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent == null ? null : sendContent.trim();
    }

    public String getSendInterface() {
        return sendInterface;
    }

    public void setSendInterface(String sendInterface) {
        this.sendInterface = sendInterface == null ? null : sendInterface.trim();
    }

    public Date getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp == null ? null : operateIp.trim();
    }

    public Integer getOperateTerminal() {
        return operateTerminal;
    }

    public void setOperateTerminal(Integer operateTerminal) {
        this.operateTerminal = operateTerminal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}