package com.xinly.dendrobe.module.user

import android.app.Application
import android.os.Bundle
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.ext.show
import com.xinly.core.viewmodel.BaseViewModel
import com.xinly.dendrobe.api.UserApi
import com.xinly.dendrobe.component.net.TokenManager
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.model.vo.result.LoginData

/**
 * Created by zm on 2019-06-28.
 */
class LoginViewModel(application: Application): BaseViewModel(application) {
    // 登陆类型
    val loginType by lazy { ObservableInt() }

    // 账户
    val accountName by lazy {ObservableField<String>()}
    val accountHint by lazy {ObservableField<String>()}
    // 密码
    val passWord by lazy {ObservableField<String>()}
    // 登陆类型
    val loginTypeText by lazy {ObservableField<String>()}

    override fun onCreate() {
        super.onCreate()
        showData()
    }

    // 界面发生改变的观察者
    val uic = UIChangeObservable()
    class UIChangeObservable{
        //密码开关观察者
        val pSwitchObservable = ObservableBoolean(false)
    }

    //event
    //关闭
    val closeClick: BindingCommand<Nothing> = BindingCommand(object :BindingAction{
        override fun call() {
            finish()
        }
    })
    //用户协议
    val agreementClick: BindingCommand<Nothing> = BindingCommand(object :BindingAction{
        override fun call() {
           "用户协议".show()
        }
    })
    //密码显示开关
    val passwordShowSwitchOnClick: BindingCommand<Nothing> = BindingCommand(object :BindingAction{
        override fun call() {
            //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
            uic.pSwitchObservable.set(!uic.pSwitchObservable.get())
        }

    })
    //忘记密码
    val forgetPwdClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            val bundle = Bundle()
            bundle.putInt(FindPasswordActivity.EXTRAS_FIND_TYPE, loginType.get())
            startActivity(FindPasswordActivity::class.java, bundle)
        }

    })
    //切换登陆
    val switchLoginClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            loginType.set(if (loginType.get()==LoginActivity.TYPE_MOBILE)LoginActivity.TYPE_EMAIL else LoginActivity.TYPE_MOBILE)
            showData()
        }

    })
    //注册
    val registerClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
           startActivity(RegisterActivity::class.java)
        }

    })
    //登陆
    val loginClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            if (checkParams()) {
                login()
            }
        }

    })

    //net
    //登陆
    private fun login() {
        val type = if (loginType.get()== LoginActivity.TYPE_MOBILE)"mobile" else "email"
        UserApi().login(type, accountName.get()!!, passWord.get()!!, object : XinlyRxSubscriberHelper<LoginData>(){
            override fun _onNext(t: LoginData) {
                TokenManager.updateToken(t.token)
            }

        },lifecycleProvider)
    }

    //normal fun
    //数据展示
    private fun showData() {
        accountHint.set(if (loginType.get()==LoginActivity.TYPE_MOBILE)"请输入手机号码" else "请输入邮箱账号")
        loginTypeText.set(if (loginType.get()==LoginActivity.TYPE_MOBILE)"邮箱登录" else "手机登录")
    }
    //参数效验
    private fun checkParams(): Boolean {
        if (accountName.get().isNullOrEmpty()){
            val accountHint: String = if (loginType.get()==0) "请输入手机号码" else "请输入邮箱账号"
            accountHint.show()
            return false
        }
        if (passWord.get().isNullOrEmpty()) {
            "请输入密码".show()
            return false
        }
        return true
    }
}