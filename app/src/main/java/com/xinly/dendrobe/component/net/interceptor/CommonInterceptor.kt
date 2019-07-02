package com.xinly.dendrobe.component.net.interceptor

import com.xinly.core.ext.yes
import com.xinly.dendrobe.component.net.TokenManager
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by zm on 2019-06-27.
 */
class CommonInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest: Request = chain.request()

        /**
         * 公共参数
         */
        val authorizedUrlBuilder: HttpUrl.Builder = oldRequest.url()
            .newBuilder()
            .scheme(oldRequest.url().scheme())
            .host(oldRequest.url().host())

        /**
         * 新的请求
         */
        var requestBuilder: Request.Builder = oldRequest.newBuilder()
            .method(oldRequest.method(), oldRequest.body())
            .url(authorizedUrlBuilder.build())

        /**
         * 已经登录的用户 带上Authorization token
         */
        TokenManager.getToken().isNotEmpty().yes {
            requestBuilder.addHeader(TokenManager.TOKEN, TokenManager.getToken())
        }

        return chain.proceed(requestBuilder.build())
    }

}