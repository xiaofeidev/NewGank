package com.github.xiaofeidev.comm.http

/**
 * @author xiaofei_dev
 * @date 2020/8/18
 * 数据请求结果的封装类
 */
sealed class DataResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : DataResult<T>()
    data class Error(val exception: Exception) : DataResult<Nothing>()
}