package com.xinly.dendrobe.module.register

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import androidx.databinding.Observable
import com.tbruyelle.rxpermissions2.RxPermissions
import com.xinly.core.ext.no
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.RegisterBinding

/**
 * 注册页面
 */
class RegisterActivity : BaseActivity<RegisterBinding, RegisterViewModel>() {

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_register
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        //权限请求
        requestPermissions()
    }

    override fun initViewObservable() {
        //注册类型监听
        viewModel?.uic?.regType?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                // 修改账户输入类型
                viewModel?.uic?.regType?.let {
                    binding.etAccount.inputType = if (it.get() == 0) InputType.TYPE_CLASS_PHONE else InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }
                // 清除输入数据
                binding.etAccount.setText("")
                binding.etImgCode.setText("")
                binding.etVerfCode.setText("")
            }

        })
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
