package com.wh.dto.system;

import com.wh.util.BaseModel;

import java.util.Date;

/**
 * Created by Administrator on 2016-4-21.
 */
public class UserSearchDto extends BaseModel{


    //查询条件字段
    private Date registerTimeStart;
    private Date registerTimeEnd;
    private Date lastloginTimeStart;
    private Date lastloginTimeEnd;
    private String realName;
    private String nickName;
    private String phone;
    private String schoolName;
    private String majorName;
    private String sortName;

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(String selectDate) {
        this.selectDate = selectDate;
    }

    private String selectDate;

    public Date getRegisterTimeStart() {
        return registerTimeStart;
    }

    public void setRegisterTimeStart(Date registerTimeStart) {
        this.registerTimeStart = registerTimeStart;
    }

    public Date getRegisterTimeEnd() {
        return registerTimeEnd;
    }

    public void setRegisterTimeEnd(Date registerTimeEnd) {
        this.registerTimeEnd = registerTimeEnd;
    }

    public Date getLastloginTimeStart() {
        return lastloginTimeStart;
    }

    public void setLastloginTimeStart(Date lastloginTimeStart) {
        this.lastloginTimeStart = lastloginTimeStart;
    }

    public Date getLastloginTimeEnd() {
        return lastloginTimeEnd;
    }

    public void setLastloginTimeEnd(Date lastloginTimeEnd) {
        this.lastloginTimeEnd = lastloginTimeEnd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}
