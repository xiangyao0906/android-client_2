package com.xinly.dendrobe.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

/*
 * RecyclerViewAdapter基类
 * <p>
 * Created by zm on 2019-07-02.
 */
abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(var mContext: Context) : RecyclerView.Adapter<VH>() {

    //ItemClick事件
    var mItemClickListener: OnItemClickListener<T>? = null

    //数据集合
    var dataList: MutableList<T> = mutableListOf()

    /*
        设置数据
        Presenter处理过为null的情况，所以为不会为Null
     */
    fun setData(sources: MutableList<T>, isRefresh: Boolean = true) {
        dataList = sources
        if (isRefresh) {
            notifyDataSetChanged()
        }
    }

    /**
     * 添加数据
     */
    fun addData(sources: MutableList<T>) {
        dataList.addAll(sources)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            if (mItemClickListener != null)
                mItemClickListener!!.onItemClick(dataList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    /*
        ItemClick事件声明
     */
    interface OnItemClickListener<in T> {
        fun onItemClick(item: T, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener<T>) {
        this.mItemClickListener = listener
    }
}
