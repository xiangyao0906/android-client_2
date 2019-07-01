package com.xinly.dendrobe.model.vo.bean;

/**
 * 用户信息
 * <p>
 * Created by zm on 2019-07-01.
 */
public class UserBean {
    private int code;
    private String nickname; //昵称
    private String realname; //真实姓名
    private String identity; //身份证号码
    private String mobile; //手机号码
    private String email; //邮箱号码
    private String avatar; //邮箱地址
    private Double bean; //青豆数量
    private Double dend; //石斛数量
    private long createTime;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getBean() {
        return bean;
    }

    public void setBean(Double bean) {
        this.bean = bean;
    }

    public Double getDend() {
        return dend;
    }

    public void setDend(Double dend) {
        this.dend = dend;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
