@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.xinly.dendrobe.util

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.google.gson.Gson
import com.xinly.core.log.DLog
import com.xinly.dendrobe.XinlyApplication
import com.xinly.dendrobe.model.constans.Constans
import java.lang.Exception

/*
 * SP工具类
 *
 */
object PrefsUtils {
    private var sp: SharedPreferences = XinlyApplication.context.getSharedPreferences(Constans.TABLE_PREFS, Context.MODE_PRIVATE)
    private var ed: Editor

    init {
        ed = sp.edit()
    }

    /*
        Boolean数据
     */
    fun putBoolean(key: String, value: Boolean) {
        ed.putBoolean(key, value)
        ed.commit()
    }

    /*
        默认 false
     */
    fun getBoolean(key: String): Boolean {
        return sp.getBoolean(key, false)
    }

    /*
        默认 true
     */
    fun getBoolean(key: String, defBoolean: Boolean): Boolean {
        return sp.getBoolean(key, defBoolean)
    }

    /*
        String数据
     */
    fun putString(key: String, value: String) {
        ed.putString(key, value)
        ed.commit()
    }

    /*
        默认 ""
     */
    fun getString(key: String): String {
        return sp.getString(key, "")
    }

    /*
        Int数据
     */
    fun putInt(key: String, value: Int) {
        ed.putInt(key, value)
        ed.commit()
    }

    /*
        默认 0
     */
    fun getInt(key: String): Int {
        return sp.getInt(key, 0)
    }

    /*
        Long数据
     */
    fun putLong(key: String, value: Long) {
        ed.putLong(key, value)
        ed.commit()
    }

    /*
        默认 0
     */
    fun getLong(key: String): Long {
        return sp.getLong(key, 0)
    }

    /*
        Set数据
     */
    fun putStringSet(key: String, set: Set<String>) {
        val localSet = getStringSet(key).toMutableSet()
        localSet.addAll(set)
        ed.putStringSet(key, localSet)
        ed.commit()
    }

    /*
        默认空set
     */
    fun getStringSet(key: String): Set<String> {
        val set = setOf<String>()
        return sp.getStringSet(key, set)
    }

    /*
        通过Gson转换成json格式的String进行存储对象
     */
    fun putObject(key: String, value: Any) {
        putString(key, Gson().toJson(value))
    }

    /**
        通过Gson去解析json格式，转换回对象返回
     */
    fun <T> getObject(key: String, clazz: Class<T>): T? {
        val jsonString = getString(key)
        if (jsonString.isEmpty()) {
            return null
        }
        return try {
            Gson().fromJson<T>(jsonString, clazz)
        }catch (e: Exception) {
            DLog.e("PrefsUtils", e.message)
            null
        }
    }

    /*
        删除key数据
     */
    fun remove(key: String) {
        ed.remove(key)
        ed.commit()
    }
}
