package com.wh.dto.system;

import java.util.List;

import com.wh.constants.Constants;

/**
 * @Description
 * @param
 * @return
 * @date 2015年11月8日下午2:58:35
 * @author 郑爽
 */
public class RoleItem {
    private String role_id;

    private String text;

    private String iconCls = Constants.roleTree;

    private List<MenuItem> children;

    public RoleItem() {
        super();
    }

    public RoleItem(String role_id, String text) {
        super();
        this.role_id = role_id;
        this.text = text;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItem> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "RoleItem [role_id=" + role_id + ", text=" + text + "]";
    }

}
