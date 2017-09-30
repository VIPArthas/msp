package com.wh.message.custom;

/**
 * Created by danding on 2015/8/19.
 */
public class ImageMessage extends BaseMessage {
    private Media image;

    public Media getImage() {
        return image;
    }

    public void setImage(Media image) {
        this.image = image;
    }
}
