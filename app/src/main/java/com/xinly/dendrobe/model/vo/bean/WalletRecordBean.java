package com.xinly.dendrobe.model.vo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zm on 2019-07-05.
 */
public class WalletRecordBean {

    /**
     * content : 测试添加
     * createTime : 1561535021218
     * num : 1
     * new : 16
     * orderId : 8287001561535021941
     */

    private String content;
    private long createTime;
    private double num;
    @SerializedName("new")
    private double newNum;
    private String orderId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public double getNewNum() {
        return newNum;
    }

    public void setNewNum(double newNum) {
        this.newNum = newNum;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
