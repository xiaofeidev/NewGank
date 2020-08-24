package com.github.xiaofeidev.inner.di

import com.github.xiaofeidev.inner.vm.GirlViewModel
import com.github.xiaofeidev.inner.vm.WebViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author 黎曼
 * @date 2020/8/23
 */
val innerModule = module {
    viewModel { WebViewModel() }
    viewModel { GirlViewModel() }
}