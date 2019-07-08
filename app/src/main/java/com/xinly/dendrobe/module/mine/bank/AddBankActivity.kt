package com.xinly.dendrobe.module.mine.bank

import android.os.Bundle
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.AddBankBinding

/**
 * 添加银行卡
 * <p>
 * Created by zm on 2019-07-08.
 */
class AddBankActivity : BaseActivity<AddBankBinding, AddBankViewModel>() {
    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_add_bank
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }


}
