package com.xinly.dendrobe.widget.bottomview

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import com.ashokvarma.bottomnavigation.utils.Utils
import com.xinly.dendrobe.R

/**
 * 底部弹出框
 * <p>
 * Created by zm on 2019-07-10.
 */
open class BaseBottomView(protected val context: Context, private val theme: Int, resource: Int=0, protected var rootView: View?=null) {

    private var bv: Dialog? = null
    private var animationStyle: Int = 0

    init {
        if (resource!=0) {
            rootView = View.inflate(context, resource, null)
        }
        setAnimation(R.style.BottomToTopAnim)
    }


    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun showBottomView(canceledOnTouchOutside: Boolean) {
        if (isShow()) return
        if (this.bv == null) {
            if (theme == 0) {
                this.bv = Dialog(this.context)
            }else{
                this.bv = Dialog(this.context, this.theme)
            }
            bv!!.apply {
                setCanceledOnTouchOutside(canceledOnTouchOutside)
                window.requestFeature(1)
                @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                setContentView(this@BaseBottomView.rootView)
                val p: WindowManager.LayoutParams = window.attributes
                p.width = Utils.getScreenWidth(context)*1
                p.gravity = 80
                if (this@BaseBottomView.animationStyle!=0) {
                    window.setWindowAnimations(this@BaseBottomView.animationStyle)
                }
                window.attributes = p
            }
        }
        bv?.show()
    }

    fun dismissBottomView() {
        bv?.dismiss()
    }

    private fun isShow(): Boolean {
        return bv?.isShowing?:false
    }

    private fun setAnimation(animationStyle: Int) {
        this.animationStyle = animationStyle
    }
}