package com.github.xiaofeidev.comm.model.gank

import com.google.gson.annotations.SerializedName

/**
 * @author xiaofei_dev
 * @date 2020/8/15
 * https://gank.io/api/v2/data/category/GanHuo/type/Android/page/1/count/10
 */
data class GanksResponse(
    @SerializedName("data")
    val gankBeans: List<GankBean>,
    val page: Int,
    val page_count: Int,
    val status: Int,
    val total_counts: Int
)