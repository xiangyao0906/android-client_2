package com.xinly.dendrobe.module.user

import android.app.Application
import androidx.databinding.ObservableField
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.ext.showAtCenter
import com.xinly.core.ext.yes
import com.xinly.dendrobe.api.UserApi
import com.xinly.dendrobe.base.BaseToolBarViewModel
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.helper.AccountManager
import com.xinly.dendrobe.model.vo.result.ChangeUserData

/**
 * Created by zm on 2019-07-04.
 */
class InviterBindingViewModel(application: Application): BaseToolBarViewModel(application) {

    // 邀请人
    val recommendId by lazy { ObservableField<String>() }

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    //event
    // 绑定推荐人
    val bindingClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            checkParams().yes {
                UserApi().changeInvite(recommendId.get()!!, object :XinlyRxSubscriberHelper<ChangeUserData>(){
                    override fun _onNext(t: ChangeUserData) {
                        AccountManager.instance.updateAccount(t.member)
                        finish()
                    }

                }, lifecycleProvider)
            }
        }

    })

    //normal fun
    private fun initData() {
        toolBarData.titleText = "绑定邀请人"
    }
    private fun checkParams(): Boolean{
        if (recommendId.get().isNullOrEmpty()) {
            "请输入邀请人ID".showAtCenter()
            return false
        }
        return true
    }
}