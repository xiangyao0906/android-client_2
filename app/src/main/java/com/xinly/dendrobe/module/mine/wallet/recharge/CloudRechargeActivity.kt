package com.xinly.dendrobe.module.mine.wallet.recharge

import android.os.Bundle
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.CloudRechargeBinding

/**
 * 云仓转入
 * <p>
 * Create by zm on 2019/7/5.
 */
class CloudRechargeActivity : BaseActivity<CloudRechargeBinding, CloudRechargeViewModel>() {
    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_cloud_recharge
    }

    override fun initVariableId(): Int {
        return  BR.viewModel
    }


}
