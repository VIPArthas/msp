package com.wh.mspentity;

import java.util.Date;

import com.wh.util.BaseModel;

/**
 * 随手拍主表
 * @author Administrator
 *
 */
public class MspPhoto extends BaseModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String userId;

    private String msg;

    private Integer commentNum;

    private Integer pageviewNum;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getPageviewNum() {
        return pageviewNum;
    }

    public void setPageviewNum(Integer pageviewNum) {
        this.pageviewNum = pageviewNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}