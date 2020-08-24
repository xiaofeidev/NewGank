package com.github.xiaofeidev.main.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.xiaofeidev.main.ui.EmptyFragment
import com.github.xiaofeidev.main.ui.GirlsFragment
import com.github.xiaofeidev.main.ui.MainFragment

/**
 * @author xiaofei_dev
 * @date 2020/8/15
 */
class MainPageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        if(position == 0){
            return MainFragment()
        } else {
            return GirlsFragment()
        }
    }
}