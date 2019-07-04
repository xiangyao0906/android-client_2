package com.xinly.dendrobe.api

import com.trello.rxlifecycle3.LifecycleProvider
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.convert
import com.xinly.core.ext.execute
import com.xinly.core.ext.handleResult
import com.xinly.core.rx.BaseSubscriber
import com.xinly.dendrobe.component.data.BaseRequestBody
import com.xinly.dendrobe.component.net.XinlyRequestManager
import com.xinly.dendrobe.model.vo.result.ConfigIndexData
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
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
        fun getImageCode(@Body requestBody: RequestBody): Observable<ResponseBody>
        // 发送验证码
        @POST("api/service/verify/code/send")
        fun sendCode(@Body requestBody: RequestBody): Observable<BaseResp<Nothing>>
        // 获取首页信息
        @POST("api/service/config/index")
        fun configIndex(@Body requestBody: RequestBody): Observable<BaseResp<ConfigIndexData>>
        // 检查数字验证码
        @POST("api/service/verify/code/check")
        fun checkCode(@Body requestBody: RequestBody): Observable<BaseResp<Nothing>>
    }

    /**
     * 获取图形码
     */
    fun getImageCode(subscriber: BaseSubscriber<ResponseBody>, lifecycleProvider: LifecycleProvider<*>) {
        api.getImageCode(BaseRequestBody(null).toRequestBody()).execute(subscriber,lifecycleProvider)
    }

    /**
     * 发送验证码
     */
    fun senCode(params: Map<String, String>,subscriber: BaseSubscriber<BaseResp<Nothing>>, lifecycleProvider: LifecycleProvider<*>) {
        api.sendCode(BaseRequestBody(params).toRequestBody())
            .handleResult()
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 获取用户首页配置
     */
    fun configIndex(subscriber: BaseSubscriber<ConfigIndexData>, lifecycleProvider: LifecycleProvider<*>) {
        api.configIndex(BaseRequestBody(null).toRequestBody())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 检查数字验证码
     * @param type 验证类型(mobile/email)
     * @param target 验证对象
     * @param code 验证码
     */
    fun checkCode(type: String, target: String, code: String, subscriber: BaseSubscriber<BaseResp<Nothing>>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, String>()
        params["type"] = type
        params["target"] = target
        params["code"] = code
        api.checkCode(BaseRequestBody(params).toRequestBody())
            .handleResult()
            .execute(subscriber, lifecycleProvider)
    }
}