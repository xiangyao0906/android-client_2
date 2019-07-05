package com.xinly.dendrobe.module.mine.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.xinly.core.net.exception.ApiException
import com.xinly.core.ui.fragment.BaseFragment
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.api.UserApi
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.databinding.RecordBinding
import com.xinly.dendrobe.model.vo.bean.WalletRecordBean
import com.xinly.dendrobe.model.vo.result.WalletRecordsData
import com.xinly.dendrobe.util.RefreshLayoutUtil

/**
 * 货币记录 （青豆记录、石斛记录）
 * <p>
 * Created by zm on 2019-07-05.
 */
class RecordFragment: BaseFragment<RecordBinding, RecordViewModel>(), OnLoadMoreListener,OnRefreshListener {

    companion object{
        const val EXTRAS_TYPE = "type"

        /**
         * @param type 0青豆 1石斛
         * @return
         */
        fun newInstance(type: Int): Fragment {
            val fragment = RecordFragment()
            val bundle = Bundle()
            bundle.putInt(EXTRAS_TYPE, type)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var type: Int = 0 // 0青豆 1石斛
    private lateinit var mAdapter: RecordAdapter
    private var pageIndex = 0
    private var pageSize = 10

    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_wallet_record
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initParam() {
        type = arguments?.getInt(EXTRAS_TYPE)?:0
    }

    override fun initData() {
        super.initData()
        mAdapter = RecordAdapter(context!!)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = mAdapter
        binding.refreshLayout.setOnLoadMoreListener(this)
        binding.refreshLayout.setOnRefreshListener(this)
        loadData()
    }

    /**
     * 加载数据
     */
    private fun loadData() {
        if (type == 0) { //获取青豆记录
            UserApi().getHaricotVertRecords(pageIndex, pageSize, object : XinlyRxSubscriberHelper<WalletRecordsData>(){
                override fun _onNext(t: WalletRecordsData) {
                    RefreshLayoutUtil.finishRefreshLayout(binding.refreshLayout)
                    showData(t.list)
                }

                override fun _onError(apiException: ApiException) {
                    super._onError(apiException)
                    RefreshLayoutUtil.finishRefreshLayout(binding.refreshLayout)
                }
            }, this)
        }else{ //获取石斛记录
            UserApi().getDendRecords(pageIndex, pageSize, object : XinlyRxSubscriberHelper<WalletRecordsData>(){
                override fun _onNext(t: WalletRecordsData) {
                    RefreshLayoutUtil.finishRefreshLayout(binding.refreshLayout)
                    showData(t.list)
                }

                override fun _onError(apiException: ApiException) {
                    super._onError(apiException)
                    RefreshLayoutUtil.finishRefreshLayout(binding.refreshLayout)
                }
            }, this)
        }
    }

    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        pageIndex++
        loadData()
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        pageIndex = 0
        loadData()
    }

    private fun showData(data: ArrayList<WalletRecordBean>) {
        if (pageIndex == 0) {
            mAdapter.setData(data)
        }else{
            mAdapter.addData(data)
        }
        binding.refreshLayout.setNoMoreData(data.size < pageSize)
    }
}