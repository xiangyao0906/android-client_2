package com.xinly.dendrobe.module.mine.wallet

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.ext.no
import com.xinly.core.ext.yes
import com.xinly.dendrobe.base.BaseToolBarViewModel
import com.xinly.dendrobe.helper.AccountManager
import com.xinly.dendrobe.model.constans.Constans
import com.xinly.dendrobe.model.vo.bean.UserBean
import com.xinly.dendrobe.module.mine.wallet.recharge.CloudRechargeActivity
import com.xinly.dendrobe.util.PrefsUtils

/**
 * Created by zm on 2019-07-05.
 */
class WalletViewModel(application: Application): BaseToolBarViewModel(application) {
    // 是否青豆 true青豆 false石斛
    val type by lazy { ObservableBoolean() }
    val userData by lazy { ObservableField<UserBean>() }
    //余额显示与隐藏
    val pSwitch by lazy { ObservableBoolean() }

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    //event
    //切换青豆按钮事件
    val beanClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            type.get().no {
                type.set(true)
                val pwdVisible = PrefsUtils.getBoolean(Constans.SP_USER_BEAN_VISIBLE, true)
                pSwitch.set(pwdVisible)
            }
        }

    })
    //切换石斛按钮事件
    val dendClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            type.get().yes {
                type.set(false)
                val pwdVisible = PrefsUtils.getBoolean(Constans.SP_USER_DEND_VISIBLE, true)
                pSwitch.set(pwdVisible)
            }
        }

    })
    //充值事件
    val rechargeClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {

        }

    })
    //云仓转入
    val cloudRechargeClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            startActivity(CloudRechargeActivity::class.java)
        }

    })
    //提现事件
    val withdrawClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {

        }

    })
    //密码显示隐藏切换
    val passwordShowSwitchOnClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            pSwitch.apply {
                type.get().yes { //青豆
                    set(!get())
                    PrefsUtils.putBoolean(Constans.SP_USER_BEAN_VISIBLE, get())
                }
               type.get().no { //石斛
                   set(!get())
                   PrefsUtils.putBoolean(Constans.SP_USER_DEND_VISIBLE, get())
               }
            }
        }

    })

    //normal fun
    private fun initData() {
        toolBarData.titleText = "钱包"
        userData.set(AccountManager.instance.getAccount())
        //密码显示隐藏
        val pwdVisible = PrefsUtils.getBoolean(Constans.SP_USER_BEAN_VISIBLE, true)
        pSwitch.set(pwdVisible)
    }
}