package com.xinly.dendrobe.api

import com.trello.rxlifecycle3.LifecycleProvider
import com.xinly.core.data.protocol.BaseResp
import com.xinly.core.ext.convert
import com.xinly.core.ext.execute
import com.xinly.core.ext.handleResult
import com.xinly.core.ext.show
import com.xinly.core.rx.BaseSubscriber
import com.xinly.dendrobe.component.data.FormDataRequestBody
import com.xinly.dendrobe.component.net.XinlyRequestManager
import com.xinly.dendrobe.model.vo.result.UploadImageData
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File

/**
 * 文件上传
 * <p>
 * Created by zm on 2019-07-02.
 */
class FileApi {

    private interface Api {
        @Multipart
        @POST("api/service/upload/image")
        fun uploadFile(@Part params: ArrayList<MultipartBody.Part>): Observable<BaseResp<UploadImageData>>
    }

    private val api: Api

    init {
        api = XinlyRequestManager.getRequest(Api::class.java)
    }

    /**
     * 上传文件
     * @param filePath 文件路径
     */
    fun uploadFile(filePath: String, moduleName: String, subscriber: BaseSubscriber<UploadImageData>, lifecycleProvider: LifecycleProvider<*>) {
        val file = File(filePath)
        if (!file.exists()) "文件不存在".show()

        api.uploadFile(FormDataRequestBody(file, moduleName).toPartList())
            .convert()
            .execute(subscriber, lifecycleProvider)
    }
}