package com.xinly.dendrobe.module.mine.bank

import android.os.Bundle
import androidx.databinding.Observable
import androidx.recyclerview.widget.LinearLayoutManager
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.base.BaseRecyclerViewAdapter
import com.xinly.dendrobe.databinding.BankBinding
import com.xinly.dendrobe.model.constans.Constans
import com.xinly.dendrobe.model.vo.bean.BankBean
import com.xinly.dendrobe.util.PrefsUtils
import com.xinly.dendrobe.widget.SpaceItemDecoration
import com.xinly.dendrobe.widget.bottomview.BankCardBottomView

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
                showBottomDialog(item, position)
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
                        mAdapter.setData(convertList(it))
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

    private fun convertList(dataList: List<BankBean>): MutableList<BankBean> {
        val newList: MutableList<BankBean> = dataList as MutableList<BankBean>
        if (newList.isEmpty()) return newList
        // 获取默认银行卡id
        val defId = PrefsUtils.getInt(Constans.SP_USER_DEF_BANK_CARD)
        // 默认银行卡标志
        val defFlag = newList.any { defId == it.id }
        return newList.apply {
            if (!defFlag) {
                first().flag = 1
                PrefsUtils.putInt(Constans.SP_USER_DEF_BANK_CARD, first().id)
            }else{
                first { defId == it.id }.flag = 1
            }
            sortByDescending { it.flag }
        }
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun showBottomDialog(item: BankBean, position: Int) {
        object : BankCardBottomView(this, item){
            override fun deleteBankCard() {
                mAdapter.dataList.removeAt(position)
                mAdapter.setData(convertList(mAdapter.dataList), false)
                mAdapter.notifyItemRemoved(position)
                mAdapter.notifyItemChanged(0)
            }

            override fun setDefaultBankCard() {
                PrefsUtils.putInt(Constans.SP_USER_DEF_BANK_CARD, item.id)
                val newList: List<BankBean> = mAdapter.dataList.map {
                    it.apply {
                        flag = if (id == item.id) 1 else 0
                    }
                }.sortedByDescending { it.flag }
                mAdapter.setData(newList as MutableList<BankBean>)
            }

        }.showBottomView(true)
    }
}
