package com.test.data.source.feed

import com.test.data.model.feed.FeedImageEntity
import com.test.data.source.shared.local.FeedDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalFeedDataSource(
    private val feedDao: FeedDao,
) : MutableFeedDataSource {

    override suspend fun saveImages(images: List<FeedImageEntity>) {
        feedDao.saveImages(images)
    }

    override fun getImagesFromTheLastDay(): Flow<Result<List<FeedImageEntity>>> =
        feedDao.getAll()
            .map { if (it.isEmpty()) Result.failure(Exception("No data")) else Result.success(it) }

}