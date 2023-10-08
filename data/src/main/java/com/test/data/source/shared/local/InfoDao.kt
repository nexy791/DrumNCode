package com.test.data.source.shared.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.data.model.info.ImageInfoEntity

@Dao
interface InfoDao {

    @Query("SELECT * FROM imageinfoentity WHERE id = :id")
    suspend fun getImageInfoById(id: String): ImageInfoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveImageInfo(image: ImageInfoEntity)

}