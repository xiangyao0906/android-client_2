package com.xinly.dendrobe.module.main

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import com.xinly.core.ext.no
import com.xinly.dendrobe.R
import com.xinly.dendrobe.api.TestApi
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper

class MainActivity : RxAppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RxPermissions(this).request(Manifest.permission.READ_PHONE_STATE)
            .subscribe {
                it?.no {
                    finish()
                }
            }
    }

    fun sendRequest(view: View) {
        TestApi().signTest(object : XinlyRxSubscriberHelper<Int>(this, true){
            override fun _onNext(t: Int) {

            }

        }, this)
    }
}
