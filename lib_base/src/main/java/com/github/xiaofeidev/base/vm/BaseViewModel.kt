package com.github.xiaofeidev.base.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author xiaofei_dev
 * @date 2020/8/17
 */
open class BaseViewModel: ViewModel() {
    //是否显示界面刷新
    val isRefreshing: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}