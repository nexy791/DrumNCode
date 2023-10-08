package com.test.testfordrumncode.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.common.flow.Resource
import com.test.common.flow.Resource.Companion.asResource
import com.test.common.safe.SafeExt.Companion.launchSafe
import com.test.domain.model.FeedImageModel
import com.test.domain.usecase.GetFeedUseCase
import com.test.testfordrumncode.state.PositionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FeedViewModel(
    private val getFeedUseCase: GetFeedUseCase,
    private val positionState: PositionState,
) : ViewModel() {

    private val _status: MutableStateFlow<Resource<List<FeedImageModel>>> =
        MutableStateFlow(Resource.Loading)
    val status = _status.asStateFlow()

    init {
        getFeed()
    }

    fun onRetry() {
        getFeed()
    }

    fun onItemClicked(id: String) {
        positionState.setCurrentId(id)
    }

    private fun getFeed() {
        viewModelScope.launchSafe(onError = { _status.value = Resource.Error(it) }) {
            _status.value = Resource.Loading
            getFeedUseCase().collect { result ->
                _status.value = result.asResource()
                positionState.setList(result.getOrNull()?.map { it.id }.orEmpty())
            }
        }
    }

}