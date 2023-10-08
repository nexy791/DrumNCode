package com.test.domain.usecase

import com.test.domain.model.InfoImageModel
import com.test.domain.repository.ImageRepository

// we don't need to use flow cuz we don't need to observe this data
interface GetInfoUseCase {

    suspend operator fun invoke(id: String): Result<InfoImageModel>

    class Base(
        private val imageRepository: ImageRepository,
    ) : GetInfoUseCase {
        override suspend fun invoke(id: String): Result<InfoImageModel> =
            imageRepository.getInfo(id)
    }

}