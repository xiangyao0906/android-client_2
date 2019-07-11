package com.xinly.dendrobe.module.user

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.show
import com.xinly.core.ext.showAtCenter
import com.xinly.core.ext.yes
import com.xinly.core.rx.BaseSubscriber
import com.xinly.core.utils.EncryptUtils
import com.xinly.dendrobe.R
import com.xinly.dendrobe.api.SystemApi
import com.xinly.dendrobe.api.UserApi
import com.xinly.dendrobe.base.BaseToolBarViewModel
import com.xinly.dendrobe.component.net.TokenManager
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.model.vo.result.RegisterData
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.ResponseBody
import java.util.concurrent.TimeUnit

/**
 * Created by zm on 2019-06-28.
 */
class RegisterViewModel(application: Application) : BaseToolBarViewModel(application) {
    //账号绑定
    val accountName = ObservableField<String>()
    val accountHint = ObservableField<String>()
    val accountIcon = ObservableField<Int>()
    //图形码绑定
    val imageCode = ObservableField<String>()
    val imgCodeBmp = ObservableField<Bitmap>()
    //验证码
    val verifCode = ObservableField<String>()
    val verifBtnText = ObservableField<String>()
    val verifBtnEnabled = ObservableField<Boolean>()
    //登陆密码
    val passWord = ObservableField("")
    //推荐人ID
    val recommendId = ObservableField("")
    //是否同意用户协议
    val agree = ObservableBoolean(false)

    //封装一个界面发生改变的观察者
    val uic: UIChangeObservable =
        UIChangeObservable()
    class UIChangeObservable {
        //注册方式 0手机注册 1邮箱注册
        val regType = ObservableInt(0)
    }

    override fun onCreate() {
        super.onCreate()
        requestImgCode()
        showData()
    }

    override fun onDestroy() {
        super.onDestroy()
        imgCodeBmp.get()?.recycle()
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
            requestImgCode()
        }
    })
    // 查看用户协议
    val agreementClick: BindingCommand<Nothing> = BindingCommand(object: BindingAction {
        override fun call() {
           "查看用户协议".show()
        }
    })
    // 登陆
    val loginClick: BindingCommand<Nothing> = BindingCommand(object: BindingAction {
        override fun call() {
            finish()
        }
    })

    //next
    // 获取图形码
    private fun requestImgCode() {
        SystemApi().getImageCode(object : BaseSubscriber<ResponseBody>(){
            override fun onNext(t: ResponseBody) {
                imgCodeBmp.set(BitmapFactory.decodeStream(t.byteStream()))
            }
        }, lifecycleProvider)
    }
    // 发送验证码
    private fun senCode() {
        if (checkAccount()&&checkImgCode()) {
            val params = HashMap<String, String>()
            params["type"] = if (uic.regType.get()==0)"mobile" else "email"
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
    // 下一步
    fun next() {
        checkParams().yes {
            val params = HashMap<String,String>()
            params["type"] = if (uic.regType.get()==0)"mobile" else "email"
            params["account"] = accountName.get()!!
            params["password"] = EncryptUtils.encryptMD5ToString(passWord.get()!!)
            params["code"] = verifCode.get()!!
            params["inviteCode"] = recommendId.get()!!

            UserApi().register(params, object : XinlyRxSubscriberHelper<RegisterData>(){
                override fun _onNext(t: RegisterData) {
                    "注册成功".show()
                    TokenManager.updateToken(t.token)
                    startActivity(BasicInfoActivity::class.java)
                    finish()
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
    private fun showData() {
        toolBarData.rightText = if (uic.regType.get() == 0) "邮箱注册" else "手机注册"
        toolBarData.titleText = if (uic.regType.get() == 0) "手机注册" else "邮箱注册"
        accountHint.set(if (uic.regType.get() == 0) "请输入手机号码" else "请输入邮箱账号")
        accountIcon.set(if (uic.regType.get() == 0) R.drawable.reg_mobile else R.drawable.reg_email)
        verifBtnText.set("获取验证码")
        verifBtnEnabled.set(true)
    }
    // 校验账号合法性
    private fun checkAccount(): Boolean {
        if (accountName.get().isNullOrEmpty()){
            val accountHint: String = if (uic.regType.get()==0) "请输入手机号码" else "请输入邮箱账号"
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