package com.github.xiaofeidev.main.repository

import com.github.xiaofeidev.comm.db.GankDatabase
import com.github.xiaofeidev.comm.http.RetrofitClient
import com.github.xiaofeidev.comm.model.gank.GankBean

/**
 * @author 黎曼
 * @date 2020/8/23
 */
class GirlsRepository {
    companion object{
        const val GIRL_TYPE = "Girl"
    }
    fun getGirls() = GankDatabase.getInstance().gankDao().queryGankList(GIRL_TYPE)

    suspend fun newGirls() =
        RetrofitClient.apiCall({ RetrofitClient.service.getGirlList(1, 10)}){
        it.gankBeans
    }

    //保存首页数据到数据库
    suspend fun saveGirls(data: List<GankBean>){
        //手动生成每条数据的 dbId
        data.forEach {
            it.dbId = "${GIRL_TYPE}_${it.id}"
        }
        GankDatabase.getInstance().gankDao().saveGankList(data)
    }

    suspend fun moreGirls(page: Int, count: Int = 10) =
        RetrofitClient.apiCall({ RetrofitClient.service.getGirlList(page, count)}){
        it.gankBeans
    }
}