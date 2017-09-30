package com.wh.message.custom;

/**
 * Created by danding on 2015/8/19.
 */
public class TextMessage extends BaseMessage {
    private Text text;

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
