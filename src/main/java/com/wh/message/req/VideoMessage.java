package com.wh.message.req;

/**
 * 请求：视频消息
 * Created by danding on 2015/8/14.
 */
public class VideoMessage extends BaseMessage{
    private String MediaId;
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }


}
