package com.xinly.dendrobe.component.data

import com.xinly.core.XinlyCore
import com.xinly.core.data.protocol.BaseReq
import com.xinly.core.utils.DeviceUtil
import com.xinly.core.utils.EncryptUtils
import com.xinly.core.utils.NetWorkUtils

/**
 * Created by zm on 2019-06-26.
 */
class BaseRequestBody<T>(data: T) : BaseReq<T>(data){

    //设备信息
    private val equip: EquipData
    //请求时间
    private val time: Long = System.currentTimeMillis()
    //加密签名
    private val sign: String

    init {
        equip = EquipData()
        sign = EncryptUtils.encryptMD5ToString("${equip.uuid}$time", "BC4B2A76B9719D91")
    }

    class EquipData{
        //uuid
        val uuid: String = DeviceUtil.getIMEI(XinlyCore.context)
        //version name
        private val appver: String = DeviceUtil.getVersionName(XinlyCore.context)
        //type
        private val type: String = "mobile"
        //os
        private val os: String = "android"
        //os version
        private val version: String = DeviceUtil.getBuildVersion()
        //phone brand
        private val name: String = DeviceUtil.getPhoneBrand()
        //phone model
        private val alias: String = DeviceUtil.getPhoneModel()
        //
        private val network: String = NetWorkUtils.getAPNStr(XinlyCore.context)
    }
}