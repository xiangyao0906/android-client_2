package com.xinly.dendrobe.module.user

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.showAtCenter
import com.xinly.dendrobe.api.UserApi
import com.xinly.dendrobe.base.BaseToolBarViewModel
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper

/**
 * Created by zm on 2019-07-01.
 */
class SetPwdViewModel(application: Application): BaseToolBarViewModel(application) {
    // 账号
    lateinit var account: String
    // 找回方式 0手机找回 1邮箱找回
    var type: Int = FindPasswordActivity.TYPE_MOBILE
    // 密码
    val passWord by lazy{ ObservableField<String>() }
    val confirmPwd by lazy{ ObservableField<String>() }

    override fun onCreate() {
        super.onCreate()
        toolBarData.titleText = "设置新密码"
    }

    // 界面发生改变的观察者
    val uic = UIChangeObservable()
    class UIChangeObservable{
        //密码开关观察者
        val pSwitchObservable = ObservableBoolean(true)
    }

    //event
    //密码显示开关
    val passwordShowSwitchOnClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
            uic.pSwitchObservable.set(!uic.pSwitchObservable.get())
        }

    })
    //设置密码
    val setPasswordClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            if (checkParams()) {
                val typeString = if (type==0)"mobile" else "email"
                UserApi().setPwd(typeString, account, passWord.get()!!, object:XinlyRxSubscriberHelper<BaseResp<Nothing>>(){
                    override fun _onNext(t: BaseResp<Nothing>) {
                        "密码修改成功".showAtCenter()
                        finish()
                    }

                }, lifecycleProvider)
            }
        }
    })

    //normal fun
    // 校验注册参数合法性
    private fun checkParams(): Boolean{
        if (passWord.get().isNullOrEmpty()) {
            "请输入新密码".showAtCenter()
            return false
        }
        if (confirmPwd.get().isNullOrEmpty()) {
            "请输入确认密码".showAtCenter()
            return false
        }
        if (passWord.get() != confirmPwd.get()) {
            "两次密码输入不一致".showAtCenter()
            return false
        }
        return true
    }
}