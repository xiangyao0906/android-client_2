package com.xinly.dendrobe.component.data

import com.xinly.core.data.BaseResultCode

/**
 * Created by zm on 2019-06-27.
 */
class ResultCode: BaseResultCode() {

    companion object {
        /**
         * token失效
         */
        const val SUCCESS_CODE_ERROR_TOKEN = 401
        /**
         * 强制更新
         */
        const val SUCCESS_CODE_UPGRADE_FOUCE = 426
    }
}