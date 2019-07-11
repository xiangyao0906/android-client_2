package com.xinly.dendrobe.module.mine

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.hwangjr.rxbus.thread.EventThread
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.ext.copy
import com.xinly.core.ext.showAtCenter
import com.xinly.core.ext.yes
import com.xinly.core.viewmodel.BaseViewModel
import com.xinly.dendrobe.helper.AccountManager
import com.xinly.dendrobe.model.constans.BusAction
import com.xinly.dendrobe.model.constans.Constans
import com.xinly.dendrobe.model.vo.bean.Event
import com.xinly.dendrobe.model.vo.bean.UserBean
import com.xinly.dendrobe.module.mine.settings.SettingsActivity
import com.xinly.dendrobe.module.mine.wallet.WalletActivity
import com.xinly.dendrobe.module.user.UserInfoEditActivity
import com.xinly.dendrobe.util.PrefsUtils

/**
 * Created by zm on 2019-07-02.
 */
class MineViewModel(application: Application): BaseViewModel(application) {

    //用户数据
    val userData by lazy { ObservableField<UserBean>() }
    //余额显示与隐藏
    val pSwitch by lazy { ObservableBoolean() }

    override fun onCreate() {
        super.onCreate()
        //获取用户数据
        userData.set(AccountManager.instance.getAccount())
        //密码显示隐藏
        val pwdVisible = PrefsUtils.getBoolean(Constans.SP_USER_BEAN_VISIBLE, true)
        pSwitch.set(pwdVisible)
    }

    //event
    //跳转至设置界面
    val jumpSettingsClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            startActivity(SettingsActivity::class.java)
        }

    })
    //跳转至个人资料
    val jumpUserInfoClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            startActivity(UserInfoEditActivity::class.java)
        }

    })
    //跳转至钱包界面
    val jumpWalletClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            startActivity(WalletActivity::class.java)
        }
    })
    //复制邀请码
    val copyClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            userData.get()?.code.toString().copy().yes {
                "邀请码已复制到粘贴板".showAtCenter()
            }

        }
    })
    //余额是否显示
    val passwordShowSwitchOnClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            pSwitch.apply {
                set(!get())
                PrefsUtils.putBoolean(Constans.SP_USER_BEAN_VISIBLE, get())
            }
        }
    })
    //normal fun

    /**
     * 更新用户信息
     */
    @Subscribe(thread = EventThread.MAIN_THREAD, tags = [Tag(BusAction.UPDATE_USER_INFO)])
    fun updateUserInfo(event: Event.MessageEvent) {
        userData.set(AccountManager.instance.getAccount())
    }

    /**
     * 更新余额是否隐藏
     */
    @Subscribe(thread = EventThread.MAIN_THREAD, tags = [Tag(BusAction.UPDATE_BEAN_VISIBLE)])
    fun updatePwdVisible(event: Event.UpdateBeanIsVisivle) {
        pSwitch.set(event.isVisible)
    }
}