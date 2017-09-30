package com.wh.dto.system;

import com.wh.util.BaseModel;

import java.util.Date;

/**
 * Created by Administrator on 2016-4-27.
 */
public class ComplaintSuggestionsDto extends BaseModel {

    private String sugNum;

    private String modularId;

    private Integer type;

    private Date submitTimeStart;
    private Date submitTimeEnd;

    private String submitter;


    private String sugContent;

    private String nickName;

    private String phone;

    private String realName;


    public Date getSubmitTimeStart() {
        return submitTimeStart;
    }

    public void setSubmitTimeStart(Date submitTimeStart) {
        this.submitTimeStart = submitTimeStart;
    }

    public Date getSubmitTimeEnd() {
        return submitTimeEnd;
    }

    public void setSubmitTimeEnd(Date submitTimeEnd) {
        this.submitTimeEnd = submitTimeEnd;
    }

    public ComplaintSuggestionsDto() {

    }


    public String getSugNum() {
        return sugNum;
    }

    public void setSugNum(String sugNum) {
        this.sugNum = sugNum;
    }

    public String getModularId() {
        return modularId;
    }

    public void setModularId(String modularId) {
        this.modularId = modularId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }


    public String getSugContent() {
        return sugContent;
    }

    public void setSugContent(String sugContent) {
        this.sugContent = sugContent;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }


}
