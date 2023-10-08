package com.test.domain.usecase

import com.test.domain.model.FeedImageModel
import com.test.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow

interface GetFeedUseCase {

     suspend operator fun invoke(): Flow<Result<List<FeedImageModel>>>

    class Base(
        private val feedRepository: FeedRepository,
    ) : GetFeedUseCase {

        override suspend operator fun invoke(): Flow<Result<List<FeedImageModel>>> =
            feedRepository.getImagesFromTheLastDay()
    }

}