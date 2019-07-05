package com.xinly.dendrobe.module.mine.wallet.recharge

import android.app.Application
import androidx.databinding.ObservableField
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.ext.showAtCenter
import com.xinly.core.ext.yes
import com.xinly.dendrobe.base.BaseToolBarViewModel
import com.xinly.dendrobe.util.BigDecimalUtils

/**
 * Created by zm on 2019-07-05.
 */
class CloudRechargeViewModel(application: Application): BaseToolBarViewModel(application) {
    // 云仓天天拼余额
    val cloudNum by lazy { ObservableField<String>() }
    // 输入青豆数量
    val beanNum by lazy { ObservableField<String>() }

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    //event
    //充值
    val rechargeClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            checkParams().yes {
                "该功能后台暂未实现".showAtCenter()
            }
        }

    })
    //充值
    val allClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            beanNum.set(cloudNum.get())
        }

    })

    //normal fun
    private fun initData() {
        toolBarData.titleText = "云仓转入青豆"
        cloudNum.set("100.00")
    }
    //效验参数合法性
    private fun checkParams(): Boolean {
        if(beanNum.get().isNullOrEmpty()) {
            "请输入要转入的青豆数量".showAtCenter()
            return false
        }
        if (BigDecimalUtils.compare(beanNum.get()!!.toDouble(), cloudNum.get()!!.toDouble())>0){
            "转入数量超过最大值".showAtCenter()
            return false
        }
        return true
    }
}