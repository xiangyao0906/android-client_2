package com.xinly.dendrobe.module.user

import android.os.Bundle
import com.jungly.gridpasswordview.GridPasswordView
import com.xinly.core.ext.no
import com.xinly.core.ext.showAtCenter
import com.xinly.core.ext.yes
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.SetSafPwdBinding

/**
 * 设置安全密码
 * <p>
 * Create by zm on 2019/7/4.
 */
class SetSecurityCodeActivity : BaseActivity<SetSafPwdBinding, SetSecurityCodeViewModel>() {

    companion object{
        const val EXTRAS_CODE: String = "code"
    }

    // 是否第一次输入
    private var isFirst = true
    // 第一次输入的密码
    private var firstPassword: String? = null
    // 验证码
    private var code: String = ""

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_set_security_code
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initParam() {
        intent?.extras?.let {
            code = it.getString(EXTRAS_CODE, "")
        }
        if (code.isEmpty()) {
            finish()
            return
        }
    }

    override fun initData() {
        super.initData()
        binding.pswView.setOnPasswordChangedListener(object : GridPasswordView.OnPasswordChangedListener{
            override fun onInputFinish(psw: String?) {
                psw?.let {
                    isFirst.yes {
                        firstPassword = psw
                        isFirst = false
                        binding.pswView.clearPassword()
                        binding.inputPwdHint.text = "请确认输入6位安全密码"
                        return@let
                    }
                    isFirst.no {
                        if (firstPassword == psw) {
                            viewModel?.changeSecurity(code, psw)
                        }else{
                            isFirst = true
                            binding.pswView.clearPassword()
                            "两次输入不一致，请重新输入".showAtCenter()
                            binding.inputPwdHint.text = "请输入6位安全密码"
                        }
                    }

                }
            }

            override fun onTextChanged(psw: String?) {

            }

        })
    }

}
