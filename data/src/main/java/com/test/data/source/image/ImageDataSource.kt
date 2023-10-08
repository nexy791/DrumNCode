package com.test.data.source.image

import com.test.data.model.info.ImageInfoEntity

interface ImageDataSource {

    suspend fun getImageInfoById(id: String): Result<ImageInfoEntity>

}

interface MutableImageDataSource : ImageDataSource {
    suspend fun saveImageInfo(image: ImageInfoEntity)

}