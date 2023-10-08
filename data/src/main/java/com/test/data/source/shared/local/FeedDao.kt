package com.test.data.source.shared.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.test.data.model.feed.FeedImageEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FeedDao {
    @Query("SELECT * FROM feedimageentity")
    fun getAll(): Flow<List<FeedImageEntity>>

    @Query("DELETE FROM feedimageentity")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<FeedImageEntity>)

    @Transaction
    suspend fun saveImages(images: List<FeedImageEntity>) {
        deleteAll()
        insertAll(images)
    }
}