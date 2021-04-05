package com.github.xiaofeidev.main.ui

import android.app.ProgressDialog
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.blankj.utilcode.util.ToastUtils
import com.github.xiaofeidev.base.ui.BaseFragment
import com.github.xiaofeidev.comm.http.DataResult
import com.github.xiaofeidev.comm.model.type.GankTypeBean
import com.github.xiaofeidev.main.R
import com.github.xiaofeidev.main.databinding.FragmentMainBinding
import com.github.xiaofeidev.main.ui.adapter.GankPageAdapter
import com.github.xiaofeidev.main.vm.TypesViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author xiaofei_dev
 * @date 2020/8/15
 */
class MainFragment: BaseFragment<FragmentMainBinding>() {
    private val viewModel by viewModel<TypesViewModel>()
    private lateinit var dialog: ProgressDialog
    private lateinit var errorView: View

    override val layoutId: Int
        get() = R.layout.fragment_main

    override fun initView() {
    }

    override fun initData() {
    }

    override fun startObserve() {
        viewModel.apply {
            isRefreshing.value = true
            isRefreshing.observe(viewLifecycleOwner){
                if (it){
                    showDialog()
                } else {
                    dismissDialog()
                }
            }

            typesResult.observe(viewLifecycleOwner){ result ->
                if (result is DataResult.Success){
                    if (result.data.isNotEmpty()){
                        initType(typeList = result.data)
                    }
                } else {
                    //网络错误
                    ToastUtils.showShort(R.string.comm_error)
                    if (!viewModel.isShowType && !::errorView.isInitialized){
                        errorView = layoutInflater.inflate(R.layout.layout_error, binding.root, true).findViewById(R.id.error)
                        errorView.findViewById<View>(R.id.btnRetry).setOnClickListener {
                            runBlocking {
                                viewModel.refresh()
                            }
                        }
                    }
                }
                dismissDialog()
            }
        }

        runBlocking {
            viewModel.refresh()
        }
    }

    private fun initType(typeList: List<GankTypeBean>){
        if (typeList.isNullOrEmpty()) return
        viewModel.isShowType = true
        if (::errorView.isInitialized && errorView.parent != null){
            (errorView.parent as ViewGroup).removeView(errorView)
        }
        val adapter = GankPageAdapter(this)
        adapter.typeList = typeList
        binding.viewPager.adapter = adapter
        for (type in typeList){
            binding.tabLayout.addTab(binding.tabLayout.newTab())
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = typeList[position].title
        }.attach()
    }

    override fun onDestroy() {
        dismissDialog()
        super.onDestroy()
    }

    private fun showDialog(){
        if (::dialog.isInitialized){
            dialog.show()
        } else {
            dialog = ProgressDialog(activity);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
            dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
            dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
            // 设置提示的title的图标，默认是没有的，如果没有设置title的话只设置Icon是不会显示图标的
            dialog.setTitle("");
            dialog.setMessage(resources.getString(R.string.comm_loading));
            dialog.show();
        }
    }

    private fun dismissDialog(){
        if (::dialog.isInitialized){
            dialog.dismiss()
        }
    }
}