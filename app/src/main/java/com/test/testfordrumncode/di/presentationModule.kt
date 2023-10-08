package com.test.testfordrumncode.di

import com.test.testfordrumncode.state.PositionState
import com.test.testfordrumncode.ui.feed.FeedViewModel
import com.test.testfordrumncode.ui.info.InfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        FeedViewModel(
            getFeedUseCase = get(),
            positionState = get()
        )
    }

    viewModel {
        InfoViewModel(
            savedState = get(),
            getInfoUseCase = get(),
            positionState = get()
        )
    }

    single<PositionState> { PositionState.Base() }

}