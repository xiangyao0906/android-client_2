package com.xinly.dendrobe.api

import com.trello.rxlifecycle3.LifecycleProvider
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.execute
import com.xinly.core.ext.handleResult
import com.xinly.core.rx.BaseSubscriber
import com.xinly.dendrobe.component.data.BaseRequestBody
import com.xinly.dendrobe.component.net.XinlyRequestManager
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 系统相关Api
 * Created by zm on 2019-06-30.
 */
class SystemApi {
    private val api: Api
    init {
        api = XinlyRequestManager.getRequest(SystemApi.Api::class.java)
    }
    interface Api {
        // 获取图形码
        @POST("api/service/verify/captcha/create")
        fun getImageCode(): Observable<BaseResp<Nothing>>
        // 发送验证码
        @POST("api/service/verify/code/send")
        fun sendCode(@Body requestBody: RequestBody): Observable<BaseResp<Nothing>>
    }

    /**
     * 获取图形码
     */
    fun getImageCode(subscriber: BaseSubscriber<BaseResp<Nothing>>, lifecycleProvider: LifecycleProvider<*>) {
        api.getImageCode().execute(subscriber,lifecycleProvider)
    }

    /**
     * 发送验证码
     */
    fun senCode(params: Map<String, String>,subscriber: BaseSubscriber<BaseResp<Nothing>>, lifecycleProvider: LifecycleProvider<*>) {
        api.sendCode(BaseRequestBody(params).toRequestBody())
            .handleResult()
            .execute(subscriber, lifecycleProvider)
    }
}