package com.xinly.dendrobe.module.user

import android.os.Bundle
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.LoginRegisterBinding

/**
 * 登陆入口
 */
class LoginRegisterActivity : BaseActivity<LoginRegisterBinding, LoginRegisterViewModel>() {

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login_register
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

}
