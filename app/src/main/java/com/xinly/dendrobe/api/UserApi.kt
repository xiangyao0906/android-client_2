package com.xinly.dendrobe.api

import com.trello.rxlifecycle3.LifecycleProvider
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.convert
import com.xinly.core.ext.execute
import com.xinly.core.rx.BaseSubscriber
import com.xinly.core.utils.EncryptUtils
import com.xinly.dendrobe.component.data.BaseRequestBody
import com.xinly.dendrobe.component.net.XinlyRequestManager
import com.xinly.dendrobe.model.vo.result.LoginData
import com.xinly.dendrobe.model.vo.result.RegisterData
import com.xinly.dendrobe.module.user.LoginActivity
import io.reactivex.Observable
import okhttp3.RequestBody
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
        // 注册
        @POST("api/user/auth/register/commit")
        fun commit(@Body requestBody: RequestBody): Observable<BaseResp<RegisterData>>
        // 登陆
        @POST("api/user/auth/login/commit")
        fun login(@Body requestBody: RequestBody): Observable<BaseResp<LoginData>>
        // 重置密码
        @POST("api/user/auth/reset/clear")
        fun reset(@Body requestBody: RequestBody): Observable<BaseResp<Nothing>>
        // 设置密码
        @POST("api/user/auth/reset/set")
        fun setPwd(@Body requestBody: RequestBody): Observable<BaseResp<Nothing>>
    }

    /**
     * 注册
     */
    fun register(params: Map<String, String>, subscriber: BaseSubscriber<RegisterData>, lifecycleProvider: LifecycleProvider<*>) {
        api.commit(BaseRequestBody(params).toRequestBody())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 登陆
     */
    fun login(type: String, account: String, passWord: String,
              subscriber: BaseSubscriber<LoginData>, lifecycleProvider: LifecycleProvider<*>){
        val params = HashMap<String, String>()
        params["type"] = type
        params["account"] = account
        params["password"] = EncryptUtils.encryptMD5ToString(passWord)

        api.login(BaseRequestBody(params).toRequestBody())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 重置密码
     */
    fun reset(type: String, account: String, code: String,
              subscriber: BaseSubscriber<BaseResp<Nothing>>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, String>()
        params["type"] = type
        params["account"] = account
        params["code"] = code

        api.reset(BaseRequestBody(params).toRequestBody())
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 设置密码
     */
    fun setPwd(type: String, account: String, password: String,
              subscriber: BaseSubscriber<BaseResp<Nothing>>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, String>()
        params["type"] = type
        params["account"] = account
        params["password"] = password

        api.setPwd(BaseRequestBody(params).toRequestBody())
            .execute(subscriber, lifecycleProvider)
    }
}