package com.xinly.dendrobe.component.net

import com.xinly.dendrobe.model.local.sp.AccountSharedPreferences

/**
 * Created by zm on 2019-06-27.
 */
object TokenManager {
    const val TOKEN = "token"

    /**
     * 全局token
     */
    private var sToken: String = ""

    /**
     * 初始化Token
     */
    private fun initToken(): String {
        // 从本地取
        sToken = AccountSharedPreferences.instance.getToken()
        return sToken
    }

    /**
     * 获取Token
     */
    fun getToken(): String {
        if (sToken.isEmpty()) {
            initToken()
        }
        return sToken
    }

    /**
     * 更新Token
     * @param token
     */
    fun updateToken(token: String) {
        sToken = token
        AccountSharedPreferences.instance.updateToken(sToken)
    }

    /**
     * 清除Token
     */
    fun clearToken() {
        AccountSharedPreferences.instance.clearToken()
    }
}