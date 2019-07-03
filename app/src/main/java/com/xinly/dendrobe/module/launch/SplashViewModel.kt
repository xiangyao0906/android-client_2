package com.xinly.dendrobe.module.launch

import android.annotation.SuppressLint
import android.app.Application
import com.xinly.core.viewmodel.BaseViewModel
import com.xinly.dendrobe.helper.AccountManager
import com.xinly.dendrobe.module.main.MainActivity
import com.xinly.dendrobe.module.user.LoginRegisterActivity
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * Created by zm on 2019-07-02.
 */
class SplashViewModel(application: Application): BaseViewModel(application) {

    /**
     * 自动登录
     */
    @SuppressLint("CheckResult")
    fun autoLogin() {
        Flowable.timer(2, TimeUnit.SECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .doOnComplete {
                if (AccountManager.instance.isLogin()) {
                    startActivity(MainActivity::class.java)
                    finish()
                }else {
                    startActivity(LoginRegisterActivity::class.java)
                    finish()
                }
            }
            .subscribe()
    }
}