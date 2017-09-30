package com.wh.entity;

import java.util.Date;

import com.wh.util.BaseModel;

public class UserPay extends BaseModel{
    private String id;

    private Long payNum;

    private String userId;

    private String receiverId;

    private Double paySchoolMoney;

    private Double schoolMoney;

    private Date payTime;

    private String orderInfo;

    private Integer payStatus;
    
    private Integer payType;

    private String payRemark;

    private String payNote;
    
    private String taskId;
    
    private Date beginPayTime;//支付记录开始时间
    
    private Date endPayTime;//支付记录截止时间
    
    private String realName;//支付记录页面查询条件
    private String payTypeName;//支付类型名称
    
    
    private String remark; //备注
    
    

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getBeginPayTime() {
		return beginPayTime;
	}

	public void setBeginPayTime(Date beginPayTime) {
		this.beginPayTime = beginPayTime;
	}

	public Date getEndPayTime() {
		return endPayTime;
	}

	public void setEndPayTime(Date endPayTime) {
		this.endPayTime = endPayTime;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getPayNum() {
        return payNum;
    }

    public void setPayNum(Long payNum) {
        this.payNum = payNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }

    public Double getPaySchoolMoney() {
        return paySchoolMoney;
    }

    public void setPaySchoolMoney(Double paySchoolMoney) {
        this.paySchoolMoney = paySchoolMoney;
    }

    public Double getSchoolMoney() {
        return schoolMoney;
    }

    public void setSchoolMoney(Double schoolMoney) {
        this.schoolMoney = schoolMoney;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo == null ? null : orderInfo.trim();
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayRemark() {
        return payRemark;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark == null ? null : payRemark.trim();
    }

    public String getPayNote() {
        return payNote;
    }

    public void setPayNote(String payNote) {
        this.payNote = payNote == null ? null : payNote.trim();
    }
}