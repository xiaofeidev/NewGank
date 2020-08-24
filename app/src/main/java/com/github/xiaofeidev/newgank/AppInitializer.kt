package com.github.xiaofeidev.newgank

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.Utils
import com.github.xiaofeidev.inner.di.innerModule
import com.github.xiaofeidev.main.di.mainModule
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

/**
 * @author xiaofei_dev
 * @date 2020/8/14
 * App 启动时需要初始化的一些东西
 */
object AppInitializer {
    fun init(app: Application){
        runBlocking {
            withContext(Dispatchers.IO){
                CrashReport.initCrashReport(app, "3cc81d32d8", false);
                Utils.init(app)
                MMKV.initialize(app)
                initEventBusIndex()
            }
        }

        initARouter(app)
        initKoin(app)
    }

    //初始化阿里路由框架
    private fun initARouter(app: Application){
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(app);
    }

    //初始化 EventBus 在编译期生成的索引类，逻辑待加
    private fun initEventBusIndex(){

    }

    //依赖注入相关
    private fun initKoin(app: Application){
        startKoin {
            if (BuildConfig.DEBUG){
                androidLogger()
            }
            androidContext(app)
            modules(mainModule)
            modules(innerModule)
        }
    }
}