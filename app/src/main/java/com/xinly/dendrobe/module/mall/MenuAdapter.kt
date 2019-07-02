package com.xinly.dendrobe.module.mall

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xinly.core.ext.loadUrl
import com.xinly.dendrobe.R
import com.xinly.dendrobe.base.BaseRecyclerViewAdapter
import com.xinly.dendrobe.model.vo.result.ConfigIndexData
import kotlinx.android.synthetic.main.item_mall_menu.view.*

/**
 * 菜单数据
 * <p>
 * Created by zm on 2019-07-02.
 */
class MenuAdapter(context: Context): BaseRecyclerViewAdapter<ConfigIndexData.MenuBean, MenuAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.item_mall_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //菜单图标
        holder.itemView.menuIcon.loadUrl(model.icon)
        //菜单名称
        holder.itemView.menuName.text = model.name
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}