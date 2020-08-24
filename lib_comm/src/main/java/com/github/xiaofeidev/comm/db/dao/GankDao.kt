package com.github.xiaofeidev.comm.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.github.xiaofeidev.comm.model.gank.GankBean
import com.github.xiaofeidev.comm.model.type.GankTypeBean

/**
 * @author xiaofei_dev
 * @date 2020/8/16
 */
@Dao
interface GankDao {
    //插入 GankTypeBean list
    @Insert(onConflict = REPLACE)
    suspend fun saveGanTypeList(list: List<GankTypeBean>):List<Long>

    //插入 GankBean list
    @Insert(onConflict = REPLACE)
    suspend fun saveGankList(list: List<GankBean>):List<Long>

    //查询数据库中所有 GankTypeBean，注意 Room 中返回 LiveData 的方法不可声明成 suspend 的
    //且查询返回 LiveData 的话，
    //因为是异步查询，初始返回的 LiveDate 其内部的 value 会是 null
    //且 room 里声明返回 LiveData 只能返回 LiveData，
    //（其实最好编译出来的方法是返回个 RoomTrackingLiveData，
    // 是 LiveData 的直接子类，其没有向外界提供公开的 setValue 和 postValue 方法），
    // 不能返回 MutableLiveDate 和 其他
    @Query("SELECT * FROM GANKTYPEBEAN")
    fun queryGankTypeList(): LiveData<MutableList<GankTypeBean>>

    //查询数据库中的所有 GankBean，注意 Room 中返回 LiveData 的方法不可声明成 suspend 的
    //且查询返回 LiveData 的话，
    //因为是异步查询，初始返回的 LiveDate 其内部的 value 会是 null
    //且 room 里声明返回 LiveData 只能返回 LiveData，
    //（其实最好编译出来的方法是返回个 RoomTrackingLiveData，
    // 是 LiveData 的直接子类，其没有向外界提供公开的 setValue 和 postValue 方法），
    // 不能返回 MutableLiveDate 和 其他
    @Query("SELECT * FROM GANKBEAN WHERE type = :gankType")
    fun queryGankList(gankType: String): LiveData<List<GankBean>>
}