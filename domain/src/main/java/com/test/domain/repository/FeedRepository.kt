package com.test.domain.repository

import com.test.domain.model.FeedImageModel
import kotlinx.coroutines.flow.Flow

// one repo, one type
// this repo could be extended to get images from the last week, month, etc.
// but not to get info about images (that would be another repo)
interface FeedRepository {

    suspend fun getImagesFromTheLastDay(): Flow<Result<List<FeedImageModel>>>

}