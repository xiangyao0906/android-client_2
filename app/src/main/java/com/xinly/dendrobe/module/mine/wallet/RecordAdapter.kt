package com.xinly.dendrobe.module.mine.wallet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.xinly.dendrobe.R
import com.xinly.dendrobe.base.BaseRecyclerViewAdapter
import com.xinly.dendrobe.model.vo.bean.WalletRecordBean
import com.xinly.dendrobe.util.DateUtil
import kotlinx.android.synthetic.main.item_wallet_record.view.*

/**
 * 钱包记录
 * <p>
 * Created by zm on 2019-07-02.
 */
class RecordAdapter(context: Context): BaseRecyclerViewAdapter<WalletRecordBean, RecordAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.item_wallet_record, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.content.text = model.content
        holder.itemView.num.apply {
            text = if (model.num>0){"+".plus(model.num)} else model.num.toString()
            setTextColor(ContextCompat.getColor(mContext, if (model.num>0) R.color.font_red_light else R.color.font_black))
        }
        holder.itemView.createTime.text = DateUtil.date2Str(model.createTime, DateUtil.FORMAT_YMDHM)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}