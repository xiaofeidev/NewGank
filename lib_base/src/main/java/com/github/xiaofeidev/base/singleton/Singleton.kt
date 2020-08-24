package com.github.xiaofeidev.base.singleton

import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.bumptech.glide.request.RequestOptions
import com.github.xiaofeidev.base.R
import com.google.gson.Gson

/**
 * @author xiaofei_dev
 * @date 2020/8/16
 */
val gson = Gson()

val requestOptions = RequestOptions().apply{
    centerCrop()
    placeholder(R.drawable.ic_place)
    error(R.drawable.ic_place)
}