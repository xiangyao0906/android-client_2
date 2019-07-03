package com.xinly.dendrobe.module.user

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.show
import com.xinly.dendrobe.R
import com.xinly.dendrobe.api.FileApi
import com.xinly.dendrobe.api.UserApi
import com.xinly.dendrobe.base.BaseToolBarViewModel
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.model.vo.result.UploadImageData
import com.xinly.dendrobe.module.main.MainActivity

/**
 * Created by zm on 2019-07-02.
 */
class BasicInfoViewModel(application: Application): BaseToolBarViewModel(application) {

    // 选择头像
    val requestSelPicture by lazy { ObservableBoolean(false) }
    //昵称
    val nickName by lazy { ObservableField<String>() }
    //安全码
    val password by lazy { ObservableField<String>() }
    //确认密码
    val confirmPwd by lazy { ObservableField<String>() }
    //头像
    val avatarUrl by lazy { ObservableField<String>() }
    val placeholderRes:  Int = R.drawable.icon_add

    override fun onCreate() {
        super.onCreate()
        initToolBar()
    }

    //event
    //选择头像
    val sltPhotoClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            requestSelPicture.set(!requestSelPicture.get())
        }

    })
    //提交事件
    val submitClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            if (checkParams()) {
                submitData()
            }
        }

    })

    //net
    //提交用户资料
    private fun submitData() {
        UserApi().submitData(avatarUrl.get()!!, nickName.get()!!, password.get()!!, object : XinlyRxSubscriberHelper<BaseResp<Nothing>>(){
            override fun _onNext(t: BaseResp<Nothing>) {
                startActivity(MainActivity::class.java)
            }

        }, lifecycleProvider)
    }
    //上传文件
    fun uploadFile(filePath: String) {

        FileApi().uploadFile(filePath,"avatar", object : XinlyRxSubscriberHelper<UploadImageData>() {
            override fun _onNext(t: UploadImageData) {
                avatarUrl.set(t.url)
            }

        }, lifecycleProvider)
    }

    //normal fun
    //初始化ToolBar
    private fun initToolBar() {
        toolBarData.titleText = "完善资料"
    }
    //效验参数合法性
    private fun checkParams(): Boolean {
        if (avatarUrl.get().isNullOrEmpty()) {
            "请上传头像".show()
        }
        if (nickName.get().isNullOrEmpty()) {
            "请输入昵称".show()
            return false
        }
        if (password.get().isNullOrEmpty()) {
            "请输入安全码".show()
            return false
        }
        if (password.get()!!.length != 6) {
            "安全码为6位数字".show()
            return false
        }
        if (confirmPwd.get().isNullOrEmpty()) {
            "请输入确认安全码".show()
            return false
        }
        if (password.get() != confirmPwd.get()) {
            "两次输入密码不一致".show()
            return false
        }
        return true
    }
}