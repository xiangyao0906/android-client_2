package com.xinly.dendrobe.helper

import android.content.Intent
import com.xinly.dendrobe.XinlyApplication
import com.xinly.dendrobe.component.net.TokenManager
import com.xinly.dendrobe.model.local.sp.AccountSharedPreferences
import com.xinly.dendrobe.model.vo.bean.UserBean
import com.xinly.dendrobe.module.user.LoginActivity

/**
 * 用户信息管理
 * <p>
 * Created by zm on 2019-07-01.
 */
class AccountManager private constructor(){

    var mUserBean: UserBean? = null

    companion object {
        val instance: AccountManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED){ AccountManager() }
    }

    /**
     * 初始化账户信息
     */
    private fun initAccount(): Boolean {
        val userBean = AccountSharedPreferences.instance.getAccountData()
        return updateAccount(userBean, false)
    }

    /**
     * 更新账户信息
     */
    fun updateAccount(userBean: UserBean?) {
        updateAccount(userBean, true)
    }

    /**
     * 更新账户信息
     * @param userBean
     * @param isUpdateLocal 是否同时更新持久化
     * @return
     */
    private fun updateAccount(userBean: UserBean?, isUpdateLocal: Boolean): Boolean {
        if (userBean == null) return false
        mUserBean = if (mUserBean == null) {
            userBean
        }else{
            // 修改内存
            mUserBean?.updateSelf(userBean)
        }
        // 修改本地
        if (isUpdateLocal) {
            mUserBean?.let {
                AccountSharedPreferences.instance.updateAccountData(it)
            }
        }
        return true
    }

    private fun getAccountName(): String? {
        if (mUserBean == null) {
            initAccount()
        }
        if (mUserBean == null){
            return null
        }
        return mUserBean?.mobile ?: mUserBean?.email
    }

    /**
     * token 存在
     * mobile or email 存在
     * @return true -> logined false unlogin
     */
    fun isLogin(): Boolean {
        // token 为空未登陆
        if (TokenManager.getToken().isEmpty()) {
            return false
        }
        // 手机号&&邮箱为空 未登录
       if (getAccountName().isNullOrEmpty()){
           return false
       }
        return true
    }

    // 登出标志
    private var logoutFlag = false

    fun setLogoutFlag(logoutFlag: Boolean) {
        this.logoutFlag = logoutFlag
    }

    /**
     * 权限失效登出
     */
    fun logout() {
        if (logoutFlag || !isLogin()) return
        logoutFlag = true
        val intent = Intent()
        intent.setClass(XinlyApplication.context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        XinlyApplication.context.startActivity(intent)
        clear()
    }

    /**
     * 清除数据
     */
    private fun clear() {
        TokenManager.clearToken()
        AccountSharedPreferences.instance.clearAccountData()
        mUserBean = null
    }
}