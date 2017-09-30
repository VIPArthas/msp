package com.wh.entity;

import java.util.Date;

public class WhAttachements {
    private String id;

    private String linkId;

    private String fileName;

    private String filePath;

    private String fileType;

    private String fileSize;

    private String fileNote;

    private Byte type;

    private Integer num;

    private String imageDetail;
    
    private Date createTime;
    
    

    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public String getFileNote() {
        return fileNote;
    }

    public void setFileNote(String fileNote) {
        this.fileNote = fileNote == null ? null : fileNote.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImageDetail() {
        return imageDetail;
    }

    public void setImageDetail(String imageDetail) {
        this.imageDetail = imageDetail == null ? null : imageDetail.trim();
    }
}