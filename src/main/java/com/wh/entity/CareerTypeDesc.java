package com.wh.entity;

import java.util.Date;

public class CareerTypeDesc {
    private String typeDescId;

    private String baseId;

    private String typeDescName;

    private String typeDescDesc;

    private Integer typeDescOrder;

    private Date createTime;

    private String creator;

    private Integer status;

    public String getTypeDescId() {
        return typeDescId;
    }

    public void setTypeDescId(String typeDescId) {
        this.typeDescId = typeDescId == null ? null : typeDescId.trim();
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId == null ? null : baseId.trim();
    }

    public String getTypeDescName() {
        return typeDescName;
    }

    public void setTypeDescName(String typeDescName) {
        this.typeDescName = typeDescName == null ? null : typeDescName.trim();
    }

    public String getTypeDescDesc() {
        return typeDescDesc;
    }

    public void setTypeDescDesc(String typeDescDesc) {
        this.typeDescDesc = typeDescDesc == null ? null : typeDescDesc.trim();
    }

    public Integer getTypeDescOrder() {
        return typeDescOrder;
    }

    public void setTypeDescOrder(Integer typeDescOrder) {
        this.typeDescOrder = typeDescOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}