package com.xinly.dendrobe.component.net

import SSL
import com.xinly.core.XinlyCore
import com.xinly.core.ext.yes
import com.xinly.core.log.DLog
import com.xinly.core.net.RetrofitClient
import com.xinly.core.utils.SystemUtil
import com.xinly.dendrobe.component.net.interceptor.CommonInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.concurrent.TimeUnit

/**
 * Created by zm on 2019-06-27.
 */
object XinlyRetrofitClient: RetrofitClient() {


    fun <T> createApi(clazz: Class<T>, baseUrl: String) : T {
        val builder: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getClient(baseUrl))
            .build()
        return builder.create(clazz)
    }


     private fun getClient(baseUrl: String) : OkHttpClient {
        /**
         * 使用自定义logInterceptor 支持 chrome输出，在debug 或 beta版本
         */
        val logInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            try {
                val text = URLDecoder.decode(message, "utf-8")
                DLog.i("", text)
            }catch (e: UnsupportedEncodingException){
                e.message?.let { DLog.e("", it) }
            }
        })
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        /**
         * 公共参数
         */
        val commonInterceptor = CommonInterceptor()

        /**
         * 本地缓存
         */
        val cacheFile = File(XinlyCore.context.cacheDir, "cache")
        val cache = Cache(cacheFile, 1024 * 1024 * 100)

        /**
         * 请求缓存策略
         */
        val mRewriteCacheControlInterceptor = getInterceptor()
        /**
         * ssl
         */
        val sslParams = SSL.getSslSocketFactory(CertificateProvider.getCertificateStreams(baseUrl), null, null)

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
            .addInterceptor(mRewriteCacheControlInterceptor)
            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
            .addInterceptor(commonInterceptor)
            .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
            .cache(cache)

        (SystemUtil.isCanLog()).yes {
            okHttpClient.addInterceptor(logInterceptor)
        }

        return okHttpClient.build()
    }
}