package com.wh.entity;

import java.util.Date;

public class UserCash {
    private String id;

    private Integer cashNum;

    private String userId;

    private Double cashMoney;

    private Double cashRealMoney;

    private Integer cashStatus;

    private Date cashTime;

    private String cashBank;

    private Double cashFees;

    private String cashCard;

    private String cashRemark;

    private String operateUser;

    private Date operateTime;

    private String operateIp;
    
    private Double balance;
    
    public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getCashNum() {
        return cashNum;
    }

    public void setCashNum(Integer cashNum) {
        this.cashNum = cashNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Double getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(Double cashMoney) {
        this.cashMoney = cashMoney;
    }

    public Double getCashRealMoney() {
        return cashRealMoney;
    }

    public void setCashRealMoney(Double cashRealMoney) {
        this.cashRealMoney = cashRealMoney;
    }

    public Integer getCashStatus() {
        return cashStatus;
    }

    public void setCashStatus(Integer cashStatus) {
        this.cashStatus = cashStatus;
    }

    public Date getCashTime() {
        return cashTime;
    }

    public void setCashTime(Date cashTime) {
        this.cashTime = cashTime;
    }

    public String getCashBank() {
        return cashBank;
    }

    public void setCashBank(String cashBank) {
        this.cashBank = cashBank == null ? null : cashBank.trim();
    }

    public Double getCashFees() {
        return cashFees;
    }

    public void setCashFees(Double cashFees) {
        this.cashFees = cashFees;
    }

    public String getCashCard() {
        return cashCard;
    }

    public void setCashCard(String cashCard) {
        this.cashCard = cashCard == null ? null : cashCard.trim();
    }

    public String getCashRemark() {
        return cashRemark;
    }

    public void setCashRemark(String cashRemark) {
        this.cashRemark = cashRemark == null ? null : cashRemark.trim();
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser == null ? null : operateUser.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp == null ? null : operateIp.trim();
    }
}