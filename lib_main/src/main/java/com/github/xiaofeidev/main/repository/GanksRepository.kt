package com.github.xiaofeidev.main.repository

import com.github.xiaofeidev.comm.db.GankDatabase
import com.github.xiaofeidev.comm.http.RetrofitClient
import retrofit2.http.Path

/**
 * @author xiaofei_dev
 * @date 2020/8/19
 */
class GanksRepository {
    fun getGanks(type: String) = GankDatabase.getInstance().gankDao().queryGankList(type)

    suspend fun newGanks(type: String) = RetrofitClient.apiCall({ RetrofitClient.service.getGankList(type, 1, 10)}){
        it.gankBeans
    }

    suspend fun moreGanks(type: String, page: Int, count: Int = 10) = RetrofitClient.apiCall({ RetrofitClient.service.getGankList(type, page, count)}){
        it.gankBeans
    }
}