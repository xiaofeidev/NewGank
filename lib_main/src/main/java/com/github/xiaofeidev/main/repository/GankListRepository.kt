package com.github.xiaofeidev.main.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.xiaofeidev.comm.http.DataResult
import com.github.xiaofeidev.comm.model.gank.GankBean

/**
 * @author xiaofei_dev
 * @date 2020/8/18
 */
interface GankListRepository {
    //获取第一页干货列表
    suspend fun fetchInitData(type: String, page: Int, count: Int)
    //刷新第一页干货列表
    suspend fun refreshGankList(type: String)

    //ViewModel层 获取第一页干货列表
    suspend fun getGankList(type: String): MutableLiveData<DataResult<List<GankBean>>>

    //获取更多干货列表，不做缓存，纯网络获取
    suspend fun getMoreGankList(type: String, page: Int, count: Int): LiveData<DataResult<List<GankBean>>>
}