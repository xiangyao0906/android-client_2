package com.xinly.dendrobe

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.xinly.core.XinlyCore
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import android.R
import android.R.attr.colorPrimary
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator



/**
 * Created by zm on 2019/5/10.
 */
class XinlyApplication: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context private set
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        XinlyCore.init(applicationContext)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    //static 代码段可以防止内存泄露
    init {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout -> ClassicsHeader(context) }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout -> ClassicsFooter(context).setDrawableSize(20f) }
    }
}