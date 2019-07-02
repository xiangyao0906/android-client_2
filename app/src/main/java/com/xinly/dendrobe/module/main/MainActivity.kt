package com.xinly.dendrobe.module.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.MainBinding
import com.xinly.dendrobe.module.financing.FinancingFragment
import com.xinly.dendrobe.module.mall.MallFragment
import com.xinly.dendrobe.module.mine.MineFragment
import com.xinly.dendrobe.module.trader.TraderFragment
import java.util.*

class MainActivity : BaseActivity<MainBinding, MainViewModel>() {

    //Fragment 栈管理
    private val mStack = Stack<Fragment>()
    //商城Fragment
    private val mMallFragment by lazy { MallFragment() }
    //交易Fragment
    private val mTraderFragment by lazy { TraderFragment() }
    //理财Fragment
    private val mFinancingFragment by lazy { FinancingFragment() }
    //我的Fragment
    private val mMineFragment by lazy { MineFragment() }

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        initFragment()
        initBottomNav()
        changeFragment(0)
    }

    /*
     * 初始化Fragment栈管理
     */
    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.fl_content, mMallFragment)
        manager.add(R.id.fl_content, mTraderFragment)
        manager.add(R.id.fl_content, mFinancingFragment)
        manager.add(R.id.fl_content, mMineFragment)
        manager.commit()

        mStack.add(mMallFragment)
        mStack.add(mTraderFragment)
        mStack.add(mFinancingFragment)
        mStack.add(mMineFragment)
    }

    /**
     * 初始化底部导航切换事件
     */
    private fun initBottomNav() {
        binding.bottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })
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
