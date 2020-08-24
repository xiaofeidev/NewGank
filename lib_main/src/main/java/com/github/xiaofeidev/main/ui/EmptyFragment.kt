package com.github.xiaofeidev.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.xiaofeidev.main.R

/**
 * @author xiaofei_dev
 * @date 2020/8/15
 */
class EmptyFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }
}