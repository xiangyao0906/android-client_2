package com.xinly.dendrobe.module.mine

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.ext.copy
import com.xinly.core.ext.showAtCenter
import com.xinly.core.ext.yes
import com.xinly.core.viewmodel.BaseViewModel
import com.xinly.dendrobe.R
import com.xinly.dendrobe.helper.AccountManager
import com.xinly.dendrobe.model.constans.Constans
import com.xinly.dendrobe.model.vo.bean.UserBean
import com.xinly.dendrobe.util.PrefsUtils

/**
 * Created by zm on 2019-07-02.
 */
class MineViewModel(application: Application): BaseViewModel(application) {

    //用户数据
    val userData by lazy { ObservableField<UserBean>() }
    //余额显示与隐藏
    val pSwitch by lazy { ObservableBoolean() }
    val visibleIcon by lazy {ObservableField<Int>()}
    val balance by lazy {ObservableField<String>()}

    override fun onCreate() {
        super.onCreate()
        //获取用户数据
        userData.set(AccountManager.instance.getAccount())
        //密码显示隐藏
        val pwdVisible = PrefsUtils.getBoolean(Constans.SP_MINE_PASSWORD_VISIBLE)
        pSwitch.set(pwdVisible)
        pwdVisible()
    }

    //event
    //跳转至设置界面
    val jumpSettingsClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {

        }
    })
    //跳转至钱包界面
    val jumpWalletClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {

        }
    })
    //复制邀请码
    val copyClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            userData.get()?.code?.copy()?.yes {
                "邀请码已复制到粘贴板".showAtCenter()
            }

        }
    })
    //查看账单
    val viewBill: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {

        }
    })
    //余额是否显示
    val passwordShowSwitchOnClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            pSwitch.apply {
                set(!get())
                pwdVisible()
                PrefsUtils.putBoolean(Constans.SP_MINE_PASSWORD_VISIBLE, get())
            }
        }
    })
    //normal fun
    //余额显示隐藏切换
    private fun pwdVisible() {
        visibleIcon.set(if(pSwitch.get()) R.drawable.mine_balance_visible else R.drawable.mine_balance_invisible)
        balance.set(if (pSwitch.get()) userData.get()!!.bean else "******")
    }
}