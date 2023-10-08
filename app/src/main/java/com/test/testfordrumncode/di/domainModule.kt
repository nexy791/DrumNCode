package com.test.testfordrumncode.di

import com.test.domain.usecase.GetFeedUseCase
import com.test.domain.usecase.GetInfoUseCase
import org.koin.dsl.module

val domainDi = module {

    factory<GetFeedUseCase> {
        GetFeedUseCase.Base(
            feedRepository = get()
        )
    }

    factory<GetInfoUseCase> {
        GetInfoUseCase.Base(
            imageRepository = get()
        )
    }

}