package com.wh.util.msp;

/**
 * Created by danding on 2015/8/17.
 */
public class AccessToken {
    //获取到的凭证
    private String token;
    //凭证有效时间，单位：秒
    private int expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
