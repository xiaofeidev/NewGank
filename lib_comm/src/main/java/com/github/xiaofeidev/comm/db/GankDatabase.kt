package com.github.xiaofeidev.comm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.blankj.utilcode.util.Utils
import com.github.xiaofeidev.comm.db.converter.StringListConverter
import com.github.xiaofeidev.comm.db.dao.GankDao
import com.github.xiaofeidev.comm.model.gank.GankBean
import com.github.xiaofeidev.comm.model.type.GankTypeBean

/**
 * @author xiaofei_dev
 * @date 2020/8/16
 */
const val DB_NAME = "GANK"

@Database(entities = arrayOf(GankTypeBean::class, GankBean::class), version = 1)
@TypeConverters(StringListConverter::class)
abstract class GankDatabase : RoomDatabase() {
    abstract fun gankDao(): GankDao

    companion object{
        @Volatile private var instance: GankDatabase? = null

        fun getInstance(): GankDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase().also { instance = it }
            }
        }

        private fun buildDatabase(): GankDatabase{
            return Room.databaseBuilder(
                Utils.getApp().applicationContext,
                GankDatabase::class.java, DB_NAME
            ).build()
        }
    }
}