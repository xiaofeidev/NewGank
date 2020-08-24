package com.github.xiaofeidev.main.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.github.xiaofeidev.comm.db.GankDatabase
import com.github.xiaofeidev.comm.db.dao.GankDao
import com.github.xiaofeidev.comm.http.DataResult
import com.github.xiaofeidev.comm.http.RetrofitClient
import com.github.xiaofeidev.comm.http.RetrofitClient.service
import com.github.xiaofeidev.comm.model.gank.GankBean
import com.github.xiaofeidev.comm.model.type.GankTypeBean
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * @author xiaofei_dev
 * @date 2020/8/17
 */
class MainRepositoryImpl(): MainRepository {
    //类型数据列表
    private lateinit var typeList: LiveData<MutableList<GankTypeBean>>

    override suspend fun getTypeList(): LiveData<MutableList<GankTypeBean>> {
        if (::typeList.isInitialized) return typeList

        typeList = GankDatabase.getInstance().gankDao().queryGankTypeList()
        refreshTypeList()
        return typeList
    }

    override suspend fun refreshTypeList() {
        val dataResult = RetrofitClient.apiCall({service.getGankType()}){
            it.gankTypeBeans
        }
        if (dataResult is DataResult.Success){
            //是否需要更新数据库
            if (typeList.value?.containsAll(dataResult.data)?.not() ?: true){
                val result = GankDatabase.getInstance().gankDao().saveGanTypeList(dataResult.data)
            }
        } /*else {//dataResult is DataResult.Error
            //是否需要展示网络错误
            if (typeList.value.isNullOrEmpty() ?: true){
                typeListResult.value = dataResult
            }
        }*/
    }
}