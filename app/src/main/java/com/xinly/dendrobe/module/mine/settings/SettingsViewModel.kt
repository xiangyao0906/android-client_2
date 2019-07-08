package com.xinly.dendrobe.module.mine.settings

import android.app.Application
import androidx.databinding.ObservableField
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.ext.showAtCenter
import com.xinly.dendrobe.BuildConfig
import com.xinly.dendrobe.base.BaseToolBarViewModel
import com.xinly.dendrobe.helper.AccountManager

/**
 * Created by zm on 2019-07-05.
 */
class SettingsViewModel(application: Application): BaseToolBarViewModel(application) {

    // 版本号
    val versionName by lazy { ObservableField<String>() }

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    //net
    private fun logout() {
        AccountManager.instance.logout()
    }

    //normal fun
    private fun initData() {
        toolBarData.titleText = "账户设置"
        versionName.set("v".plus(BuildConfig.VERSION_NAME))
    }

    //event
    //退出登录
    val logout: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            "退出登录成功".showAtCenter()
            logout()
        }

    })
    //版本号
    val verHistoryCLick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            "历史版本".showAtCenter()
        }

    })
    //建议反馈
    val feedbackClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            "建议反馈".showAtCenter()
        }

    })
    //关于我们
    val aboutUsClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            "关于我们".showAtCenter()
        }

    })
    //清理缓存
    val clearCache: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            "清理成功".showAtCenter()
        }

    })
}