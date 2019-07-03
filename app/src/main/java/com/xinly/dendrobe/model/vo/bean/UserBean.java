package com.xinly.dendrobe.model.vo.bean;

import java.io.Serializable;

/**
 * 用户信息
 * <p>
 * Created by zm on 2019-07-01.
 */
public class UserBean implements Serializable, Cloneable {
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

    @Override
    protected UserBean clone() throws CloneNotSupportedException {
        return (UserBean) super.clone();
    }

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

    public UserBean updateSelf(UserBean userBean) {
        if (null == userBean) return this;
        if (null != userBean.avatar) {
            this.avatar = userBean.avatar;
        }
        if (null != userBean.nickname) {
            this.nickname = userBean.nickname;
        }
        if (0 != userBean.code) {
            this.code = userBean.code;
        }
        if (null != userBean.realname) {
            this.realname = userBean.realname;
        }
        if (null != userBean.identity) {
            this.identity = userBean.identity;
        }
        if (null != userBean.mobile) {
            this.mobile = userBean.mobile;
        }
        if (null != userBean.email) {
            this.email = userBean.email;
        }
        if (0 != userBean.bean) {
            this.bean = userBean.bean;
        }
        if (0 != userBean.dend) {
            this.dend = userBean.dend;
        }
        if (0 != userBean.createTime) {
            this.createTime = userBean.createTime;
        }
        return this;
    }
}