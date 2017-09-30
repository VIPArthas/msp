package com.wh.mspentity;

import java.util.Date;

import com.wh.util.BaseModel;

/**
 * 企业号平台
 * @author Administrator
 *
 */
public class MspPlatform extends BaseModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String accessToken;

    private String corpid;

    private String corpsecret;

    private String code;

    private String contact;

    private Date createtime;

    private Date lasttokentime;

    private String agentid;

    private String remark;

    

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid == null ? null : corpid.trim();
    }

    public String getCorpsecret() {
        return corpsecret;
    }

    public void setCorpsecret(String corpsecret) {
        this.corpsecret = corpsecret == null ? null : corpsecret.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLasttokentime() {
        return lasttokentime;
    }

    public void setLasttokentime(Date lasttokentime) {
        this.lasttokentime = lasttokentime;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid == null ? null : agentid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}