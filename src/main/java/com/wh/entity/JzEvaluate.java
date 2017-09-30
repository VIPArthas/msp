package com.wh.entity;

import com.wh.util.BaseModel;

import java.util.Date;
import java.util.List;

public class JzEvaluate extends BaseModel{
    private String id;

    private String jzId;

    private String jzOwner;

    private String evaluateContent;

    private Date evaluateDate;

    private Double evaluateLng;

    private Double evaluateLat;

    private String evaluateCity;

    private String evaluateArea;

    private String evaluateAddress;

    private Integer evaluateStatus;

    private String statusOperator;

    private Date statusOperateTime;

    private Integer evaluateScore;

    private String evaluateId;

    private String evaluateIdMobile;

    private Integer evaluateReward;

    private Date evaluateRewardDate;

    private Integer status;

    private String extend1;

    private String extend2;

    private String extend3;

    private String extend4;


    private Double idCount;//评价条数

    private Double numSum;//评价总分数

    private String jzTitle;
    private Date jzStartDate;
    private Date jzEndDate;

    private int rewardSum;//总奖励

    private String filePath;//图片路径

    private List<String> filePathList;//上传图片路径集合

    private List<WhAttachements> whAttachementsList;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getJzId() {
        return jzId;
    }

    public void setJzId(String jzId) {
        this.jzId = jzId == null ? null : jzId.trim();
    }

    public String getJzOwner() {
        return jzOwner;
    }

    public void setJzOwner(String jzOwner) {
        this.jzOwner = jzOwner == null ? null : jzOwner.trim();
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent == null ? null : evaluateContent.trim();
    }

    public Date getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(Date evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public Double getEvaluateLng() {
        return evaluateLng;
    }

    public void setEvaluateLng(Double evaluateLng) {
        this.evaluateLng = evaluateLng;
    }

    public Double getEvaluateLat() {
        return evaluateLat;
    }

    public void setEvaluateLat(Double evaluateLat) {
        this.evaluateLat = evaluateLat;
    }

    public String getEvaluateCity() {
        return evaluateCity;
    }

    public void setEvaluateCity(String evaluateCity) {
        this.evaluateCity = evaluateCity == null ? null : evaluateCity.trim();
    }

    public String getEvaluateArea() {
        return evaluateArea;
    }

    public void setEvaluateArea(String evaluateArea) {
        this.evaluateArea = evaluateArea == null ? null : evaluateArea.trim();
    }

    public String getEvaluateAddress() {
        return evaluateAddress;
    }

    public void setEvaluateAddress(String evaluateAddress) {
        this.evaluateAddress = evaluateAddress == null ? null : evaluateAddress.trim();
    }

    public Integer getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(Integer evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    public String getStatusOperator() {
        return statusOperator;
    }

    public void setStatusOperator(String statusOperator) {
        this.statusOperator = statusOperator == null ? null : statusOperator.trim();
    }

    public Date getStatusOperateTime() {
        return statusOperateTime;
    }

    public void setStatusOperateTime(Date statusOperateTime) {
        this.statusOperateTime = statusOperateTime;
    }

    public Integer getEvaluateScore() {
        return evaluateScore;
    }

    public void setEvaluateScore(Integer evaluateScore) {
        this.evaluateScore = evaluateScore;
    }

    public String getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(String evaluateId) {
        this.evaluateId = evaluateId == null ? null : evaluateId.trim();
    }

    public String getEvaluateIdMobile() {
        return evaluateIdMobile;
    }

    public void setEvaluateIdMobile(String evaluateIdMobile) {
        this.evaluateIdMobile = evaluateIdMobile == null ? null : evaluateIdMobile.trim();
    }

    public Integer getEvaluateReward() {
        return evaluateReward;
    }

    public void setEvaluateReward(Integer evaluateReward) {
        this.evaluateReward = evaluateReward;
    }

    public Date getEvaluateRewardDate() {
        return evaluateRewardDate;
    }

    public void setEvaluateRewardDate(Date evaluateRewardDate) {
        this.evaluateRewardDate = evaluateRewardDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1 == null ? null : extend1.trim();
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2 == null ? null : extend2.trim();
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3 == null ? null : extend3.trim();
    }

    public String getExtend4() {
        return extend4;
    }

    public void setExtend4(String extend4) {
        this.extend4 = extend4 == null ? null : extend4.trim();
    }


    public Double getIdCount() {
        return idCount;
    }

    public void setIdCount(Double idCount) {
        this.idCount = idCount;
    }

    public Double getNumSum() {
        return numSum;
    }

    public void setNumSum(Double numSum) {
        this.numSum = numSum;
    }

    public String getJzTitle() {
        return jzTitle;
    }

    public void setJzTitle(String jzTitle) {
        this.jzTitle = jzTitle;
    }

    public Date getJzStartDate() {
        return jzStartDate;
    }

    public void setJzStartDate(Date jzStartDate) {
        this.jzStartDate = jzStartDate;
    }

    public Date getJzEndDate() {
        return jzEndDate;
    }

    public void setJzEndDate(Date jzEndDate) {
        this.jzEndDate = jzEndDate;
    }

    public int getRewardSum() {
        return rewardSum;
    }

    public void setRewardSum(int rewardSum) {
        this.rewardSum = rewardSum;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getFilePathList() {
        return filePathList;
    }

    public void setFilePathList(List<String> filePathList) {
        this.filePathList = filePathList;
    }

    public List<WhAttachements> getWhAttachementsList() {
        return whAttachementsList;
    }

    public void setWhAttachementsList(List<WhAttachements> whAttachementsList) {
        this.whAttachementsList = whAttachementsList;
    }
}