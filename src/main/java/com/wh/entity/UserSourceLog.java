package com.wh.entity;

import java.util.Date;

public class UserSourceLog {
    private String id;

    private String sessionId;

    private String userId;

    private String logModel;

    private Integer logTimes;

    private String operateIp;

    private Integer operateTerminal;

    private Date operateTime;

    private Integer addScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLogModel() {
        return logModel;
    }

    public void setLogModel(String logModel) {
        this.logModel = logModel == null ? null : logModel.trim();
    }

    public Integer getLogTimes() {
        return logTimes;
    }

    public void setLogTimes(Integer logTimes) {
        this.logTimes = logTimes;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp == null ? null : operateIp.trim();
    }

    public Integer getOperateTerminal() {
        return operateTerminal;
    }

    public void setOperateTerminal(Integer operateTerminal) {
        this.operateTerminal = operateTerminal;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getAddScore() {
        return addScore;
    }

    public void setAddScore(Integer addScore) {
        this.addScore = addScore;
    }
}