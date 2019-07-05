package com.xinly.dendrobe.module.mine.wallet

import android.os.Bundle
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.WalletBinding
import java.util.*

/**
 * 钱包
 * <p>
 * Created by zm on 2019-07-05.
 */
class WalletActivity : BaseActivity<WalletBinding, WalletViewModel>() {

    //青豆记录
    private val beanFragment by lazy { RecordFragment.newInstance(0) }
    //石斛记录
    private val dendFragment by lazy { RecordFragment.newInstance(1) }

    //Fragment 栈管理
    private val mStack = Stack<Fragment>()

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_wallet
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        viewModel?.type?.set(true)
        initFragment()
        changeFragment(0)
    }

    override fun initViewObservable() {
        viewModel?.type?.apply {
            addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    changeFragment(if(get()) 0 else 1)
                }
            })
        }
    }

    private fun initFragment(){
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.fl_content, beanFragment)
        manager.add(R.id.fl_content, dendFragment)
        manager.commit()

        mStack.add(beanFragment)
        mStack.add(dendFragment)
    }

    /**
     * 切换Tab，切换对应的Fragment
     */
    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        mStack.forEach {
            manager.hide(it)
        }
        manager.show(mStack[position])
        manager.commit()
    }
}
