package com.xinly.dendrobe.api

import com.trello.rxlifecycle3.LifecycleProvider
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.convert
import com.xinly.core.ext.execute
import com.xinly.core.ext.handleResult
import com.xinly.core.rx.BaseSubscriber
import com.xinly.dendrobe.component.data.BaseRequestBody
import com.xinly.dendrobe.component.net.XinlyRequestManager
import com.xinly.dendrobe.model.vo.result.BankListData
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 银行卡相关Api
 * <p>
 * Created by zm on 2019-07-08.
 */
class BankApi {

    interface Api {
        //添加银行卡
        @POST("api/user/bank/list/add")
        fun add(@Body requestBody: RequestBody): Observable<BaseResp<Nothing>>
        //请求用户银行卡
        @POST("api/user/bank/list/get")
        fun get(@Body requestBody: RequestBody): Observable<BaseResp<BankListData>>
    }

    private val api: Api

    init {
        api = XinlyRequestManager.getRequest(Api::class.java)
    }

    /**
     * 获取银行卡列表
     */
    fun get(subscriberHelper: BaseSubscriber<BankListData>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, Int>()
        params["paging"] = 0
        params["limit"] = 20

        api.get(BaseRequestBody(params).toRequestBody())
            .convert()
            .execute(subscriberHelper, lifecycleProvider)
    }

    /**
     * 添加银行卡
     * @param bankCode 银行卡号
     * @param bankName 银行名称
     * @param openingBank 开户行
     * @param openingName 开户人姓名
     * @param openingPhone 开户人手机
     */
    fun add(bankCode: String, bankName: String, openingBank: String, openingName: String, openingPhone: String
            , subscriberHelper: BaseSubscriber<BaseResp<Nothing>>, lifecycleProvider: LifecycleProvider<*>) {
        val params = HashMap<String, String>()
        params["bankCode"] = bankCode
        params["bankName"] = bankName
        params["openingBank"] = openingBank
        params["openingName"] = openingName
        params["openingPhone"] = openingPhone

        api.add(BaseRequestBody(params).toRequestBody())
            .handleResult()
            .execute(subscriberHelper, lifecycleProvider)
    }

}