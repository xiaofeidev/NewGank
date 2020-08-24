package com.github.xiaofeidev.main.repository

import com.github.xiaofeidev.comm.db.GankDatabase
import com.github.xiaofeidev.comm.http.RetrofitClient

/**
 * @author xiaofei_dev
 * @date 2020/8/19
 */
class TypesRepository {
    fun getTypes() = GankDatabase.getInstance().gankDao().queryGankTypeList()

    suspend fun newTypes() =
        RetrofitClient.apiCall({ RetrofitClient.service.getGankType() }){
        it.gankTypeBeans
    }
}