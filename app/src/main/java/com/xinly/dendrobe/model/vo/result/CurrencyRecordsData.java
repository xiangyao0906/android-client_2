package com.xinly.dendrobe.model.vo.result;

import com.xinly.dendrobe.model.vo.bean.CurrencyBean;
import com.xinly.dendrobe.model.vo.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by zm on 2019-07-05.
 */
public class CurrencyRecordsData {
    private UserBean member;
    private ArrayList<CurrencyBean> list;

    public UserBean getMember() {
        return member;
    }

    public void setMember(UserBean member) {
        this.member = member;
    }

    public ArrayList<CurrencyBean> getList() {
        return list;
    }

    public void setList(ArrayList<CurrencyBean> list) {
        this.list = list;
    }
}
