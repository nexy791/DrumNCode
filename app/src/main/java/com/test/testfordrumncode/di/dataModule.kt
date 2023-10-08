package com.test.testfordrumncode.di

import com.test.data.repository.FeedRepositoryImpl
import com.test.data.repository.ImageRepositoryImpl
import com.test.data.source.feed.FeedDataSource
import com.test.data.source.feed.LocalFeedDataSource
import com.test.data.source.feed.MutableFeedDataSource
import com.test.data.source.feed.RemoteFeedDataSource
import com.test.data.source.image.ImageDataSource
import com.test.data.source.image.LocalImageDataSource
import com.test.data.source.image.MutableImageDataSource
import com.test.data.source.image.RemoteImageDataSource
import com.test.domain.repository.FeedRepository
import com.test.domain.repository.ImageRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single<MutableFeedDataSource> {
        LocalFeedDataSource(
            feedDao = get()
        )
    }

    single<FeedDataSource> {
        RemoteFeedDataSource(
            apiService = get(),
            photoMapper = get(named(PHOTO_RESPONSE_MAPPER))
        )
    }

    single<FeedRepository> {
        FeedRepositoryImpl(
            remoteFeedDataSource = get(),
            localFeedDataSource = get(),
            feedMapper = get(named(FEED_IMAGE_MAPPER)),
            dispatcher = get(named(IO_DISPATCHER))
        )
    }


    single<MutableImageDataSource> {
        LocalImageDataSource(
            infoDao = get()
        )
    }

    single<ImageDataSource> {
        RemoteImageDataSource(
            apiService = get(),
            infoMapper = get(named(INFO_RESPONSE_MAPPER))
        )
    }


    single<ImageRepository> {
        ImageRepositoryImpl(
            remoteImageDataSource = get(),
            localImageDataSource = get(),
            infoMapper = get(named(INFO_IMAGE_MAPPER)),
            dispatcher = get(named(IO_DISPATCHER))
        )
    }

}
