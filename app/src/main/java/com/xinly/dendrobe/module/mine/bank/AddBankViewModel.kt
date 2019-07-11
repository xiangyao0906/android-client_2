package com.xinly.dendrobe.module.mine.bank

import android.app.Application
import androidx.databinding.ObservableField
import com.hwangjr.rxbus.RxBus
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.showAtCenter
import com.xinly.core.ext.yes
import com.xinly.dendrobe.api.BankApi
import com.xinly.dendrobe.base.BaseToolBarViewModel
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.model.constans.BusAction
import com.xinly.dendrobe.model.vo.bean.Event

/**
 * Created by zm on 2019-07-08.
 */
class AddBankViewModel(application: Application): BaseToolBarViewModel(application) {

    //银行卡
    val bankCard by lazy { ObservableField<String>() }
    //银行名称
    val bankName by lazy { ObservableField<String>() }
    //开户行
    val targetBank by lazy { ObservableField<String>() }
    //姓名
    val name by lazy { ObservableField<String>() }
    //手机
    val mobile by lazy { ObservableField<String>() }

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    //normal
    private fun initData() {
        toolBarData.titleText = "添加银行卡"
    }
    //效验参数
    private fun checkParams(): Boolean {
        if (bankCard.get().isNullOrEmpty()) {
            "请输入银行卡号".showAtCenter()
            return false
        }
        if (bankCard.get()!!.length < 16 || bankCard.get()!!.length>19) {
            "请输入正确的银行卡号".showAtCenter()
            return false
        }
        if (bankName.get().isNullOrEmpty()) {
            "请输入银行名称".showAtCenter()
            return false
        }
        if (targetBank.get().isNullOrEmpty()) {
            "请输入开户行".showAtCenter()
            return false
        }
        if (name.get().isNullOrEmpty()) {
            "请输入开卡人姓名".showAtCenter()
            return false
        }
        if (mobile.get().isNullOrEmpty()) {
            "请输入手机号码".showAtCenter()
            return false
        }
        return true
    }

    //event
    //保存银行卡
    val saveClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction{
        override fun call() {
            checkParams().yes {
                BankApi().add(bankCard.get()!!, bankName.get()!!, targetBank.get()!!, name.get()!!, mobile.get()!!,
                    object : XinlyRxSubscriberHelper<BaseResp<Nothing>>(){
                        override fun _onNext(t: BaseResp<Nothing>) {
                            RxBus.get().post(BusAction.BANK_ADD_SUCCESS, Event.MessageEvent())
                            finish()
                        }

                    }, lifecycleProvider)
            }
        }

    })
}