package com.github.xiaofeidev.main.repository

import androidx.lifecycle.LiveData
import com.github.xiaofeidev.comm.model.gank.GankBean
import com.github.xiaofeidev.comm.model.type.GankTypeBean

/**
 * @author xiaofei_dev
 * @date 2020/8/17
 */
interface MainRepository {
    //获取干货类型，本地数据库作为单一可信数据源
    suspend fun getTypeList(): LiveData<MutableList<GankTypeBean>>
    //刷新本地数据库数据
    suspend fun refreshTypeList()
}