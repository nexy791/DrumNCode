package com.test.data.source.shared.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.data.model.feed.FeedImageEntity
import com.test.data.model.info.ImageInfoEntity

@Database(entities = [FeedImageEntity::class, ImageInfoEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract val feedDao: FeedDao
    abstract val infoDao: InfoDao

    companion object {
        const val DATABASE_NAME = "database-name"
    }

}