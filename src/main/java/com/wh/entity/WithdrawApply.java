package com.wh.entity;

import com.wh.util.BaseModel;

public class WithdrawApply extends BaseModel{
    private String id;

    private String bankId;

    private String swiftNumber;

    private String createTime;

    private Double withdrawalAmount;

    private Double fee;

    private Double fee2;

    private Integer status;
    private String userId;
    private  UserBank userBank;
    
    private Integer withdrawType;
    
    
    public Integer getWithdrawType() {
		return withdrawType;
	}

	public void setWithdrawType(Integer withdrawType) {
		this.withdrawType = withdrawType;
	}

	public String getUserId() {
        return userId;
    }

    public UserBank getUserBank() {
        return userBank;
    }

    public void setUserBank(UserBank userBank) {
        this.userBank = userBank;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }

    public String getSwiftNumber() {
        return swiftNumber;
    }

    public void setSwiftNumber(String swiftNumber) {
        this.swiftNumber = swiftNumber == null ? null : swiftNumber.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Double getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(Double withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getFee2() {
        return fee2;
    }

    public void setFee2(Double fee2) {
        this.fee2 = fee2;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}