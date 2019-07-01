package com.xinly.dendrobe.module.login

import android.app.Application
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.ext.show
import com.xinly.core.viewmodel.BaseViewModel
import com.xinly.dendrobe.module.register.RegisterActivity

/**
 * Created by zm on 2019-06-28.
 */
class LoginRegisterViewModel(application: Application): BaseViewModel(application) {
    /**
     * 手机登录
     */
    val mobileLogin: BindingCommand<Nothing> = BindingCommand(object:BindingAction{
        override fun call() {
            startActivity(LoginActivity::class.java)
        }

    })

    /**
     * 邮箱登录
     */
    val emailLogin: BindingCommand<Nothing> = BindingCommand(object:BindingAction{
        override fun call() {
            "邮箱登录".show()
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