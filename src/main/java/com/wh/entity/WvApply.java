package com.wh.entity;

import com.wh.util.BaseModel;
//申请的实体类
public class WvApply extends BaseModel{
    private String id;

    private String openid;

    private String name;

    private String cardId;

    private String phone;

    private String schoolId;

    private String schoolNumber;

    private String qqNumber;

    private String wechatNumber;

    private Integer key2;

    private String vacationTime;

    private String factory;

    private String payType;

    private String payStatus;

    private Integer key1;

    private String createTime;

    private Float payMoney;

    private String shcoolName;
    private String otherExplain; //其它说明
    private Integer jiFen;//积分
    private Integer gift1;
    private Integer gift2;
    private Integer gift3;
    private Integer gift4;
    private Integer gift5;
    private Integer gift6;
    private Integer gift7;
    private String  startDate;//开学时间
    private  Integer selectType;//后台选中的状态

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getGift7() {
        return gift7;
    }

    public void setGift7(Integer gift7) {
        this.gift7 = gift7;
    }

    public Integer getGift2() {
        return gift2;
    }

    public void setGift2(Integer gift2) {
        this.gift2 = gift2;
    }

    public Integer getGift3() {
        return gift3;
    }

    public void setGift3(Integer gift3) {
        this.gift3 = gift3;
    }

    public Integer getGift1() {
        return gift1;
    }

    public void setGift1(Integer gift1) {
        this.gift1 = gift1;
    }

    public Integer getGift4() {
        return gift4;
    }

    public void setGift4(Integer gift4) {
        this.gift4 = gift4;
    }

    public Integer getGift5() {
        return gift5;
    }

    public void setGift5(Integer gift5) {
        this.gift5 = gift5;
    }

    public Integer getGift6() {
        return gift6;
    }

    public void setGift6(Integer gift6) {
        this.gift6 = gift6;
    }

    public Integer getJiFen() {
        return jiFen;
    }

    public void setJiFen(Integer jiFen) {
        this.jiFen = jiFen;
    }

    public String getOtherExplain() {
        return otherExplain;
    }

    public void setOtherExplain(String otherExplain) {
        this.otherExplain = otherExplain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }

    public Integer getKey2() {
        return key2;
    }

    public void setKey2(Integer key2) {
        this.key2 = key2;
    }

    public String getVacationTime() {
        return vacationTime;
    }

    public void setVacationTime(String vacationTime) {
        this.vacationTime = vacationTime;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getKey1() {
        return key1;
    }

    public void setKey1(Integer key1) {
        this.key1 = key1;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Float getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Float payMoney) {
        this.payMoney = payMoney;
    }

    public String getShcoolName() {
        return shcoolName;
    }

    public void setShcoolName(String shcoolName) {
        this.shcoolName = shcoolName;
    }
}