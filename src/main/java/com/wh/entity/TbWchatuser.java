package com.wh.entity;

import com.wh.util.UUIDUtil;

import java.util.Date;
import java.util.List;

public class TbWchatuser{
    private String id;

    private Date createtime;

    private String openid;

    private String platformId;

    private String source;

    private Integer status;

    private String userId;
    
    private String headImg;
    
    private String nickName;
    
    private String addressProvince;
    
    private String addressCity;
    
    private String addressArea;
    
    private String addressDetail;
    
    private double addressLng;
    
    private double addressLat;

	private List<String> openIds;

	public List<String> getOpenIds() {
		return openIds;
	}

	public void setOpenIds(List<String> openIds) {
		this.openIds = openIds;
	}

	public TbWchatuser() {
		this.id= UUIDUtil.getUUID();
	}


	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId == null ? null : platformId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg == null ? null : headImg.trim();
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public String getAddressProvince() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince == null ? null : addressProvince.trim();
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity == null ? null : addressCity.trim();
	}

	public String getAddressArea() {
		return addressArea;
	}

	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea == null ? null : addressArea.trim();
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail == null ? null : addressDetail.trim();
	}

	public double getAddressLng() {
		return addressLng;
	}

	public void setAddressLng(double addressLng) {
		this.addressLng = addressLng;
	}

	public double getAddressLat() {
		return addressLat;
	}

	public void setAddressLat(double addressLat) {
		this.addressLat = addressLat;
	}
    
}