package com.xinly.dendrobe.model.vo.bean

/**
 * Created by zm on 2019-07-05.
 */
object Event {
    // 通用消息体
    open class MessageEvent
    // 更新青豆余额是否可见
    class UpdateBeanIsVisivle(val isVisible: Boolean): MessageEvent()
}