package com.xinly.dendrobe.component.net

import com.xinly.core.net.RequestManager
import com.xinly.dendrobe.util.HostUtil

/**
 * Created by zm on 2019-06-27.
 */
object XinlyRequestManager: RequestManager() {

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> getRequest(clazz: Class<T>): T {
        var t: T? = sRequestManager[clazz] as? T
        if (t == null) {
            t = XinlyRetrofitClient.createApi(clazz, HostUtil.HOST_URL)
            sRequestManager[clazz] = t
        }
        return t
    }
}