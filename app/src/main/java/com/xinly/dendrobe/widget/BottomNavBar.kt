package com.xinly.dendrobe.widget

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.xinly.dendrobe.R

/*
 * 底部导航
 * <p>
 * Create by zm on 2019/7/2.
 */
class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    init {
        //首页
        val homeItem = BottomNavigationItem(R.drawable.tab_nav_home_pre, "首页")
                .setInactiveIconResource(R.drawable.tab_nav_home_nor)
                .setActiveColorResource(R.color.white)
                .setInActiveColorResource(R.color.font_gray)

        //交易
        val businessItem = BottomNavigationItem(R.drawable.tab_nav_trader_pre, "交易")
                .setInactiveIconResource(R.drawable.tab_nav_trader_nor)
                .setActiveColorResource(R.color.white)
                .setInActiveColorResource(R.color.font_gray)

        //理财
        val financingItem = BottomNavigationItem(R.drawable.tab_nav_financing_pre, "理财")
                .setInactiveIconResource(R.drawable.tab_nav_financing_nor)
                .setActiveColorResource(R.color.white)
                .setInActiveColorResource(R.color.font_gray)


        //我的
        val userItem = BottomNavigationItem(R.drawable.tab_nav_mine_pre, "我的")
                .setInactiveIconResource(R.drawable.tab_nav_mine_nor)
                .setActiveColorResource(R.color.white)
                .setInActiveColorResource(R.color.font_gray)

        //设置底部导航模式及样式
        setMode(MODE_FIXED)
        setBackgroundStyle(BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.bg_btn_black)
        //添加Tab
        addItem(homeItem)
                .addItem(businessItem)
                .addItem(financingItem)
                .addItem(userItem)
                .setFirstSelectedPosition(0)
                .initialise()
    }
}
