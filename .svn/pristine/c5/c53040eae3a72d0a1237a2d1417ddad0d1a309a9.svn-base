package com.wh.dto;

import java.util.Date;
import java.util.List;

import com.wh.util.BaseModel;

/**
* @author: 何阳
* @date: 2016年7月5日
*/
public class UserRouteDto extends BaseModel{
	
	private String id;
	private List<String> userId; //所属用户id
	private String origins;//出发地名称
	private String destinations; //目的地名称
	private double originsLongitude; //出发地经度
	private double originsLatitude; //出发地纬度
	private double destLongitude; //目的地经度
	private double destLatitude; //目的地纬度
	private String mode; //类型，包括driving(驾车)（默认）、walking(步行)
	private Date startDate; //关心时间段-开始时间
	private Date endDate; //关心时间段-结束时间
	private Date createTime; //创建时间
	private Integer status; //路线状态（1-正常，0-删除（数据库不删除，仅前台不显示，后台不再自动抓取数据））
	private String city; //所在城市
	private String citys; //出发地所在城市
	private String citye; //目的地所在城市
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	
	public List<String> getUserId() {
		return userId;
	}
	public void setUserId(List<String> userId) {
		this.userId = userId;
	}
	public String getOrigins() {
		return origins;
	}
	public void setOrigins(String origins) {
		this.origins = origins == null ? null : origins.trim();
	}
	public String getDestinations() {
		return destinations;
	}
	public void setDestinations(String destinations) {
		this.destinations = destinations == null ? null : destinations.trim();
	}
	public double getOriginsLongitude() {
		return originsLongitude;
	}
	public void setOriginsLongitude(double originsLongitude) {
		this.originsLongitude = originsLongitude;
	}
	public double getOriginsLatitude() {
		return originsLatitude;
	}
	public void setOriginsLatitude(double originsLatitude) {
		this.originsLatitude = originsLatitude;
	}
	public double getDestLongitude() {
		return destLongitude;
	}
	public void setDestLongitude(double destLongitude) {
		this.destLongitude = destLongitude;
	}
	public double getDestLatitude() {
		return destLatitude;
	}
	public void setDestLatitude(double destLatitude) {
		this.destLatitude = destLatitude;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode == null ? null : mode.trim();
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}
	public String getCitys() {
		return citys;
	}
	public void setCitys(String citys) {
		this.citys = citys == null ? null : citys.trim();
	}
	public String getCitye() {
		return citye;
	}
	public void setCitye(String citye) {
		this.citye = citye == null ? null : citye.trim();
	}
	
}
