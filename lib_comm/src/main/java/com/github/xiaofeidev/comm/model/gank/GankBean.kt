package com.github.xiaofeidev.comm.model.gank

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.koin.ext.getScopeId

/**
 * @author xiaofei_dev
 * @date 2020/8/15
 */
@Entity
data class GankBean(
    //保存到数据库中时用的主键，需要自己构造
    @PrimaryKey
    var dbId: String,
    @SerializedName("_id")
    val id: String,
    val author: String,
    val category: String,
    val createdAt: String,
    val desc: String,
    val images: List<String>,
    val likeCounts: Int,
    val publishedAt: String,
    val stars: Int,
    val title: String,
    val type: String,
    //通过此字段跳转到干货详情页，一个 WebView
    val url: String,
    val views: Int
){
    override fun equals(other: Any?): Boolean {
        if(other == null || !(other is GankBean)) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}