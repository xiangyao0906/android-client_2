package com.xinly.dendrobe.module.login

import android.os.Bundle
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.MobileLoginBinding

/**
 * 手机号登陆
 */
class MobileLoginActivity : BaseActivity<MobileLoginBinding, MobileLoginViewModel>() {

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_mobile_login
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

}
