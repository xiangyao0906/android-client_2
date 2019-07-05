package com.xinly.dendrobe.module.user

import android.app.Application
import android.os.Bundle
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.hwangjr.rxbus.thread.EventThread
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.ext.showAtCenter
import com.xinly.dendrobe.api.FileApi
import com.xinly.dendrobe.api.UserApi
import com.xinly.dendrobe.base.BaseToolBarViewModel
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.helper.AccountManager
import com.xinly.dendrobe.model.constans.BusAction
import com.xinly.dendrobe.model.vo.bean.Event
import com.xinly.dendrobe.model.vo.bean.UserBean
import com.xinly.dendrobe.model.vo.result.ChangeUserData
import com.xinly.dendrobe.model.vo.result.UploadImageData

/**
 * Created by zm on 2019-07-03.
 */
class UserInfoEditViewModel(application: Application): BaseToolBarViewModel(application) {
    //用户数据
    val userData by lazy { ObservableField<UserBean>() }
    // 选择头像
    val requestSelPicture by lazy { ObservableBoolean(false) }

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    //event
    //选择头像
    val sltPhotoClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            requestSelPicture.set(!requestSelPicture.get())
        }

    })
    //绑定手机号码
    val updateNicknameClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            val bundle = Bundle()
            bundle.putInt(AccountBindingActivity.EXTRAS_BINDING_TYPE, AccountBindingActivity.TYPE_MOBILE)
            startActivity(AccountBindingActivity::class.java, bundle)
        }

    })
    //绑定安全邮箱
    val updateEmailClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            val bundle = Bundle()
            bundle.putInt(AccountBindingActivity.EXTRAS_BINDING_TYPE, AccountBindingActivity.TYPE_EMAIL)
            startActivity(AccountBindingActivity::class.java, bundle)
        }

    })
    //修改密码
    val updatePwdClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            startActivity(ChangeSecurityCodeActivity::class.java)
        }

    })
    //我的邀请人
    val updateInviterClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            startActivity(InviterBindingActivity::class.java)
        }

    })


    //normal fun
    //初始化数据
    private fun initData() {
        toolBarData.titleText = "个人资料"
        userData.set(AccountManager.instance.getAccount())
    }
    //上传文件
    fun uploadFile(filePath: String) {
        FileApi().uploadFile(filePath,"avatar", object : XinlyRxSubscriberHelper<UploadImageData>() {
            override fun _onNext(t: UploadImageData) {
                changeAvatar(t.url)
            }

        }, lifecycleProvider)
    }
    //更新头像
    private fun changeAvatar(avatar: String) {
        UserApi().changeAvatar(avatar, object :XinlyRxSubscriberHelper<ChangeUserData>(){
            override fun _onNext(t: ChangeUserData) {
                AccountManager.instance.updateAccount(t.member)
                RxBus.get().post(BusAction.UPDATE_USER_INFO, Event.MessageEvent())
                "头像更新成功".showAtCenter()
            }

        }, lifecycleProvider)
    }

    /**
     * 更新用户信息
     */
    @Subscribe(thread = EventThread.MAIN_THREAD, tags = [Tag(BusAction.UPDATE_USER_INFO)])
    fun updateUserInfo(event: Event.MessageEvent) {
        userData.set(AccountManager.instance.getAccount())
    }

}