package com.xinly.dendrobe.widget.bottomview

import android.widget.TextView
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.net.exception.ApiException
import com.xinly.dendrobe.R
import com.xinly.dendrobe.api.BankApi
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.model.vo.bean.BankBean
import java.lang.ref.WeakReference

/**
 * Created by zm on 2019-07-10.
 */
abstract class BankCardBottomView(activity: RxAppCompatActivity, private val item: BankBean) : BaseBottomView(activity, R.style.BottomViewTheme_Default, R.layout.dialog_bank_bottom) {

    private val activityWR: WeakReference<RxAppCompatActivity> = WeakReference(activity)

    init {
        initView()
    }

    // 删除银行卡
    abstract fun deleteBankCard()
    // 设置默认
    abstract fun setDefaultBankCard()

    private fun initView() {
        rootView?.apply {
            // 设置默认银行卡
            findViewById<TextView>(R.id.setDefault).setOnClickListener {
                dismissBottomView()
                setDefaultBankCard()
            }
            //删除银行卡
            findViewById<TextView>(R.id.deleteBank).setOnClickListener {
                activityWR.get()?.let { it1 ->
                    BankApi().del(item.id, object : XinlyRxSubscriberHelper<BaseResp<Nothing>>(it1, true) {
                        override fun _onNext(t: BaseResp<Nothing>) {
                            dismissBottomView()
                            deleteBankCard()
                        }

                        override fun _onError(apiException: ApiException) {
                            super._onError(apiException)
                            dismissBottomView()
                        }
                    }, it1)
                }
            }
            //取消
            findViewById<TextView>(R.id.cancel).setOnClickListener {
               dismissBottomView()
            }
        }
    }

}