package com.github.xiaofeidev.main.repository

import com.github.xiaofeidev.comm.db.GankDatabase
import com.github.xiaofeidev.comm.http.DataResult
import com.github.xiaofeidev.comm.http.RetrofitClient
import com.github.xiaofeidev.comm.model.gank.GankBean
import retrofit2.http.Path

/**
 * @author xiaofei_dev
 * @date 2020/8/19
 */
class GanksRepository {
    fun getGanks(type: String) = GankDatabase.getInstance().gankDao().queryGankList(type)

    suspend fun newGanks(type: String) =
        RetrofitClient.apiCall({ RetrofitClient.service.getGankList(type, 1, 10)}){
        it.gankBeans
    }

    //保存首页数据到数据库
    suspend fun saveGanks(type: String, data: List<GankBean>){
        //手动生成每条数据的 dbId
        data.forEach {
            it.dbId = "${type}_${it.id}"
        }
        GankDatabase.getInstance().gankDao().saveGankList(data)
    }

    suspend fun moreGanks(type: String, page: Int, count: Int = 10) =
        RetrofitClient.apiCall({ RetrofitClient.service.getGankList(type, page, count)}){
        it.gankBeans
    }
}