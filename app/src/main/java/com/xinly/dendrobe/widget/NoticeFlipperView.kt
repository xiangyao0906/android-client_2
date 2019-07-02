package com.xinly.dendrobe.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.core.content.ContextCompat
import com.xinly.dendrobe.R

/*
    公告组件封装
 */
class NoticeFlipperView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    private val mFlipperView: ViewFlipper

    init {
        val rootView = View.inflate(context, R.layout.layout_news_flipper, null)
        mFlipperView = rootView.findViewById(R.id.mFlipperView)
        mFlipperView.setInAnimation(context, R.anim.notice_bottom_in)
        mFlipperView.setOutAnimation(context, R.anim.notice_bottom_out)
        addView(rootView)
    }


    /*
        构建公告
     */
    private fun buildNewsView(text: String): View {
        val textView = TextView(context)
        textView.text = text
        textView.setTextColor(ContextCompat.getColor(context, R.color.white))
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,12f)
        textView.setSingleLine()
        textView.ellipsize = TextUtils.TruncateAt.END
        textView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)


        return textView
    }

    /*
        设置公告数据
     */
    fun setData(data: List<String>) {
        for (text in data) {
            mFlipperView.addView(buildNewsView(text))
        }
        mFlipperView.startFlipping()
    }
}
