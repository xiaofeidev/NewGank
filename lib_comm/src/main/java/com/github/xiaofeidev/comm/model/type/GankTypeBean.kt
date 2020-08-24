package com.github.xiaofeidev.comm.model.type

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.xiaofeidev.comm.model.gank.GankBean
import com.google.gson.annotations.SerializedName

/**
 * 一个文章分类，需要用此一条数据去获取具体分类下的 Gank 数据列表
 */
@Entity
data class GankTypeBean(
    //保存到数据库中时用的主键，需要自己构造
    @PrimaryKey
    @SerializedName("_id")
    val id: String,
    val coverImageUrl: String,
    val desc: String,
    val title: String,
    val type: String
){
    override fun equals(other: Any?): Boolean {
        if(other == null || !(other is GankTypeBean)) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}