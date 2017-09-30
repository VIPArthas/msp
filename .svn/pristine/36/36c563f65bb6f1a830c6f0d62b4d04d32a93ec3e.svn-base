package com.wh.mspentity;

import java.util.Date;
import java.util.List;

import com.wh.util.BaseModel;

public class MspDepartment extends BaseModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String name;

    private String parentid;

    private Integer orderId;	//用于存储数据中心部门id

    private Date createTime;
    
    private String order;
    
    private List<MspDepartment> childDepList;
    
    


	public List<MspDepartment> getChildDepList() {
		return childDepList;
	}

	public void setChildDepList(List<MspDepartment> childDepList) {
		this.childDepList = childDepList;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

   

    public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}