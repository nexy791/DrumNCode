package com.test.data.repository

import com.test.data.mapper.BaseMapper
import com.test.data.model.info.ImageInfoEntity
import com.test.data.source.image.ImageDataSource
import com.test.data.source.image.MutableImageDataSource
import com.test.domain.model.InfoImageModel
import com.test.domain.repository.ImageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ImageRepositoryImpl(
    private val remoteImageDataSource: ImageDataSource,
    private val localImageDataSource: MutableImageDataSource,
    private val infoMapper: BaseMapper<ImageInfoEntity, InfoImageModel>,
    private val dispatcher: CoroutineDispatcher,
) : ImageRepository {

    override suspend fun getInfo(id: String): Result<InfoImageModel> = withContext(dispatcher) {

        val remoteResult = remoteImageDataSource.getImageInfoById(id)
            .mapCatching(infoMapper::map)

        remoteResult.onSuccess {
            localImageDataSource.saveImageInfo(infoMapper.reverseMap(it))
        }

        val localResult = localImageDataSource.getImageInfoById(id)
            .mapCatching(infoMapper::map)

        if (localResult.isSuccess) localResult
        else remoteResult
    }


}