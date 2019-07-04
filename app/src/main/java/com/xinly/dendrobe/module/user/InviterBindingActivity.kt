package com.xinly.dendrobe.module.user

import android.os.Bundle
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.InviterBinding

/**
 * 绑定邀请人
 * <p>
 * Create by zm on 2019/7/4.
 */
class InviterBindingActivity : BaseActivity<InviterBinding, InviterBindingViewModel>() {

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_inviter_binding
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }


}
