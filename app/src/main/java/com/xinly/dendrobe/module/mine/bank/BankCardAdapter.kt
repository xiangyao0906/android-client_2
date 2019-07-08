package com.xinly.dendrobe.module.mine.bank

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xinly.dendrobe.R
import com.xinly.dendrobe.base.BaseRecyclerViewAdapter
import com.xinly.dendrobe.model.vo.bean.BankBean
import kotlinx.android.synthetic.main.item_bank_card.view.*

/**
 * 钱包记录
 * <p>
 * Created by zm on 2019-07-02.
 */
class BankCardAdapter(context: Context): BaseRecyclerViewAdapter<BankBean, BankCardAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.item_bank_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.bankName.text = model.bankName
        holder.itemView.bankType.text = "储蓄卡"
        //银行卡
        holder.itemView.bankCode.text = "**** **** **** ".plus(model.bankCode.substring(12..15))

        when(model.bankName) {
            "中国银行" -> { //boc
                holder.itemView.bankIcon.setImageResource(R.drawable.bank_boc_icon)
                holder.itemView.bankBgIcon.setImageResource(R.drawable.bank_boc_icon2)
                holder.itemView.bankbg.setBackgroundResource(R.drawable.bank_card_boc_bg)
            }
            "工商银行" -> { //icbc
                holder.itemView.bankIcon.setImageResource(R.drawable.bank_icbc_icon)
                holder.itemView.bankBgIcon.setImageResource(R.drawable.bank_icbc_icon2)
                holder.itemView.bankbg.setBackgroundResource(R.drawable.bank_card_icbc_bg)
            }
            "交通银行" -> { //bcm
                holder.itemView.bankIcon.setImageResource(R.drawable.bank_bcm_icon)
                holder.itemView.bankBgIcon.setImageResource(R.drawable.bank_bcm_icon2)
                holder.itemView.bankbg.setBackgroundResource(R.drawable.bank_card_bcm_bg)
            }
            "农业银行" -> { //abc
                holder.itemView.bankIcon.setImageResource(R.drawable.bank_abc_icon)
                holder.itemView.bankBgIcon.setImageResource(R.drawable.bank_abc_icon2)
                holder.itemView.bankbg.setBackgroundResource(R.drawable.bank_card_abc_bg)
            }
            "建设银行" -> { //ccb
                holder.itemView.bankIcon.setImageResource(R.drawable.bank_ccb_icon)
                holder.itemView.bankBgIcon.setImageResource(R.drawable.bank_ccb_icon2)
                holder.itemView.bankbg.setBackgroundResource(R.drawable.bank_card_ccb_bg)
            }
            else -> {
                holder.itemView.bankIcon.setImageResource(R.drawable.bank_union_pay_icon)
                holder.itemView.bankBgIcon.setImageResource(R.drawable.bank_union_pay_icon2)
                holder.itemView.bankbg.setBackgroundResource(R.drawable.bank_card_common_bg)
            }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}