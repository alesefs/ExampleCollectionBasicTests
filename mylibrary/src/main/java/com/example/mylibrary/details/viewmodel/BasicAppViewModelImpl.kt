package com.example.mylibrary.details.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylibrary.details.interactor.BasicAppInteractor
import com.example.mylibrary.details.interactor.BasicAppInteractorState
import kotlinx.coroutines.launch

class BasicAppViewModelImpl(
    private val interactor: BasicAppInteractor
) : ViewModel() {

    private val state = MutableLiveData<BasicAppViewModelState.DataItems>()
    val viewState: LiveData<BasicAppViewModelState.DataItems> = state

//    init {
//        fetchData()
//    }

    fun fetchData() {
        viewModelScope.launch {
            state.value = BasicAppViewModelState.DataItems.ShowShimmer
            val result = interactor.fetchData()
            fetchItem(result)
        }
    }

    private fun fetchItem(result: BasicAppInteractorState.DataItems) {
        when (result) {
            is BasicAppInteractorState.DataItems.Success -> {
                Log.i("ALELOG viewmodel", result.items.name)
                state.value = BasicAppViewModelState.DataItems.StopShimmer
                state.value = BasicAppViewModelState.DataItems.Success(result.items)
            }
            is BasicAppInteractorState.DataItems.Error -> {
                state.value = BasicAppViewModelState.DataItems.StopShimmer
                state.value = BasicAppViewModelState.DataItems.Error(result.exception)
            }
        }
    }
}