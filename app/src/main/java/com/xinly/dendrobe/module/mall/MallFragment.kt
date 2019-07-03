package com.xinly.dendrobe.module.mall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.xinly.core.ext.showAtCenter
import com.xinly.core.ui.fragment.BaseFragment
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.api.SystemApi
import com.xinly.dendrobe.base.BaseRecyclerViewAdapter
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.databinding.MallBinding
import com.xinly.dendrobe.helper.AccountManager
import com.xinly.dendrobe.model.vo.result.ConfigIndexData
import com.xinly.dendrobe.widget.BannerImageLoader
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer

/**
 * 商城
 * <p>
 * Create by zm on 2019/7/2
 */
class MallFragment : BaseFragment<MallBinding, MallViewModel>() {
    private var mData: ConfigIndexData? = null
    private lateinit var mAdapter: MenuAdapter

    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_mall
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        // 初始化banner
        binding.banner.apply {
            setImageLoader(BannerImageLoader())
            setBannerAnimation(Transformer.Default)
            setDelayTime(3000)
            setIndicatorGravity(BannerConfig.RIGHT)
        }
        // 初始化RecyclerView
        mAdapter = MenuAdapter(context!!)
        mAdapter.mItemClickListener = object : BaseRecyclerViewAdapter.OnItemClickListener<ConfigIndexData.MenuBean>{
            override fun onItemClick(item: ConfigIndexData.MenuBean, position: Int) {
                if (!item.isStatus){
                    "该功能暂未开放".showAtCenter()
                    return
                }
            }
        }
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter = mAdapter
        // 加载数据
        loadData()
    }

    /**
     * 加载数据
     */
    private fun loadData() {
        SystemApi().configIndex(object : XinlyRxSubscriberHelper<ConfigIndexData>(){
            override fun _onNext(t: ConfigIndexData) {
                mData = t
                showData()
            }

        }, this)
    }

    private fun showData() {
        mData?.apply {
            // notice
            val newNotices = notice.map { it.title }
            binding.mNoticeFlipperView.setData(newNotices)
            // banner
            val newImgUrls: MutableList<String> = slide.map { it.image } as MutableList<String>
            binding.banner.setImages(newImgUrls).start()
            // recyclerView
            mAdapter.setData(menu)
            // update User Data
            AccountManager.instance.updateAccount(member)
        }
    }
}
