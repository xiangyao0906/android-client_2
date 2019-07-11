package com.xinly.dendrobe.model.vo.bean;

/**
 * Created by zm on 2019-07-08.
 */
public class BankBean {
    private int id; //银行卡ID
    private String bankCode; //银行卡号
    private String bankName; //银行名称
    private String openingBank; //开户银行
    private String openingPhone; //开户手机号
    private String openingName; //开户名称
    private int flag; // 1默认

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getOpeningBank() {
        return openingBank;
    }

    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }

    public String getOpeningPhone() {
        return openingPhone;
    }

    public void setOpeningPhone(String openingPhone) {
        this.openingPhone = openingPhone;
    }

    public String getOpeningName() {
        return openingName;
    }

    public void setOpeningName(String openingName) {
        this.openingName = openingName;
    }
}
