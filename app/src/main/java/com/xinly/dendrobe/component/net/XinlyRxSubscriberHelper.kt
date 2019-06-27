package com.xinly.dendrobe.component.net

import android.content.Context
import com.xinly.core.ext.yes
import com.xinly.core.log.DLog
import com.xinly.core.net.RxSubscriberHelper
import com.xinly.core.net.exception.ApiException
import com.xinly.core.net.exception.ExceptionEngine
import com.xinly.core.utils.ThrowableUtil
import com.xinly.dendrobe.component.data.ResultCode
import io.reactivex.exceptions.CompositeException

/**
 * Created by zm on 2019-06-27.
 */
abstract class XinlyRxSubscriberHelper<T>(context: Context? = null, isShowLoad: Boolean = false)
    : RxSubscriberHelper<T>(context, isShowLoad) {

    override fun onError(e: Throwable) {
        autoThrowable(e)

        val apiException: ApiException = ExceptionEngine.handleException(e)
        if (isShowMessage) {
            onShowMessage(apiException)
        }
        if (isShowLoad){
            onDismissLoad()
        }

        when(apiException.code){
            /**
             * 升级检查
             */
            ResultCode.SUCCESS_CODE_UPGRADE_FOUCE->{
                onUpgradeError(apiException)
            }
            /**
             * token权限校验 SUCCESS_CODE_ERROR_TOKEN
             */
            ResultCode.SUCCESS_CODE_ERROR_TOKEN -> {
                isCheckPermission.yes {
                    onPermissionError(apiException)
                }
            }
            /**
             * 其它错误
             */
            else -> {
                _onError(apiException)
            }
        }
    }

    private fun log(messages: String) {
        DLog.e("", messages)
    }

    /**
     * 更新
     */
    private fun onUpgradeError(apiException: ApiException) {

    }

    /**
     * 输出错误日志
     */
    private fun autoThrowable(throwable: Throwable) {
        if (throwable is CompositeException){
            val throwables: List<Throwable>? = throwable.exceptions
            throwables?.forEach {
                parseThrowable(it)
            }
        }else{
            parseThrowable(throwable)
        }
    }

    private fun parseThrowable(throwable: Throwable) {
        DLog.e("", ThrowableUtil.getFullStackTrace(throwable))
    }
}