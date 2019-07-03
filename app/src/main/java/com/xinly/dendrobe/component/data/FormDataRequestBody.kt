package com.xinly.dendrobe.component.data

import com.google.gson.Gson
import com.xinly.core.XinlyCore
import com.xinly.core.utils.DeviceUtil
import com.xinly.core.utils.EncryptUtils
import com.xinly.core.utils.NetWorkUtils
import com.xinly.dendrobe.component.net.TokenManager
import com.xinly.dendrobe.model.constans.Constans
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by zm on 2019-06-26.
 */
class FormDataRequestBody(private val file: File, private val moduleName: String){

    //设备信息
    private val equip: EquipData
    //请求时间
    private val time: Long = System.currentTimeMillis()
    //加密签名
    private val sign: String

    init {
        equip = EquipData()
        sign = EncryptUtils.encryptMD5ToString("${equip.uuid}${TokenManager.getToken()}$time", Constans.MD5_SALT)
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

    fun toPartList(): ArrayList<MultipartBody.Part> {
        val parts = arrayListOf<MultipartBody.Part>()
        val reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val reqFilePart = MultipartBody.Part.createFormData("data[file]", file.name, reqFile)
        val reqTypePart = MultipartBody.Part.createFormData("data[type]", "file")
        val reqModulePart = MultipartBody.Part.createFormData("data[module]", moduleName)
        val reqTimePart = MultipartBody.Part.createFormData("time", time.toString())
        val reqSignPart = MultipartBody.Part.createFormData("sign", sign)
        val reqEquipPart = MultipartBody.Part.createFormData("equip", Gson().toJson(EquipData()))
        parts.add(reqFilePart)
        parts.add(reqTypePart)
        parts.add(reqModulePart)
        parts.add(reqTimePart)
        parts.add(reqSignPart)
        parts.add(reqEquipPart)
        return parts
    }
}