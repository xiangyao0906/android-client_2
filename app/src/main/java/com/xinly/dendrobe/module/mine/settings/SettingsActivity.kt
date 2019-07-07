package com.xinly.dendrobe.module.mine.settings

import android.os.Bundle
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.SettingsBinding

/**
 * 账户设置
 * <p>
 * Create by zm on 2019/7/5.
 */
class SettingsActivity : BaseActivity<SettingsBinding,SettingsViewModel>() {

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_settings
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

}
