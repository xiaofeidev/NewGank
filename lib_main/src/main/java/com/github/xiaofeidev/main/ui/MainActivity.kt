package com.github.xiaofeidev.main.ui

import android.app.ProgressDialog
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.ToastUtils
import com.github.xiaofeidev.base.ui.BaseActivity
import com.github.xiaofeidev.main.R
import com.github.xiaofeidev.main.databinding.ActivityMainBinding
import com.github.xiaofeidev.main.ui.adapter.MainPageAdapter
import com.github.xiaofeidev.main.vm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel by viewModel<MainViewModel>()

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun perSetContent(){
        setTheme(R.style.AppTheme);
    }

    override fun initView() {
        viewModel.title.value = resources.getString(R.string.page_gank)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)

        binding.viewPager.adapter = MainPageAdapter(this)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val menuItem = binding.navigationBottom.menu.getItem(position)
                menuItem.setChecked(true)
                viewModel.title.value = menuItem.title.toString()
            }
        })

        binding.navigationBottom.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.page_gank -> {
                    binding.viewPager.currentItem = 0
                    viewModel.title.value = menuItem.title.toString()
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {//R.id.girl
                    binding.viewPager.currentItem = 1
                    viewModel.title.value = menuItem.title.toString()
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }

    override fun initData() {
        binding.viewModel = viewModel
    }

    override fun startObserve() {
        viewModel.apply {
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                ToastUtils.showShort(R.string.comm_planning)
            }
            R.id.action_about -> {
                ToastUtils.showShort(R.string.comm_planning)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}