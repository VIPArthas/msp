package com.wh.entity;

import java.util.Date;

import com.wh.util.BaseModel;

public class WmhFaultRelate extends BaseModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer areaId;

    private String areaName;

    private Integer goodsId;

    private String goodsName;

    private String respersonId;

    private String respersonName;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getRespersonId() {
        return respersonId;
    }

    public void setRespersonId(String respersonId) {
        this.respersonId = respersonId == null ? null : respersonId.trim();
    }

    public String getRespersonName() {
        return respersonName;
    }

    public void setRespersonName(String respersonName) {
        this.respersonName = respersonName == null ? null : respersonName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}