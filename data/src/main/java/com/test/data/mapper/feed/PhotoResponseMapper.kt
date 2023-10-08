package com.test.data.mapper.feed

import com.test.data.mapper.BaseMapper
import com.test.data.model.feed.FeedImageEntity
import com.test.data.model.feed.response.FeedResponseModel

class PhotoResponseMapper :
    BaseMapper.Mapper<FeedResponseModel.PhotosModel.PhotoModel, FeedImageEntity>() {

    // we need id so if null throw error
    override fun map(from: FeedResponseModel.PhotosModel.PhotoModel): FeedImageEntity =
        FeedImageEntity(
            title = from.title.orEmpty(),
            id = from.id!!,
            secret = from.secret.orEmpty(),
            server = from.server.orEmpty()
        )

    override fun reverseMap(to: FeedImageEntity): FeedResponseModel.PhotosModel.PhotoModel =
        FeedResponseModel.PhotosModel.PhotoModel(
            title = to.title,
            id = to.id
        )


}