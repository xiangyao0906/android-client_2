package com.xinly.dendrobe.module.register

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.show
import com.xinly.core.ext.yes
import com.xinly.dendrobe.R
import com.xinly.dendrobe.api.SystemApi
import com.xinly.dendrobe.api.UserApi
import com.xinly.dendrobe.base.BaseToolBarViewModel
import com.xinly.dendrobe.component.net.TokenManager
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.model.constans.Constans
import com.xinly.dendrobe.model.vo.result.RegisterData

/**
 * Created by zm on 2019-06-28.
 */
class RegisterViewModel(application: Application) : BaseToolBarViewModel(application) {
    //账号绑定
    val accountName: ObservableField<String> = ObservableField("")
    val accountHint: ObservableField<String> = ObservableField("")
    val accountIcon: ObservableField<Int> = ObservableField(0)
    //图形码绑定
    val imageCode = ObservableField("")
    val imgCodeUrl = ObservableField("")
    //验证码
    val verifCode = ObservableField("")
    //登陆密码
    val passWord = ObservableField("")
    //推荐人ID
    val recommendId = ObservableField("")
    //是否同意用户协议
    val agree = ObservableBoolean(false)

    //封装一个界面发生改变的观察者
    val uic:  UIChangeObservable = UIChangeObservable()
    class UIChangeObservable {
        //注册方式 0手机注册 1邮箱注册
        val regType = ObservableInt(0)
    }

    override fun onCreate() {
        showData()
    }

    //event
    override fun handRightText() {
        uic.regType.set(if (uic.regType.get() == 0) 1 else 0)
        showData()
    }
    // 注册事件
    val goRegisterClick: BindingCommand<Nothing> = BindingCommand(object: BindingAction {
        override fun call() {
            next()
        }
    })
    // 发送验证码事件
    val sendCodeClick: BindingCommand<Nothing> = BindingCommand(object: BindingAction {
        override fun call() {
            senCode()
        }
    })
    // 刷新图形码事件
    val refreshImgCodeClick: BindingCommand<Nothing> = BindingCommand(object: BindingAction {
        override fun call() {
           if (checkAccount()) {
               imgCodeUrl.set(Constans.URL_IMAGE_CODE.plus(accountName.get()))
           }
        }
    })
    // 查看用户协议
    val agreementClick: BindingCommand<Nothing> = BindingCommand(object: BindingAction {
        override fun call() {
           "查看用户协议".show()
        }
    })
    // 登陆
    // 登陆
    val loginClick: BindingCommand<Nothing> = BindingCommand(object: BindingAction {
        override fun call() {
            "登陆".show()
        }
    })

    //next
    // 发送验证码
    private fun senCode() {
        if (checkAccount()) {
            val params = HashMap<String, String>()
            params["type"] = if (uic.regType.get()==0)"mobile" else "email"
            params["target"] = accountName.get()!!
            params["code"] = imageCode.get()!!

            SystemApi().senCode(params, object : XinlyRxSubscriberHelper<BaseResp<Nothing>>(){
                override fun _onNext(t: BaseResp<Nothing>) {
                    "验证码发送成功".show()
                }

            }, lifecycleProvider)
        }
    }
    // 下一步
    fun next() {
        checkParams().yes {
            val params = HashMap<String,String>()
            params["type"] = if (uic.regType.get()==0)"mobile" else "email"
            params["account"]= accountName.get()!!
            params["password"]= passWord.get()!!
            params["code"]= verifCode.get()!!
            params["inviteCode"]= recommendId.get()!!

            UserApi().register(params, object : XinlyRxSubscriberHelper<RegisterData>(){
                override fun _onNext(t: RegisterData) {
                    TokenManager.updateToken(t.token)
                }
            }, lifecycleProvider)
        }
    }
    // 数据展示
   private fun showData() {
        toolBarData.rightText = if (uic.regType.get() == 0) "邮箱注册" else "手机注册"
        toolBarData.titleText = if (uic.regType.get() == 0) "手机注册" else "邮箱注册"
        accountHint.set(if (uic.regType.get() == 0) "请输入手机号码" else "请输入邮箱账号")
        accountIcon.set(if (uic.regType.get() == 0) R.drawable.reg_mobile else R.drawable.reg_email)
    }

    //normal fun
    // 校验账号合法性
    private fun checkAccount(): Boolean {
        if (accountName.get().isNullOrEmpty()){
            val accountHint: String = if (uic.regType.get()==0) "请输入手机号码" else "请输入邮箱账号"
            accountHint.show()
            return false
        }
        return true
    }
    // 校验注册参数合法性
    private fun checkParams(): Boolean{
        if (!checkAccount()) {
            return false
        }
        if (imageCode.get().isNullOrEmpty()) {
            "请输入图形码".show()
            return false
        }
        if (verifCode.get().isNullOrEmpty()) {
            "请输入验证码".show()
            return false
        }
        if (passWord.get().isNullOrEmpty()) {
            "请输入登陆密码".show()
            return false
        }
        if (!agree.get()){
            "注册需阅读并同意用户协议".show()
            return false
        }
        return true
    }
}