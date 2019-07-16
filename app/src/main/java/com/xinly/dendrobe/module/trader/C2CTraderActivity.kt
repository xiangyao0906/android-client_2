package com.xinly.dendrobe.module.trader

import android.os.Bundle
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.C2CTraderBinding

/**
 * C2C交易
 * <p>
 * Create by zm on 2019/7/8.
 */
class C2CTraderActivity : BaseActivity<C2CTraderBinding, C2CTraderViewModel>() {

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_c2c_trader
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }


}
