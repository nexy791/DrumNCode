package com.test.data.source.feed

import com.test.common.api.handleApiFlow
import com.test.data.mapper.BaseMapper
import com.test.data.model.feed.FeedImageEntity
import com.test.data.model.feed.response.FeedResponseModel
import com.test.data.source.shared.remote.ApiService
import com.test.data.utils.FlowExt.Companion.mapResult
import kotlinx.coroutines.flow.Flow

class RemoteFeedDataSource(
    private val apiService: ApiService,
    private val photoMapper: BaseMapper<FeedResponseModel.PhotosModel.PhotoModel, FeedImageEntity>,
) : FeedDataSource {

    override fun getImagesFromTheLastDay(): Flow<Result<List<FeedImageEntity>>> = handleApiFlow {
        apiService.getImagesFromTheLastDay()
    }.mapResult { it.photos?.photo.orEmpty().map(photoMapper::map) }

}