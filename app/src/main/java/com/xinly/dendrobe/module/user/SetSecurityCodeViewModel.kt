package com.xinly.dendrobe.module.user

import android.app.Application
import com.xinly.core.ext.showAtCenter
import com.xinly.dendrobe.api.UserApi
import com.xinly.dendrobe.base.BaseToolBarViewModel
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.model.vo.result.ChangeUserData

/**
 * Created by zm on 2019-07-04.
 */
class SetSecurityCodeViewModel(application: Application): BaseToolBarViewModel(application) {

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    //normal fun
    private fun initData() {
        toolBarData.titleText = "设置安全密码"
    }

    /**
     * 修改安全密码
     */
    fun changeSecurity(security: String, code: String) {
        UserApi().changeSecurity(security, code, object : XinlyRxSubscriberHelper<ChangeUserData>() {
            override fun _onNext(t: ChangeUserData) {
                "安全密码修改成功".showAtCenter()
                finish()
            }

        }, lifecycleProvider)
    }
}