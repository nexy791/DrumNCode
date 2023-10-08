package com.test.data.repository

import com.test.data.mapper.BaseMapper
import com.test.data.model.feed.FeedImageEntity
import com.test.data.source.feed.FeedDataSource
import com.test.data.source.feed.MutableFeedDataSource
import com.test.data.utils.FlowExt.Companion.mapResult
import com.test.domain.model.FeedImageModel
import com.test.domain.repository.FeedRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext

class FeedRepositoryImpl(
    private val remoteFeedDataSource: FeedDataSource,
    private val localFeedDataSource: MutableFeedDataSource,
    private val feedMapper: BaseMapper<FeedImageEntity, FeedImageModel>,
    private val dispatcher: CoroutineDispatcher,
) : FeedRepository {

    override suspend fun getImagesFromTheLastDay(): Flow<Result<List<FeedImageModel>>> =
        withContext(dispatcher) {
            val localFlow = localFeedDataSource.getImagesFromTheLastDay()
                .mapResult { it.map(feedMapper::map) }
            val remoteFlow = remoteFeedDataSource.getImagesFromTheLastDay()
                .mapResult { it.map(feedMapper::map) }

            remoteFlow.combine(localFlow) { remoteResult, localResult ->
                remoteResult.onSuccess {
                    localFeedDataSource.saveImages(feedMapper.reverseMap(it))
                }
                if (localResult.isSuccess) localResult
                else remoteResult
            }
        }
}