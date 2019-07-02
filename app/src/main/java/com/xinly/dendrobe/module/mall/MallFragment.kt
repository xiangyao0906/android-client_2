package com.xinly.dendrobe.module.mall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.xinly.core.ui.fragment.BaseFragment
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.MallBinding

/**
 * 商城
 * <p>
 * Create by zm on 2019/7/2
 */
class MallFragment : BaseFragment<MallBinding, MallViewModel>() {

    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_mall
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

}
