package com.github.xiaofeidev.newgank

import androidx.multidex.MultiDexApplication
import org.koin.core.context.startKoin

/**
 * @author xiaofei_dev
 * @date 2020/8/14
 */
class App: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        AppInitializer.init(this)
    }
}