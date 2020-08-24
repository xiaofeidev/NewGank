package com.solo.utils

import android.util.Log
import com.github.xiaofeidev.base.BuildConfig
import com.tencent.mmkv.MMKV

/**
 * @author xiaofei_dev
 * @desc 读写 SP 存储项的基础工具类
 */
object SPUtils {
     fun <T> putValue(key: String, value: T) {
        when (value) {
            is Int -> MMKV.defaultMMKV().encode(key, value as Int)
            is String -> MMKV.defaultMMKV().encode(key, value as String)
            is Long -> MMKV.defaultMMKV().encode(key, value as Long)
            is Float -> MMKV.defaultMMKV().encode(key, value as Float)
            is Boolean -> MMKV.defaultMMKV().encode(key, value as Boolean)
            is Double -> MMKV.defaultMMKV().encode(key, value as Double)
        }
         if (BuildConfig.DEBUG) {
             Log.d("SP==>","put key: $key ==> $value")
         }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getValue(key: String, defValue: T): T {
        val res: Any = when (defValue) {
            is String -> MMKV.defaultMMKV().decodeString(key, defValue)
            is Int -> MMKV.defaultMMKV().decodeInt(key, defValue)
            is Long -> MMKV.defaultMMKV().decodeLong(key, defValue)
            is Boolean -> MMKV.defaultMMKV().decodeBool(key, defValue)
            is Float -> MMKV.defaultMMKV().decodeFloat(key, defValue)
            is Double -> MMKV.defaultMMKV().decodeDouble(key, defValue)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }
        if (BuildConfig.DEBUG) {
            Log.d("SP==>","get key: $key ==> $res")
        }
        return res as T
    }
}