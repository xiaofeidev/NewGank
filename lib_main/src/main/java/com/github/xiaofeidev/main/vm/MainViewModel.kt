package com.github.xiaofeidev.main.vm

import androidx.lifecycle.MutableLiveData
import com.github.xiaofeidev.base.vm.BaseViewModel
import com.github.xiaofeidev.comm.http.DataResult
import com.github.xiaofeidev.comm.model.gank.GankBean

/**
 * @author 黎曼
 * @date 2020/8/23
 */
class MainViewModel: BaseViewModel() {
    val title: MutableLiveData<String> = MutableLiveData<String>()
}