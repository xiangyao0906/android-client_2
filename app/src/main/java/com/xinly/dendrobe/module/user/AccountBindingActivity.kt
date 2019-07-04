package com.xinly.dendrobe.module.user

import android.os.Bundle
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.AccountBinding

/**
 * 邮箱或手机号绑定
 * <p>
 * Create by zm on 2019/7/4
 */
class AccountBindingActivity : BaseActivity<AccountBinding,AccountBindingViewModel>() {

    companion object{
        const val EXTRAS_BINDING_TYPE = "type"
        // 手机号绑定
        const val TYPE_MOBILE = 0
        // 邮箱绑定
        const val TYPE_EMAIL = 1
    }

    // 登陆类型，默认手机登录
    var type: Int = TYPE_MOBILE

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_binding_account
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initParam() {
        type = intent?.extras?.getInt(EXTRAS_BINDING_TYPE, TYPE_MOBILE)?: TYPE_MOBILE
    }

    override fun initData() {
        viewModel?.type?.set(type)
    }
}
