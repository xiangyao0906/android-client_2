package com.xinly.dendrobe.module.financing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.xinly.core.ui.fragment.BaseFragment
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.FinancingBinding

/**
 * 理财
 * <p>
 * Create by zm on 2019/7/2
 */
class FinancingFragment : BaseFragment<FinancingBinding, FinancingViewModel>() {

    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_financing
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

}
