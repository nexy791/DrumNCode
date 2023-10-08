package com.test.data.source.image

import com.test.data.model.info.ImageInfoEntity
import com.test.data.source.shared.local.InfoDao

class LocalImageDataSource(
    private val infoDao: InfoDao,
) : MutableImageDataSource {
    override suspend fun saveImageInfo(image: ImageInfoEntity) {
        infoDao.saveImageInfo(image)
    }

    override suspend fun getImageInfoById(id: String): Result<ImageInfoEntity> =
        infoDao.getImageInfoById(id)?.let { Result.success(it) }
            ?: Result.failure(Exception("No data"))

}