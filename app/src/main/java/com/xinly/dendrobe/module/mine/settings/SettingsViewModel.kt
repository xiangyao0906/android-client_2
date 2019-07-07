package com.xinly.dendrobe.module.mine.settings

import android.app.Application
import com.xinly.dendrobe.base.BaseToolBarViewModel

/**
 * Created by zm on 2019-07-05.
 */
class SettingsViewModel(application: Application): BaseToolBarViewModel(application) {

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    //normal fun
    private fun initData() {
        toolBarData.titleText = "账户设置"
    }
}