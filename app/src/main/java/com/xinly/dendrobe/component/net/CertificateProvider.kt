package com.xinly.dendrobe.component.net

import com.xinly.dendrobe.XinlyApplication
import java.io.InputStream

/**
 * 根据URL的地址来判断证书的加载
 * <p>
 * Created by zm on 2019-06-27.
 */
object CertificateProvider {

    fun getCertificateStreams(baseUrl: String): Array<InputStream>? {
        val cerName: String = if (baseUrl.startsWith("")) "" else ""
        return if (cerName.isNotEmpty()) arrayOf(XinlyApplication.context.assets.open(cerName)) else null
    }

}