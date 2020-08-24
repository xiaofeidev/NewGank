package com.github.xiaofeidev.main.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.github.xiaofeidev.base.vm.BaseViewModel
import com.github.xiaofeidev.comm.db.GankDatabase
import com.github.xiaofeidev.comm.http.DataResult
import com.github.xiaofeidev.comm.model.gank.GankBean
import com.github.xiaofeidev.comm.model.type.GankTypeBean
import com.github.xiaofeidev.main.repository.GanksRepository
import com.github.xiaofeidev.main.repository.TypesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * @author xiaofei_dev
 * @date 2020/8/19
 */
class TypesViewModel(val typesRepository: TypesRepository): BaseViewModel() {
    private val types by lazy {
        typesRepository.getTypes()
    }

    var isShowType = false

    @Suppress("UNCHECKED_CAST")
    val typesResult: MutableLiveData<DataResult<List<GankTypeBean>>> by lazy {
        Transformations.map(types){
            //初次从数据库读取，列表可能为空
            DataResult.Success(it)
        } as MutableLiveData<DataResult<List<GankTypeBean>>>
    }

    suspend fun refresh(){
        isRefreshing.value = true
        viewModelScope.launch(Dispatchers.IO){
            val result = typesRepository.newTypes()

            if (result is DataResult.Success){
                //是否需要更新数据库
                if (types.value?.containsAll(result.data)?.not() ?: true){
                    GankDatabase.getInstance().gankDao().saveGanTypeList(result.data)
                }
            } else {//result is DataResult.Error
                //通知上游网络错误
                typesResult.postValue(result)
            }
        }
    }
}