package com.test.data.mapper.feed

import com.test.data.mapper.BaseMapper
import com.test.data.model.feed.FeedImageEntity
import com.test.domain.model.FeedImageModel

class FeedImageMapper(
    private val baseUrl: String,
) : BaseMapper.PathMapper<FeedImageEntity, FeedImageModel>() {

    companion object {
        private const val SIZE_SUFFIX = "q" // q - square 150x150
    }

    override fun map(from: FeedImageEntity): FeedImageModel =
        FeedImageModel.Base(
            id = from.id,
            title = from.title,
            server = from.server,
            secret = from.secret,
            path = getImagePath(
                base = baseUrl,
                server = from.server,
                id = from.id,
                secret = from.secret,
                sizeSuffix = SIZE_SUFFIX
            )
        )

    override fun reverseMap(to: FeedImageModel): FeedImageEntity =
        FeedImageEntity(
            id = to.id,
            title = to.title,
            secret = to.secret,
            server = to.server
        )


}