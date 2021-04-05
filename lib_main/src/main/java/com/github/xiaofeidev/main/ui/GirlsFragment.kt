package com.github.xiaofeidev.main.ui

import android.graphics.Color
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.github.xiaofeidev.base.ui.BaseFragment
import com.github.xiaofeidev.comm.http.DataResult
import com.github.xiaofeidev.comm.router.ARouterParam
import com.github.xiaofeidev.comm.router.PAGE_GIRL
import com.github.xiaofeidev.comm.router.PAGE_WEB
import com.github.xiaofeidev.main.R
import com.github.xiaofeidev.main.databinding.FragmentGirlsBinding
import com.github.xiaofeidev.main.ui.adapter.rv.GirlAdapter
import com.github.xiaofeidev.main.vm.GirlsViewModel
import com.yanyusong.y_divideritemdecoration.Y_Divider
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * @author 黎曼
 * @date 2020/8/23
 */
class GirlsFragment: BaseFragment<FragmentGirlsBinding>() {
    private val girlsViewModel by viewModel<GirlsViewModel>()
    lateinit var mAdapter: GirlAdapter

    override val layoutId: Int
        get() = R.layout.fragment_girls

    override fun initView() {
        mAdapter = GirlAdapter((ScreenUtils.getScreenWidth() / 2f * 1.5f).toInt())
        binding.recycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mAdapter
            addItemDecoration(object : Y_DividerItemDecoration(context) {
                override fun getDivider(itemPosition: Int): Y_Divider? {
                    return Y_DividerBuilder()
                        .setTopSideLine(true, Color.TRANSPARENT, 8f, 0f, 0f)
                        .create()
                }
            })
        }
        mAdapter.setOnItemClickListener { adapter, view, position ->
            val act = activity ?: return@setOnItemClickListener
            val optionsCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(act, view, "simple transition name"
                )
            ARouter.getInstance().build(PAGE_GIRL)
                .withOptionsCompat(optionsCompat)
                .withString(ARouterParam.PAGE_WEB_URL, mAdapter.data[position].url)
                .navigation(act)
        }
        mAdapter.setEmptyView(R.layout.layout_empty)
        initLoadMore()
        binding.refresh.setOnRefreshListener {
            runBlocking {
                girlsViewModel.refresh()
            }
        }
    }

    override fun initData() {
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
            girlsViewModel.getMoreGirls()
        }
    }

    override fun startObserve() {
        girlsViewModel.apply {
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
                    if (it.data.size < girlsViewModel.PAGE_SIZE){
                        mAdapter.loadMoreModule.loadMoreEnd()
                    } else {
                        mAdapter.loadMoreModule.loadMoreComplete()
                    }
                    girlsViewModel.nextPage ++
                } else {
                    //网络错误
                    mAdapter.loadMoreModule.isEnableLoadMore = true
                    mAdapter.loadMoreModule.loadMoreFail()
                }
            }
        }

        runBlocking {
            girlsViewModel.refresh()
        }
    }
}