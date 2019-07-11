package com.xinly.dendrobe.model.constans

import com.xinly.dendrobe.BuildConfig
import com.xinly.dendrobe.util.HostUtil

/**
 * Created by zm on 2019-06-27.
 */
object Constans{
    /**********************-Config START-*********************/
    // md5盐
    const val MD5_SALT = BuildConfig.MD5_SALT
    //SP表名
    const val TABLE_PREFS = "dendrobe"
    /*********************-Config END-************************/

    //SP键名
    const val SP_USER_BEAN_VISIBLE = "sp_user_bean_visible"
    const val SP_USER_DEND_VISIBLE = "sp_user_dend_visible"
    const val SP_USER_DEF_BANK_CARD = "sp_user_bank_card_def"
}