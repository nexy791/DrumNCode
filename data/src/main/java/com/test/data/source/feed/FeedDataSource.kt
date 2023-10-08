package com.test.data.source.feed

import com.test.data.model.feed.FeedImageEntity
import kotlinx.coroutines.flow.Flow

interface FeedDataSource {

    fun getImagesFromTheLastDay(): Flow<Result<List<FeedImageEntity>>>

}

interface MutableFeedDataSource : FeedDataSource {
    suspend fun saveImages(images: List<FeedImageEntity>)

}
