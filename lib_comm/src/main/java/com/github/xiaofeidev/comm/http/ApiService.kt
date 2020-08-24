package com.github.xiaofeidev.comm.http

import com.github.xiaofeidev.comm.model.gank.GanksResponse
import com.github.xiaofeidev.comm.model.type.GankTypesResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author xiaofei_dev
 * @date 2020/8/15
 */
interface ApiService {
    @GET("categories/GanHuo")
    suspend fun getGankType(): GankTypesResponse

    @GET("data/category/GanHuo/type/{type}/page/{page}/count/{count}")
    suspend fun getGankList(@Path("type") type: String, @Path("page") page: Int, @Path("count") count: Int): GanksResponse

    //获取 Girls 图片列表
    @GET("data/category/Girl/type/Girl/page/{page}/count/{count}")
    suspend fun getGirlList(@Path("page") page: Int, @Path("count") count: Int): GanksResponse
}