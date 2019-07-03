package com.xinly.dendrobe.module.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.recyclerview.widget.GridLayoutManager
import com.xinly.core.ext.showAtCenter
import com.xinly.core.ui.fragment.BaseFragment
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.base.BaseRecyclerViewAdapter
import com.xinly.dendrobe.databinding.MineBinding
import com.xinly.dendrobe.helper.AccountManager
import com.xinly.dendrobe.model.constans.Constans
import com.xinly.dendrobe.model.vo.bean.MenuBean
import com.xinly.dendrobe.util.PrefsUtils

/**
 * 我的
 * <p>
 * Create by zm on 2019/7/2
 */
class MineFragment : BaseFragment<MineBinding, MineViewModel>() {

    private val menus by lazy {
        arrayListOf(MenuBean(0, "我的推广", R.drawable.mine_popularize)
            , MenuBean(1, "消息", R.drawable.mine_message)
            , MenuBean(2, "C2C交易", R.drawable.mine_trader)
            , MenuBean(3, "银行卡", R.drawable.mine_bank_card)
            , MenuBean(4, "实名认证", R.drawable.mine_verified)
            , MenuBean(5, " 帮助中心", R.drawable.mine_helper))
    }
    private lateinit var mAdapter: MenuAdapter

    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_mine
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        // 初始化RecyclerView
        mAdapter = MenuAdapter(context!!)
        mAdapter.mItemClickListener = object : BaseRecyclerViewAdapter.OnItemClickListener<MenuBean>{
            override fun onItemClick(item: MenuBean, position: Int) {
                "该功能暂未开放".showAtCenter()
            }
        }
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter = mAdapter
        mAdapter.setData(menus)
    }

}
