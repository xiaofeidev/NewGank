package com.github.xiaofeidev.main.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.github.xiaofeidev.comm.db.GankDatabase
import com.github.xiaofeidev.comm.http.DataResult
import com.github.xiaofeidev.comm.http.RetrofitClient
import com.github.xiaofeidev.comm.model.gank.GankBean

/**
 * @author xiaofei_dev
 * @date 2020/8/19
 */
class GankListRepositoryImpl: GankListRepository {
    //直接的数据列表
    private lateinit var firstPageData: LiveData<List<GankBean>>
    private lateinit var firstPageResult: MutableLiveData<DataResult<List<GankBean>>>

    override suspend fun fetchInitData(type: String, page: Int, count: Int) {
        firstPageData = GankDatabase.getInstance().gankDao().queryGankList(type)
    }

    override suspend fun refreshGankList(type: String) {
        val dataResult = RetrofitClient.apiCall({ RetrofitClient.service.getGankList(type, 1, 10)}){
            it.gankBeans
        }
        if (dataResult is DataResult.Success){
            //是否需要更新数据库
            //判断网络获取的第一条数据和本地数据库的第一条是否【不】相等
            if (dataResult.data.isNotEmpty() && dataResult.data[0] != firstPageData.value?.get(0)){
                //手动生成每条数据的 dbId
                dataResult.data.forEach {
                    it.dbId = "${type}_${it.id}"
                }
                GankDatabase.getInstance().gankDao().saveGankList(dataResult.data)
            }
        } else {//dataResult is DataResult.Error
            //通知上游网络错误
            firstPageResult.postValue(dataResult)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun getGankList(type: String): MutableLiveData<DataResult<List<GankBean>>> {
        fetchInitData(type, 1, 10)
        firstPageResult = Transformations.map(firstPageData){
            DataResult.Success(it)
        } as MutableLiveData<DataResult<List<GankBean>>>

//        refreshGankList(type)

        return firstPageResult
    }

    override suspend fun getMoreGankList(
        type: String,
        page: Int,
        count: Int
    ): LiveData<DataResult<List<GankBean>>> {
        //TODO("Not yet implemented")
        return MutableLiveData()
    }
}