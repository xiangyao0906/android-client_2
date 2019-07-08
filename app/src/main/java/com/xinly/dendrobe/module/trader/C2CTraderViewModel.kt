package com.xinly.dendrobe.module.trader

import android.app.Application
import com.xinly.dendrobe.base.BaseToolBarViewModel

/**
 * Created by zm on 2019-07-08.
 */
class C2CTraderViewModel(application: Application): BaseToolBarViewModel(application) {

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    //normal fun
    private fun initData() {
        toolBarData.titleText = "C2C交易"
        toolBarData.rightText = "帮助"
    }
}