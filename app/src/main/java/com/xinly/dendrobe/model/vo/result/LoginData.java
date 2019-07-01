package com.xinly.dendrobe.model.vo.result;

import com.xinly.dendrobe.model.vo.bean.UserBean;

/**
 * Created by zm on 2019-07-01.
 */
public class LoginData {
    private UserBean member;
    private String token;

    public UserBean getMember() {
        return member;
    }

    public void setMember(UserBean member) {
        this.member = member;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
