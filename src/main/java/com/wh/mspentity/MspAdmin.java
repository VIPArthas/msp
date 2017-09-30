package com.wh.mspentity;

import com.wh.util.BaseModel;

public class MspAdmin extends BaseModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String adminName;

    private String adminPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }
}