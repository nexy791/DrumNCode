package com.test.testfordrumncode.di

import com.test.data.mapper.BaseMapper
import com.test.data.mapper.feed.FeedImageMapper
import com.test.data.mapper.feed.PhotoResponseMapper
import com.test.data.mapper.info.InfoImageMapper
import com.test.data.mapper.info.InfoResponseMapper
import com.test.data.model.feed.FeedImageEntity
import com.test.data.model.feed.response.FeedResponseModel
import com.test.data.model.info.ImageInfoEntity
import com.test.data.model.info.response.InfoResponseModel
import com.test.domain.model.FeedImageModel
import com.test.domain.model.InfoImageModel
import com.test.testfordrumncode.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mapperModule = module {

    factory<BaseMapper<FeedImageEntity, FeedImageModel>>(
        named(FEED_IMAGE_MAPPER)
    ) { FeedImageMapper(BuildConfig.IMAGE_BASE_URL) }

    factory<BaseMapper<FeedResponseModel.PhotosModel.PhotoModel, FeedImageEntity>>(
        named(PHOTO_RESPONSE_MAPPER)
    ) { PhotoResponseMapper() }

    factory<BaseMapper<InfoResponseModel.PhotoModel, ImageInfoEntity>>(
        named(INFO_RESPONSE_MAPPER)
    ) { InfoResponseMapper() }

    factory<BaseMapper<ImageInfoEntity, InfoImageModel>>(
        named(INFO_IMAGE_MAPPER)
    ) { InfoImageMapper(BuildConfig.IMAGE_BASE_URL) }

}

const val FEED_IMAGE_MAPPER = "FEED_IMAGE_MAPPER"
const val PHOTO_RESPONSE_MAPPER = "PHOTO_RESPONSE_MAPPER"

const val INFO_IMAGE_MAPPER = "INFO_IMAGE_MAPPER"
const val INFO_RESPONSE_MAPPER = "INFO_RESPONSE_MAPPER"