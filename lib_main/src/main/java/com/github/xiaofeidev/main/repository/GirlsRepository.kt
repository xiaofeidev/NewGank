package com.github.xiaofeidev.main.repository

import com.github.xiaofeidev.comm.db.GankDatabase
import com.github.xiaofeidev.comm.http.RetrofitClient

/**
 * @author 黎曼
 * @date 2020/8/23
 */
class GirlsRepository {
    companion object{
        const val GIRL_TYPE = "Girl"
    }
    fun getGirls() = GankDatabase.getInstance().gankDao().queryGankList(GIRL_TYPE)

    suspend fun newGirls() = RetrofitClient.apiCall({ RetrofitClient.service.getGirlList(1, 10)}){
        it.gankBeans
    }

    suspend fun moreGirls(page: Int, count: Int = 10) = RetrofitClient.apiCall({ RetrofitClient.service.getGirlList(page, count)}){
        it.gankBeans
    }
}