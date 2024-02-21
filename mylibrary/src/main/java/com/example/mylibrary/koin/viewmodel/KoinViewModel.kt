package com.example.mylibrary.koin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylibrary.commons.BasicAppException
import com.example.mylibrary.models.DataItemsResponseModel
import com.example.mylibrary.koin.presenter.KoinPresenter
import com.example.mylibrary.koin.presenter.KoinPresenterState
import com.example.mylibrary.koin.repository.KoinRepository
import kotlinx.coroutines.launch

object KoinViewModelState {
    sealed class DataItems {
        data class Success(val items: DataItemsResponseModel) : DataItems()
        data class Error(val exception: BasicAppException) : DataItems()
    }
}

class KoinViewModel(
    private val presenter: KoinPresenter,
    val repository: KoinRepository
) : ViewModel() {

    private val state = MutableLiveData<KoinViewModelState.DataItems>()
    val viewState: LiveData<KoinViewModelState.DataItems> = state

    init {
        fetchDataFakeApi()
    }

    fun fetchTestViewModel(): String =
        "${repository.fetchTestKoinRepository()} from KoinViewModel"

    private fun fetchDataFakeApi() {
        viewModelScope.launch {
            val result = presenter.fetchFakeApi()
            fetchItem(result)
        }
    }

    private fun fetchItem(result: KoinPresenterState.DataItems) {
        when (result) {
            is KoinPresenterState.DataItems.Success -> {
                Log.i("ALELOG viewmodel", result.items.name)
                state.value = KoinViewModelState.DataItems.Success(result.items)
            }
            is KoinPresenterState.DataItems.Error -> {
                state.value = KoinViewModelState.DataItems.Error(result.exception)
            }
        }
    }
}
