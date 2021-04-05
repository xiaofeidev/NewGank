package com.github.xiaofeidev.main.ui

import android.graphics.Color
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.github.xiaofeidev.base.ui.BaseFragment
import com.github.xiaofeidev.comm.http.DataResult
import com.github.xiaofeidev.comm.router.ARouterParam
import com.github.xiaofeidev.comm.router.PAGE_WEB
import com.github.xiaofeidev.main.R
import com.github.xiaofeidev.main.databinding.FragmentGankListBinding
import com.github.xiaofeidev.main.ui.adapter.rv.GankAdapter
import com.github.xiaofeidev.main.vm.GanksViewModel
import com.yanyusong.y_divideritemdecoration.Y_Divider
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * @author xiaofei_dev
 * @date 2020/8/15
 */

class GankListFragment: BaseFragment<FragmentGankListBinding>() {
    companion object{
        const val GANK_TYPE = "GANK_TYPE"
    }

    private val ganksViewModel by viewModel<GanksViewModel>()
    lateinit var mAdapter: GankAdapter

    override val layoutId: Int
        get() = R.layout.fragment_gank_list

    override fun initView() {
        mAdapter = GankAdapter()
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            addItemDecoration(object : Y_DividerItemDecoration(context) {
                override fun getDivider(itemPosition: Int): Y_Divider? {
                    return Y_DividerBuilder()
                        .setLeftSideLine(true, Color.TRANSPARENT, 8f, 0f, 0f)
                        .setRightSideLine(true, Color.TRANSPARENT, 8f, 0f, 0f)
                        .setTopSideLine(true, Color.TRANSPARENT, 8f, 0f, 0f)
                        .setBottomSideLine(true, Color.TRANSPARENT, 0f, 0f, 0f)
                        .create()
                }
            })
        }

        mAdapter.setOnItemClickListener { adapter, view, position ->
            ARouter.getInstance().build(PAGE_WEB)
                .withString(ARouterParam.PAGE_WEB_URL, mAdapter.data[position].url)
                .withString(ARouterParam.PAGE_WEB_TITLE, mAdapter.data[position].title)
                .navigation()
        }

        mAdapter.setEmptyView(R.layout.layout_empty)
        initLoadMore()
        binding.refresh.setOnRefreshListener {
            runBlocking {
                ganksViewModel.refresh()
            }
        }
//        refresh.isRefreshing = true
    }

    override fun initData() {
        arguments?.takeIf { it.containsKey(GANK_TYPE) }?.apply {
            ganksViewModel.type = getString(GANK_TYPE) ?: ""
        }
    }

    //加载更多
    private fun initLoadMore() {
        mAdapter.loadMoreModule.setOnLoadMoreListener(OnLoadMoreListener { loadMore() })
        mAdapter.loadMoreModule.isAutoLoadMore = true
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        mAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
    }

    //请求更多数据
    private fun loadMore(){
        runBlocking {
            ganksViewModel.getMoreGanks()
        }
    }

    override fun startObserve() {
        ganksViewModel.apply {
            isRefreshing.value = true

            isRefreshing.observe(viewLifecycleOwner){
                binding.refresh.isRefreshing = it
            }

            firstPageResult.observe(viewLifecycleOwner){
                if (it is DataResult.Success){
                    val firstPage = it.data
                    if (firstPage.isNotEmpty()){
                        //加载第一页数据
                        mAdapter.setList(firstPage.toMutableList())
                        isRefreshing.value = false
                    }
                } else {
                    //网络错误
                    isRefreshing.value = false
                    ToastUtils.showShort(R.string.comm_error)
                }
            }

            moreGanksResult.observe(viewLifecycleOwner){
                if (it is DataResult.Success){
                    mAdapter.addData(it.data)
                    if (it.data.size < ganksViewModel.PAGE_SIZE){
                        mAdapter.loadMoreModule.loadMoreEnd()
                    } else {
                        mAdapter.loadMoreModule.loadMoreComplete()
                    }
                    ganksViewModel.nextPage ++
                } else {
                    //网络错误
                    mAdapter.loadMoreModule.isEnableLoadMore = true
                    mAdapter.loadMoreModule.loadMoreFail()
                }
            }
        }

        runBlocking {
            ganksViewModel.refresh()
        }
    }
}