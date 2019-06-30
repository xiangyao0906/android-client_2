package com.xinly.dendrobe.api

import com.trello.rxlifecycle3.LifecycleProvider
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.convert
import com.xinly.core.ext.execute
import com.xinly.core.rx.BaseSubscriber
import com.xinly.dendrobe.component.data.BaseRequestBody
import com.xinly.dendrobe.component.net.XinlyRequestManager
import com.xinly.dendrobe.model.vo.result.RegisterData
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 用户相关Api
 * <p>
 * Created by zm on 2019-06-28.
 */
class UserApi {

    private val api: Api
    init {
        api = XinlyRequestManager.getRequest(Api::class.java)
    }

    private interface Api {
        @POST("api/user/auth/register/commit")
        fun commit(@Body requestBody: okhttp3.RequestBody): Observable<BaseResp<RegisterData>>
    }

    /**
     * 测试
     */
    fun register(params: Map<String, String>, subscriber: BaseSubscriber<RegisterData>, lifecycleProvider: LifecycleProvider<*>) {
        api.commit(BaseRequestBody(params).toRequestBody())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }
}