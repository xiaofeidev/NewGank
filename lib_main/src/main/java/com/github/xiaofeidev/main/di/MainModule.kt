package com.github.xiaofeidev.main.di

import com.github.xiaofeidev.main.repository.*
import com.github.xiaofeidev.main.vm.GanksViewModel
import com.github.xiaofeidev.main.vm.GirlsViewModel
import com.github.xiaofeidev.main.vm.MainViewModel
import com.github.xiaofeidev.main.vm.TypesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author xiaofei_dev
 * @date 2020/8/17
 * 需要依赖注入的类实例，在此声明一个 Koin Module
 */
val mainModule = module {
//    viewModel { MainViewModel(get()) }
//    viewModel { GankListViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { TypesViewModel(get()) }
    viewModel { GanksViewModel(get()) }
    viewModel { GirlsViewModel(get()) }
    // MainRepository 单例
    single<MainRepository> { MainRepositoryImpl() }
    single<GankListRepository> { GankListRepositoryImpl() }
    single<GanksRepository> { GanksRepository() }
    single<TypesRepository> { TypesRepository() }
    single<GirlsRepository> { GirlsRepository() }
}