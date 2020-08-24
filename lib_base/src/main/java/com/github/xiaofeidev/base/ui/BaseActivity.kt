package com.github.xiaofeidev.base.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author xiaofei_dev
 * @date 2020/8/17
 */
abstract class BaseActivity<T: ViewDataBinding>: AppCompatActivity() {
    //ViewDataBinding 实例
    protected lateinit var binding: T

    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        perSetContent()
        binding = DataBindingUtil.setContentView<T>(this, layoutId).apply {
            lifecycleOwner = this@BaseActivity
        }
        initView()
        initData()
        startObserve()
    }

    protected open fun perSetContent(){}

    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
}