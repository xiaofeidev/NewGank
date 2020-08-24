package com.github.xiaofeidev.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * @author xiaofei_dev
 * @date 2020/8/17
 */
abstract class BaseFragment<T: ViewDataBinding>: Fragment() {
    //ViewDataBinding 实例
    protected lateinit var binding: T

    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<T>(inflater,layoutId, container,false).apply {
            lifecycleOwner = this@BaseFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initData()
        startObserve()
        super.onViewCreated(view, savedInstanceState)
    }

    protected abstract fun initView()
    protected abstract fun initData()
    protected abstract fun startObserve()
}