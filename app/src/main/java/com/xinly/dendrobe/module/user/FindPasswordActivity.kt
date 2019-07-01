package com.xinly.dendrobe.module.user

import android.os.Bundle
import android.text.InputType
import androidx.databinding.Observable
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
        viewModel?.uic?.findType?.set(findType)
    }

    override fun initViewObservable() {
        //找回面膜类型监听
        viewModel?.uic?.findType?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                // 修改账户输入类型
                viewModel?.uic?.findType?.let {
                    binding.etAccount.inputType = if (it.get() == 0) InputType.TYPE_CLASS_PHONE else InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }
                // 清除输入数据
                binding.etAccount.setText("")
                binding.etImgCode.setText("")
                binding.etVerfCode.setText("")
            }

        })
    }
}
