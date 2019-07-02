package com.xinly.dendrobe.module.launch

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import com.tbruyelle.rxpermissions2.RxPermissions
import com.xinly.core.ext.no
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

    override fun initData() {
        //权限请求
        requestPermissions()
    }

    @SuppressLint("CheckResult")
    private fun requestPermissions() {
        RxPermissions(this)
            .request(Manifest.permission.READ_PHONE_STATE)
            .subscribe {
                it.no {
                    finish()
                }
            }
    }
}
