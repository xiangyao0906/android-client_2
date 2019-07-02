package com.xinly.dendrobe.model.local.sp

import com.xinly.dendrobe.model.vo.bean.UserBean
import com.xinly.dendrobe.util.PrefsUtils

/**
 * Created by zm on 2019-07-01.
 */
class AccountSharedPreferences private constructor(){

    companion object {
        private val ACCOUNT_TOKEN = "ACCOUNT_TOKEN"
        private val ACCOUNT_DATA = "ACCOUNT_DATA"
        val instance: AccountSharedPreferences by lazy(LazyThreadSafetyMode.SYNCHRONIZED){ AccountSharedPreferences() }
    }

    /**
     * 更新token
     * @param token
     */
    fun updateToken(token: String) {
        PrefsUtils.putString(ACCOUNT_TOKEN, token)
    }

    /**
     * 清除token
     */
    fun clearToken() {
        PrefsUtils.remove(ACCOUNT_TOKEN)
    }

    /**
     * 获取token
     *
     */
    fun getToken(): String {
        return PrefsUtils.getString(ACCOUNT_TOKEN)
    }

    /**
     * 更新用户数据
     * @param userBean
     */
    fun updateAccountData(userBean: UserBean) {
        PrefsUtils.putObject(ACCOUNT_DATA, userBean)
    }

    /**
     * 清除用户数据
     */
    fun clearAccountData(){
        PrefsUtils.remove(ACCOUNT_DATA)
    }

    /**
     * 获取用户数据
     * @return
     */
    fun getAccountData(): UserBean? {
        return PrefsUtils.getObject(ACCOUNT_DATA, UserBean::class.java)
    }
}