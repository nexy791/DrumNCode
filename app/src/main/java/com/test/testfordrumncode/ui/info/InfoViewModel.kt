package com.test.testfordrumncode.ui.info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.common.flow.Resource
import com.test.common.flow.Resource.Companion.asResource
import com.test.common.safe.SafeExt.Companion.launchSafe
import com.test.domain.model.InfoImageModel
import com.test.domain.usecase.GetInfoUseCase
import com.test.testfordrumncode.state.PositionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InfoViewModel(
    savedState: SavedStateHandle,
    private val getInfoUseCase: GetInfoUseCase,
    private val positionState: PositionState,
) : ViewModel() {

    private val id = InfoFragmentArgs.fromSavedStateHandle(savedState).id

    private val _status: MutableStateFlow<Resource<InfoImageModel>> =
        MutableStateFlow(Resource.Loading)
    val status = _status.asStateFlow()

    private val _position: MutableStateFlow<PositionState.Position> =
        MutableStateFlow(PositionState.Position(0, 0))
    val position = _position.asStateFlow()

    init {
        getInfo(id)
        getPosition()
    }

    fun onNext() {
        val id = positionState.nextId()
        getInfo(id)
        getPosition()
    }

    fun onPrev() {
        val id = positionState.previousId()
        getInfo(id)
        getPosition()
    }

    fun onRetry() {
        val id = positionState.getId()
        getInfo(id)
        getPosition()
    }

    private fun getInfo(id: String) {
        _status.value = Resource.Loading
        viewModelScope.launchSafe(onError = { _status.value = Resource.Error(it) }) {
            val result = getInfoUseCase(id).asResource()
            _status.value = result
        }
    }

    private fun getPosition() {
        _position.value = positionState.getCurrentPosition()
    }

}