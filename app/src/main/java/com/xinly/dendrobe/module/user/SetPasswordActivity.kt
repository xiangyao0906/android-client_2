package com.xinly.dendrobe.module.user

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.databinding.Observable
import com.xinly.core.ext.no
import com.xinly.core.ext.yes
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.SetPwdBinding

/**
 * 设置密码
 * <p>
 *  Create by zm on 2019/7/1
 */
class SetPasswordActivity : BaseActivity<SetPwdBinding, SetPwdViewModel>() {

    // 登陆类型，默认手机登录
    var findType: Int = FindPasswordActivity.TYPE_MOBILE
    // 账号
    lateinit var account: String

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_set_password
    }

    override fun initVariableId(): Int {
       return BR.viewModel
    }

    override fun initParam() {
        intent?.extras?.let {
            findType = it.getInt(FindPasswordActivity.EXTRAS_FIND_TYPE, FindPasswordActivity.TYPE_MOBILE)
            account = it.getString(FindPasswordActivity.EXTRAS_FIND_ACCOUNT, "")
        }
        account.isEmpty().yes {
            finish()
        }
    }

    override fun initData() {
        viewModel?.account = account
        viewModel?.type = findType
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
    }
}
