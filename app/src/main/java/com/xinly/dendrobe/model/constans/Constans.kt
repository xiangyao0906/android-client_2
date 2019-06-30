package com.xinly.dendrobe.model.constans

import com.xinly.dendrobe.BuildConfig
import com.xinly.dendrobe.util.HostUtil

/**
 * Created by zm on 2019-06-27.
 */
object Constans{
    /**********************-BuildConfig START-*********************/
    // md5盐
    const val MD5_SALT = BuildConfig.MD5_SALT
    /*********************-BuildConfig END-************************/

    const val URL_IMAGE_CODE = HostUtil.HOST_URL + "api/service/verify/captcha/create/"
}