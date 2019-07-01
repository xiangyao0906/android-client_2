package com.xinly.dendrobe.module.user

import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.databinding.Observable
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.LoginBinding

/**
 * 手机号登陆
 */
class LoginActivity : BaseActivity<LoginBinding, LoginViewModel>() {

    companion object{
        const val EXTRAS_REGISTER_TYPE = "registerType"
        // 手机号注册
        const val TYPE_MOBILE = 0
        // 邮箱注册
        const val TYPE_EMAIL = 1
    }

    // 登陆类型，默认手机登录
    var loginType: Int = TYPE_MOBILE

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initParam() {
        loginType = intent?.extras?.getInt(EXTRAS_REGISTER_TYPE,TYPE_MOBILE )?:TYPE_MOBILE
    }

    override fun initData() {
        viewModel?.loginType?.set(loginType)
    }

    override fun initViewObservable() {
        //监听ViewModel中pSwitchObservable的变化时回调该方法
        viewModel?.uic?.pSwitchObservable?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                viewModel?.uic?.pSwitchObservable?.get()?.let {
                    if (it){
                        //密码可见
                        binding.ivSwitchPwd.setImageResource(R.drawable.login_pwd_visible)
                        binding.etPwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    }else{
                        //密码不可见
                        binding.ivSwitchPwd.setImageResource(R.drawable.login_pwd_invisible)
                        binding.etPwd.transformationMethod = PasswordTransformationMethod.getInstance()
                    }
                }
            }

        })
        //登陆类型监听
        viewModel?.loginType?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                // 修改账户输入类型
                viewModel?.loginType?.let {
                    binding.etAccount.inputType = if (it.get() == TYPE_MOBILE) InputType.TYPE_CLASS_PHONE else InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }
                // 清除输入数据
                binding.etAccount.setText("")
                binding.etPwd.setText("")
            }

        })
    }

}
