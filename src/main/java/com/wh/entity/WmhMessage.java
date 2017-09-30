package com.wh.entity;

import com.wh.util.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class WmhMessage extends BaseModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String toUser;

    private Integer templateType;

    private String parm1;

    private String parm2;

    private String parm3;

    private String parm4;

    private String parm5;

    private String parm6;

    private Integer wxSend;

    private Integer smsSend;

    private Integer mailSend;
    
    private String userNameArray;	//添加用户名(多个之间用,隔开)
    
    
    
    public String getUserNameArray() {
		return userNameArray;
	}

	public void setUserNameArray(String userNameArray) {
		this.userNameArray = userNameArray;
	}

	private List<String> tagList=new ArrayList<>();

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser == null ? null : toUser.trim();
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public String getParm1() {
        return parm1;
    }

    public void setParm1(String parm1) {
        this.parm1 = parm1 == null ? null : parm1.trim();
    }

    public String getParm2() {
        return parm2;
    }

    public void setParm2(String parm2) {
        this.parm2 = parm2 == null ? null : parm2.trim();
    }

    public String getParm3() {
        return parm3;
    }

    public void setParm3(String parm3) {
        this.parm3 = parm3 == null ? null : parm3.trim();
    }

    public String getParm4() {
        return parm4;
    }

    public void setParm4(String parm4) {
        this.parm4 = parm4 == null ? null : parm4.trim();
    }

    public String getParm5() {
        return parm5;
    }

    public void setParm5(String parm5) {
        this.parm5 = parm5 == null ? null : parm5.trim();
    }

    public String getParm6() {
        return parm6;
    }

    public void setParm6(String parm6) {
        this.parm6 = parm6 == null ? null : parm6.trim();
    }

    public Integer getWxSend() {
        return wxSend;
    }

    public void setWxSend(Integer wxSend) {
        this.wxSend = wxSend;
    }

    public Integer getSmsSend() {
        return smsSend;
    }

    public void setSmsSend(Integer smsSend) {
        this.smsSend = smsSend;
    }

    public Integer getMailSend() {
        return mailSend;
    }

    public void setMailSend(Integer mailSend) {
        this.mailSend = mailSend;
    }
}