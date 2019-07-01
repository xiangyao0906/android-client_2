package com.xinly.dendrobe.module.user

import android.app.Application
import android.os.Bundle
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.viewmodel.BaseViewModel

/**
 * Created by zm on 2019-06-28.
 */
class LoginRegisterViewModel(application: Application): BaseViewModel(application) {

    /**
     * 手机登录
     */
    val mobileLogin: BindingCommand<Nothing> = BindingCommand(object:BindingAction{
        override fun call() {
            val bundle = Bundle()
            bundle.putInt(LoginActivity.EXTRAS_REGISTER_TYPE, LoginActivity.TYPE_MOBILE)
            startActivity(LoginActivity::class.java, bundle)
        }

    })

    /**
     * 邮箱登录
     */
    val emailLogin: BindingCommand<Nothing> = BindingCommand(object:BindingAction{
        override fun call() {
            val bundle = Bundle()
            bundle.putInt(LoginActivity.EXTRAS_REGISTER_TYPE, LoginActivity.TYPE_EMAIL)
            startActivity(LoginActivity::class.java, bundle)
        }

    })

    /**
     * 注册
     */
    val goRegister: BindingCommand<Nothing> = BindingCommand(object:BindingAction{
        override fun call() {
            // 跳转至注册页面
            startActivity(RegisterActivity::class.java)
        }

    })
}