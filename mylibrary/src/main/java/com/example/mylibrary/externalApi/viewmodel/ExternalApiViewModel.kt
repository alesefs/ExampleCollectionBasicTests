package com.example.mylibrary.externalApi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylibrary.externalApi.interactor.ExternalApiInteractor
import com.example.mylibrary.externalApi.interactor.ExternalApiInteractorState
import kotlinx.coroutines.launch

class ExternalApiViewModel(
    private val interactor: ExternalApiInteractor
) : ViewModel() {

    private val state = MutableLiveData<ExternalApiViewModelState.GetPosts>()
    val viewState: LiveData<ExternalApiViewModelState.GetPosts> = state

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            state.value = ExternalApiViewModelState.GetPosts.ShowShimmer
            val result = interactor.getPosts()
            fetchPosts(result)
        }
    }

    private fun fetchPosts(result: ExternalApiInteractorState.GetPosts) {
        when(result) {
            is ExternalApiInteractorState.GetPosts.Success -> {
                state.value = ExternalApiViewModelState.GetPosts.StopShimmer
                state.value = ExternalApiViewModelState.GetPosts.Success(result.posts)
            }
            is ExternalApiInteractorState.GetPosts.Error -> {
                state.value = ExternalApiViewModelState.GetPosts.StopShimmer
                state.value = ExternalApiViewModelState.GetPosts.Error(result.exception)
            }
            is ExternalApiInteractorState.GetPosts.ErrorApi -> {
                state.value = ExternalApiViewModelState.GetPosts.StopShimmer
                state.value = ExternalApiViewModelState.GetPosts.ErrorApi(result.error)
            }
        }
    }
}