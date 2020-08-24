package com.github.xiaofeidev.main.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.xiaofeidev.comm.model.type.GankTypeBean
import com.github.xiaofeidev.main.ui.GankListFragment

/**
 * @author xiaofei_dev
 * @date 2020/8/15
 */
class GankPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    lateinit var typeList: List<GankTypeBean>

    override fun getItemCount(): Int {
        return typeList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = GankListFragment()
        fragment.arguments = Bundle().apply {
            putString(GankListFragment.GANK_TYPE, typeList[position].type)
        }
        return fragment
    }
}