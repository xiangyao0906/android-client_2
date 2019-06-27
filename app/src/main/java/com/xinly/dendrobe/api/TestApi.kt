package com.xinly.dendrobe.api

import com.trello.rxlifecycle3.LifecycleProvider
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.convert
import com.xinly.core.ext.execute
import com.xinly.core.rx.BaseSubscriber
import com.xinly.dendrobe.component.data.BaseRequestBody
import com.xinly.dendrobe.component.net.XinlyRequestManager
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by zm on 2019-06-26.
 */
class TestApi {

    private interface Api {
        @POST("/api/debug/inside/sign")
        fun bankTest(@Body requestBody: okhttp3.RequestBody): Observable<BaseResp<Int>>
    }

    private val api: Api

    init {
        api = XinlyRequestManager.getRequest(Api::class.java)
    }

    /**
     * 测试
     */
    fun signTest(subscriber: BaseSubscriber<Int>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, String>()
        params["test"] = "测试."
        params["remark"] = "就是测试咯."

        api.bankTest(BaseRequestBody<Map<String, String>>(params).toRequestBody())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }
}