package com.wh.message.custom;

/**
 * 客服账户
 * Created by danding on 2015/8/20.
 */
public class CustomAccount {
    private String kf_account;//客服账号
    private String nickname;//客服昵称
    private String password;//客服密码

    public String getKf_account() {
        return kf_account;
    }

    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
