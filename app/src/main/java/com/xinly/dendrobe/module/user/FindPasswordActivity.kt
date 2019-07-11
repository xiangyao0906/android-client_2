package com.xinly.dendrobe.module.user

import android.os.Bundle
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.FindPwdBinding

/**
 * 找回密码
 * <p>
 * Create by zm 2019/7/1
 */
class FindPasswordActivity : BaseActivity<FindPwdBinding, FindPwdViewModel>() {

    companion object{
        const val EXTRAS_FIND_TYPE = "type"
        const val EXTRAS_FIND_ACCOUNT = "account"
        // 手机号找回
        const val TYPE_MOBILE = 0
        // 邮箱找回
        const val TYPE_EMAIL = 1
    }

    // 登陆类型，默认手机登录
    var findType: Int = TYPE_MOBILE

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_find_password
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initParam() {
        findType = intent?.extras?.getInt(EXTRAS_FIND_TYPE,TYPE_MOBILE )?:TYPE_MOBILE
    }

    override fun initData() {
        viewModel?.findType?.set(findType)
    }
}
