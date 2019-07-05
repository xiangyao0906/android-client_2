package com.xinly.dendrobe

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.xinly.core.XinlyCore

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
}