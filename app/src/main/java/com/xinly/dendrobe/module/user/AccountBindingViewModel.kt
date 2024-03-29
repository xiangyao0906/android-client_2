package com.xinly.dendrobe.module.user

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.hwangjr.rxbus.RxBus
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.show
import com.xinly.core.ext.showAtCenter
import com.xinly.core.rx.BaseSubscriber
import com.xinly.dendrobe.api.SystemApi
import com.xinly.dendrobe.api.UserApi
import com.xinly.dendrobe.base.BaseToolBarViewModel
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.helper.AccountManager
import com.xinly.dendrobe.model.constans.BusAction
import com.xinly.dendrobe.model.vo.bean.Event
import com.xinly.dendrobe.model.vo.result.ChangeUserData
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.ResponseBody
import java.util.concurrent.TimeUnit

/**
 * Created by zm on 2019-07-04.
 */
class AccountBindingViewModel(application: Application): BaseToolBarViewModel(application) {
    // 绑定类型 0手机号码 1邮箱账号
    val type by lazy { ObservableInt() }
    //账号绑定
    val accountName = ObservableField<String>()
    //图形码绑定
    val imageCode = ObservableField<String>()
    val imgCodeBmp = ObservableField<Bitmap>()
    //验证码
    val verifCode = ObservableField<String>()
    val verifBtnText = ObservableField<String>()
    val verifBtnEnabled = ObservableField<Boolean>()

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    //event
    // 登陆
    // 发送验证码事件
    val sendCodeClick: BindingCommand<Nothing> = BindingCommand(object: BindingAction {
        override fun call() {
            sendCode()
        }
    })
    // 刷新图形码事件
    val refreshImgCodeClick: BindingCommand<Nothing> = BindingCommand(object: BindingAction {
        override fun call() {
            requestImgCode()
        }
    })
   
    // 下一步
    val nextClick: BindingCommand<Nothing> = BindingCommand(object: BindingAction {
        override fun call() {
            if (checkParams()) {
                if (type.get()==0){
                    UserApi().changeMobile(accountName.get()!!, imageCode.get()!!, object : XinlyRxSubscriberHelper<ChangeUserData>(){
                        override fun _onNext(t: ChangeUserData) {
                            AccountManager.instance.updateAccount(t.member)
                            RxBus.get().post(BusAction.UPDATE_USER_INFO, Event.MessageEvent())
                            finish()
                        }

                    }, lifecycleProvider)
                }else {
                    UserApi().changeEmail(accountName.get()!!, imageCode.get()!!, object : XinlyRxSubscriberHelper<ChangeUserData>(){
                        override fun _onNext(t: ChangeUserData) {
                            AccountManager.instance.updateAccount(t.member)
                            RxBus.get().post(BusAction.UPDATE_USER_INFO)
                            finish()
                        }

                    }, lifecycleProvider)
                }

            }
        }
    })

    //net
    // 获取图形码
    private fun requestImgCode() {
        SystemApi().getImageCode(object : BaseSubscriber<ResponseBody>(){
            override fun onNext(t: ResponseBody) {
                imgCodeBmp.set(BitmapFactory.decodeStream(t.byteStream()))
            }
        }, lifecycleProvider)
    }
    // 发送验证码
    private fun sendCode() {
        if (checkAccount()&&checkImgCode()) {
            val params = HashMap<String, String>()
            params["type"] = if (type.get()==0)"mobile" else "email"
            params["target"] = accountName.get()!!
            params["code"] = imageCode.get()!!

            SystemApi().senCode(params, object : XinlyRxSubscriberHelper<BaseResp<Nothing>>(){
                override fun _onNext(t: BaseResp<Nothing>) {
                    countdown()
                    "验证码发送成功".showAtCenter()
                }

            }, lifecycleProvider)
        }
    }

    //normal fun
    // 验证码倒计时
    @SuppressLint("CheckResult")
    private fun countdown() {
        Flowable.intervalRange(0, 61, 0, 1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .doOnNext {
                verifBtnEnabled.set(false)
                verifBtnText.set("重新获取(${60-it})")
            }
            .doOnComplete {
                verifBtnText.set("获取验证码")
                verifBtnEnabled.set(true)
            }.subscribe()
    }
    // 数据展示
    private fun initData() {
        toolBarData.titleText = if (type.get() == 0) "绑定手机" else "绑定邮箱"
        verifBtnText.set("获取验证码")
        verifBtnEnabled.set(true)
        requestImgCode()
    }
    // 校验账号合法性
    private fun checkAccount(): Boolean {
        if (accountName.get().isNullOrEmpty()){
            val accountHint: String = if (type.get()==0) "请输入手机号码" else "请输入邮箱账号"
            accountHint.show()
            return false
        }
        return true
    }
    // 校验账号合法性
    private fun checkImgCode(): Boolean {
        if (imageCode.get().isNullOrEmpty()){
            "请输入图形码".show()
            return false
        }
        return true
    }
    // 校验注册参数合法性
    private fun checkParams(): Boolean{
        if (!checkAccount()) {
            return false
        }
        if (!checkImgCode()) {
            return false
        }
        if (verifCode.get().isNullOrEmpty()) {
            "请输入验证码".show()
            return false
        }
        return true
    }
}