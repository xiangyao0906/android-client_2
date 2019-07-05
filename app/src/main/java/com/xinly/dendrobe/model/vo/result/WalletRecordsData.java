package com.xinly.dendrobe.model.vo.result;

import com.xinly.dendrobe.model.vo.bean.WalletRecordBean;
import com.xinly.dendrobe.model.vo.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by zm on 2019-07-05.
 */
public class WalletRecordsData {
    private UserBean member;
    private ArrayList<WalletRecordBean> list;

    public UserBean getMember() {
        return member;
    }

    public void setMember(UserBean member) {
        this.member = member;
    }

    public ArrayList<WalletRecordBean> getList() {
        return list;
    }

    public void setList(ArrayList<WalletRecordBean> list) {
        this.list = list;
    }
}
