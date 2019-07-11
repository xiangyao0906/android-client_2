package com.xinly.dendrobe.module.mine.bank

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.hwangjr.rxbus.thread.EventThread
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.ext.showAtCenter
import com.xinly.dendrobe.api.BankApi
import com.xinly.dendrobe.base.BaseToolBarViewModel
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.model.constans.BusAction
import com.xinly.dendrobe.model.vo.bean.BankBean
import com.xinly.dendrobe.model.vo.bean.Event
import com.xinly.dendrobe.model.vo.result.BankListData

/**
 * Created by zm on 2019-07-08.
 */
class BankViewModel(application: Application): BaseToolBarViewModel(application) {
    //是否已添加银行卡
    val bankIsEmpty by lazy { ObservableBoolean() }
    // 银行卡列表数据
    val bankList by lazy { ObservableField<ArrayList<BankBean>>() }

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    //net
    //获取银行卡列表
    fun bankList() {
        BankApi().get(object : XinlyRxSubscriberHelper<BankListData>(){
            override fun _onNext(t: BankListData) {
                bankList.set(t.list)
            }

        }, lifecycleProvider)
    }

    //normal
    private fun initData() {
        toolBarData.titleText = "银行卡"
        bankIsEmpty.set(true)
        toolBarData.rightText = if(bankIsEmpty.get()) "" else "添加"
    }

    //添加银行卡事件
    override fun handRightText() {
        startActivity(AddBankActivity::class.java)
    }
    //添加银行卡事件
    val addBankClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            startActivity(AddBankActivity::class.java)
        }

    })

    @Subscribe(thread = EventThread.MAIN_THREAD, tags = [Tag(BusAction.BANK_ADD_SUCCESS)])
    fun addBankSuccess(event: Event.MessageEvent){
        bankList()
    }
}