package com.wh.entity;

import java.util.Date;

import com.wh.util.BaseModel;

public class WmhFaultLog extends BaseModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String faultId;

    private String faultMsg;

    private String createUser;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFaultId() {
        return faultId;
    }

    public void setFaultId(String faultId) {
        this.faultId = faultId == null ? null : faultId.trim();
    }

    public String getFaultMsg() {
        return faultMsg;
    }

    public void setFaultMsg(String faultMsg) {
        this.faultMsg = faultMsg == null ? null : faultMsg.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}