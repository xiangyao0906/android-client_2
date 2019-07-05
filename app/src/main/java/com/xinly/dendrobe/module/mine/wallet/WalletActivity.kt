package com.xinly.dendrobe.module.mine.wallet

import android.os.Bundle
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.WalletBinding

/**
 * 钱包
 * <p>
 * Created by zm on 2019-07-05.
 */
class WalletActivity : BaseActivity<WalletBinding, WalletViewModel>() {
    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_wallet
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

}
