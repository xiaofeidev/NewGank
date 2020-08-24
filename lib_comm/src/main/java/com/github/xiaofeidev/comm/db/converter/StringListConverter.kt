package com.github.xiaofeidev.comm.db.converter

import androidx.room.TypeConverter
import com.github.xiaofeidev.base.singleton.gson
import com.google.gson.reflect.TypeToken

/**
 * @author xiaofei_dev
 * @date 2020/8/16
 * Room 存取复杂对象(这里是 List)数据转换器，不可直接存复杂对象，会异常
 */
class StringListConverter {
    @TypeConverter
    fun list2json(list: List<String>) = gson.toJson(list)

    @TypeConverter
    fun json2list(json: String): List<String>{
        val listType = object : TypeToken<List<String>>(){}.type
        return gson.fromJson(json, listType)
    }
}