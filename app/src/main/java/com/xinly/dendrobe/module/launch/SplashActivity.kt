package com.xinly.dendrobe.module.launch

import android.os.Bundle
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.SplashBinding

/**
 * 闪屏页
 * <p>
 * Create by zm on 2019/7/2.
 */
class SplashActivity : BaseActivity<SplashBinding,SplashViewModel>() {

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

}
