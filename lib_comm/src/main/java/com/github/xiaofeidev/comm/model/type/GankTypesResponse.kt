package com.github.xiaofeidev.comm.model.type

import com.google.gson.annotations.SerializedName

/**
 * @author xiaofei_dev
 * @date 2020/8/15
 * https://gank.io/api/v2/categories/GanHuo
 */
data class GankTypesResponse(
    @SerializedName("data")
    val gankTypeBeans: List<GankTypeBean>,
    val status: Int
)