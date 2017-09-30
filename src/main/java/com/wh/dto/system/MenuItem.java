package com.wh.dto.system;

import java.util.List;

/**
 * @Description 
 * @param
 * @return
 * @date 2015年11月8日下午3:08:30
 * @author 郑爽
 */
public class MenuItem {
	
	private String menu_id;
	private String menu_role_id;
	private String text;
	private String menu_url;
	private String menu_f_id;
	private String order_num;
	private String menu_type;
	private List<MenuItem> children;
	private String menu_des;
	
	
	public MenuItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuItem(String menu_id,String text){
		this.menu_id = menu_id;
		this.text = text;
	}
	
	public MenuItem(String menu_id, String menu_role_id, String text, String menu_url, String menu_f_id,
			String order_num, String menu_type, List<MenuItem> children) {
		super();
		this.menu_id = menu_id;
		this.menu_role_id = menu_role_id;
		this.text = text;
		this.menu_url = menu_url;
		this.menu_f_id = menu_f_id;
		this.order_num = order_num;
		this.menu_type = menu_type;
		this.children = children;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	
	public String getMenu_role_id() {
		return menu_role_id;
	}
	public void setMenu_role_id(String menu_role_id) {
		this.menu_role_id = menu_role_id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public String getMenu_f_id() {
		return menu_f_id;
	}
	public void setMenu_f_id(String menu_f_id) {
		this.menu_f_id = menu_f_id;
	}
	
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public List<MenuItem> getChildren() {
		return children;
	}
	public void setChildren(List<MenuItem> children) {
		this.children = children;
	}
	
	public String getMenu_des() {
		return menu_des;
	}

	public void setMenu_des(String menu_des) {
		this.menu_des = menu_des;
	}

	@Override
	public String toString() {
		return "MenuItem [menu_id=" + menu_id + ", menu_role_id=" + menu_role_id + ", text=" + text + ", menu_url="
				+ menu_url + ", menu_f_id=" + menu_f_id + ", order_num=" + order_num + ", menu_type=" + menu_type
				+ ", children=" + children + "]";
	}
	
	
	
}
