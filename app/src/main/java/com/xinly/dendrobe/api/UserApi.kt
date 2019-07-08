package com.xinly.dendrobe.api

import com.trello.rxlifecycle3.LifecycleProvider
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.convert
import com.xinly.core.ext.execute
import com.xinly.core.ext.handleResult
import com.xinly.core.rx.BaseSubscriber
import com.xinly.core.utils.EncryptUtils
import com.xinly.dendrobe.component.data.BaseRequestBody
import com.xinly.dendrobe.component.net.XinlyRequestManager
import com.xinly.dendrobe.component.net.XinlyRxSubscriberHelper
import com.xinly.dendrobe.model.vo.result.ChangeUserData
import com.xinly.dendrobe.model.vo.result.WalletRecordsData
import com.xinly.dendrobe.model.vo.result.LoginData
import com.xinly.dendrobe.model.vo.result.RegisterData
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
        // 退出登录
        @POST("api/user/auth/logout/commit")
        fun logout(@Body requestBody: RequestBody): Observable<BaseResp<Nothing>>
        // 重置密码
        @POST("api/user/auth/reset/clear")
        fun reset(@Body requestBody: RequestBody): Observable<BaseResp<Nothing>>
        // 设置密码
        @POST("api/user/auth/reset/set")
        fun setPwd(@Body requestBody: RequestBody): Observable<BaseResp<Nothing>>
        // 填写基本资料
        @POST("api/user/auth/info/commit")
        fun submitData(@Body requestBody: RequestBody): Observable<BaseResp<ChangeUserData>>
        // 更改头像
        @POST("api/user/info/change/avatar")
        fun changeAvatar(@Body requestBody: RequestBody): Observable<BaseResp<ChangeUserData>>
        // 绑定手机
        @POST("api/user/info/change/mobile")
        fun changeMobile(@Body requestBody: RequestBody): Observable<BaseResp<ChangeUserData>>
        // 绑定邀请人
        @POST("api/user/info/change/invite")
        fun changeInvite(@Body requestBody: RequestBody): Observable<BaseResp<ChangeUserData>>
        // 绑定邮箱
        @POST("api/user/info/change/email")
        fun changeEmail(@Body requestBody: RequestBody): Observable<BaseResp<ChangeUserData>>
        // 更改头像
        @POST("api/user/info/change/security")
        fun changeSecurity(@Body requestBody: RequestBody): Observable<BaseResp<ChangeUserData>>
        // 意见反馈
        @POST("api/user/info/report/commit")
        fun reportCommit(@Body requestBody: RequestBody): Observable<BaseResp<Nothing>>
        // 获取青豆记录
        @POST("api/user/wallet/log/bean")
        fun getHaricotVertRecords(@Body requestBody: RequestBody): Observable<BaseResp<WalletRecordsData>>
        // 获取石斛记录
        @POST("api/user/wallet/log/dend")
        fun getDendRecords(@Body requestBody: RequestBody): Observable<BaseResp<WalletRecordsData>>
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

    /**
     * 提交基本资料
     * @param avatar 头像
     * @param nickname 昵称
     * @param nickname 安全码
     */
    fun submitData(avatar: String, nickname: String, security: String,
                   subscriber: BaseSubscriber<ChangeUserData>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, String>()
        params["avatar"] = avatar
        params["nickname"] = nickname
        params["security"] = EncryptUtils.encryptMD5ToString(security)

        api.submitData(BaseRequestBody(params).toRequestBody())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 修改头像
     * @param avatar
     */
    fun changeAvatar(avatar: String, subscriber: XinlyRxSubscriberHelper<ChangeUserData>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, String>()
        params["avatar"] = avatar

        api.changeAvatar(BaseRequestBody(params).toRequestBody())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 修改头像
     * @param inviteCode
     */
    fun changeInvite(inviteCode: String, subscriber: XinlyRxSubscriberHelper<ChangeUserData>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, String>()
        params["inviteCode"] = inviteCode

        api.changeInvite(BaseRequestBody(params).toRequestBody())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 绑定手机
     * @param account
     * @param code
     */
    fun changeMobile(account: String,code: String, subscriber: XinlyRxSubscriberHelper<ChangeUserData>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, String>()
        params["account"] = account
        params["code"] = code

        api.changeMobile(BaseRequestBody(params).toRequestBody())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 绑定邮箱
     * @param avatar
     */
    fun changeEmail(account: String, code: String, subscriber: XinlyRxSubscriberHelper<ChangeUserData>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, String>()
        params["account"] = account
        params["code"] = code

        api.changeEmail(BaseRequestBody(params).toRequestBody())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 修改头像
     * @param security
     * @param code
     */
    fun changeSecurity(security: String, code: String, subscriber: XinlyRxSubscriberHelper<ChangeUserData>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, String>()
        params["security"] = security
        params["code"] = code

        api.changeSecurity(BaseRequestBody(params).toRequestBody())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 意见反馈
     * @param content 反馈内容
     * @param contact 联系方式
     * @param images 图片组
     */
    fun reportCommit(content: String, contact: String, images: Array<String>,subscriber: XinlyRxSubscriberHelper<BaseResp<Nothing>>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, Any>()
        params["content"] = content
        params["contact"] = contact
        params["image"] = images

        api.reportCommit(BaseRequestBody(params).toRequestBody())
            .handleResult()
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 获取青豆记录
     * @param paging 当前分页（从0开始）
     * @param limit 每页条数（不大于100）
     */
    fun getHaricotVertRecords(paging: Int, limit: Int, subscriber: XinlyRxSubscriberHelper<WalletRecordsData>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, Int>()
        params["paging"] = paging
        params["limit"] = limit
        api.getHaricotVertRecords(BaseRequestBody(params).toRequestBody())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 获取石斛记录
     * @param paging 当前分页（从0开始）
     * @param limit 每页条数（不大于100）
     */
    fun getDendRecords(paging: Int, limit: Int, subscriber: XinlyRxSubscriberHelper<WalletRecordsData>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, Int>()
        params["paging"] = paging
        params["limit"] = limit
        api.getDendRecords(BaseRequestBody(params).toRequestBody())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }

    /**
     * 退出登录
     */
    fun logout(subscriber: XinlyRxSubscriberHelper<BaseResp<Nothing>>, lifecycleProvider: LifecycleProvider<*>) {
        api.logout(BaseRequestBody(null).toRequestBody())
            .handleResult()
            .execute(subscriber, lifecycleProvider)
    }
}