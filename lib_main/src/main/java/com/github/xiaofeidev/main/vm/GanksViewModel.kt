package com.github.xiaofeidev.main.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.github.xiaofeidev.base.vm.BaseViewModel
import com.github.xiaofeidev.comm.db.GankDatabase
import com.github.xiaofeidev.comm.http.DataResult
import com.github.xiaofeidev.comm.model.gank.GankBean
import com.github.xiaofeidev.main.repository.GankListRepository
import com.github.xiaofeidev.main.repository.GanksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * @author xiaofei_dev
 * @date 2020/8/19
 */
class GanksViewModel(val ganksRepository: GanksRepository): BaseViewModel() {
    lateinit var type: String
    //默认一页几条数据
    val PAGE_SIZE = 10
    //加载更多数据到第几页了
    var nextPage = 2

    private val firstPage by lazy {
        ganksRepository.getGanks(type)
    }

    @Suppress("UNCHECKED_CAST")
    val firstPageResult: MutableLiveData<DataResult<List<GankBean>>> by lazy {
        Transformations.map(firstPage){
            //初次从数据库读取，列表可能为空
            DataResult.Success(it)
        } as MutableLiveData<DataResult<List<GankBean>>>
    }

    //更多 GankBean 数据
    val moreGanksResult = MutableLiveData<DataResult<List<GankBean>>>()

    suspend fun refresh(){
        viewModelScope.launch(Dispatchers.IO){
            val result = ganksRepository.newGanks(type)

            if (result is DataResult.Success){
                //是否需要更新数据库
                //判断网络获取的第一条数据和本地数据库的第一条是否【不】相等
                if (result.data.isNotEmpty() && (firstPage.value.isNullOrEmpty() || result.data[0] != firstPage.value?.get(0))){
                    //手动生成每条数据的 dbId
                    result.data.forEach {
                        it.dbId = "${type}_${it.id}"
                    }
                    GankDatabase.getInstance().gankDao().saveGankList(result.data)
                } else {
                    isRefreshing.postValue(false)
                }
            } else {//result is DataResult.Error
                //通知上游网络错误
                firstPageResult.postValue(result)
            }
        }
    }

    suspend fun getMoreGanks(page: Int = nextPage, count: Int = PAGE_SIZE){
        viewModelScope.launch(Dispatchers.IO){
            val result = ganksRepository.moreGanks(type, page, count)
            moreGanksResult.postValue(result)
        }
    }
}