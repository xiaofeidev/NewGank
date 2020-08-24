package com.github.xiaofeidev.base.extension

import com.blankj.utilcode.util.SizeUtils

/**
 * @author xiaofei_dev
 * @date 2020/8/14
 */
val Int.dp2px: Int
    get() {
        return SizeUtils.dp2px(this.toFloat())
    }

val Int.px2dp: Int
    get() {
        return SizeUtils.px2dp(this.toFloat())
    }