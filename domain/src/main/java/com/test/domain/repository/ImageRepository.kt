package com.test.domain.repository

import com.test.domain.model.FeedImageModel
import com.test.domain.model.InfoImageModel

// This repo will be used to get info about images
// (title, description, etc.)
interface ImageRepository {
    suspend fun getInfo(id: String): Result<InfoImageModel>
}