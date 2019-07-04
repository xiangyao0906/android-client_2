package com.xinly.dendrobe.module.user

import android.os.Bundle
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.ChangeSefCodeBinding

/**
 * 修改安全密码
 * <p>
 * Create by zm 2019/7/1
 */
class ChangeSecurityCodeActivity : BaseActivity<ChangeSefCodeBinding, ChangeSecurityCodeViewModel>() {

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_change_security_code
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}
