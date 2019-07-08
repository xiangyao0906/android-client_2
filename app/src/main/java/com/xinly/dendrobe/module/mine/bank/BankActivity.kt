package com.xinly.dendrobe.module.mine.bank

import android.os.Bundle
import androidx.databinding.Observable
import androidx.recyclerview.widget.LinearLayoutManager
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.base.BaseRecyclerViewAdapter
import com.xinly.dendrobe.databinding.BankBinding
import com.xinly.dendrobe.model.vo.bean.BankBean
import com.xinly.dendrobe.widget.SpaceItemDecoration

/**
 * 银行卡
 * <p>
 * Created by zm on 2019-07-08.
 */
class BankActivity : BaseActivity<BankBinding, BankViewModel>() {

    private lateinit var mAdapter: BankCardAdapter

    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_bank
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        mAdapter = BankCardAdapter(this)
        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<BankBean>{
            override fun onItemClick(item: BankBean, position: Int) {
                showBottomDialog(item.id)
            }

        })
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(SpaceItemDecoration(30, 30))
        binding.recyclerView.adapter = mAdapter
        binding.refreshLayout.setOnRefreshListener {
            viewModel?.bankList()
        }
        viewModel?.bankList()
    }

    override fun initViewObservable() {
        viewModel?.apply {
            bankList.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    binding.refreshLayout.isRefreshing = false
                    bankList.get()?.let {
                        bankIsEmpty.set(it.size==0)
                        mAdapter.setData(it)
                    }
                }

            })
            bankIsEmpty.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    toolBarData.rightText = if(bankIsEmpty.get()) "" else "添加"
                }

            })
        }
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun showBottomDialog(bankCardId: Int) {
    }
}
