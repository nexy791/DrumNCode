package com.test.data.source.image

import com.test.common.api.handleApi
import com.test.data.mapper.BaseMapper
import com.test.data.model.info.ImageInfoEntity
import com.test.data.model.info.response.InfoResponseModel
import com.test.data.source.shared.remote.ApiService

class RemoteImageDataSource(
    private val apiService: ApiService,
    private val infoMapper: BaseMapper<InfoResponseModel.PhotoModel, ImageInfoEntity>,
) : ImageDataSource {
    override suspend fun getImageInfoById(id: String): Result<ImageInfoEntity> = handleApi {
        apiService.getImageInfo(id)
    }.map { it.photo!!.let(infoMapper::map) }

}