package com.github.xiaofeidev.base.utils

import org.greenrobot.eventbus.EventBus

/**
 * @author xiaofei_dev
 * @date 2020/8/14
 */

/**
 * 发送 EventBus 事件
 */
fun postEvent(event: Any){
    EventBus.getDefault().post(event)
}