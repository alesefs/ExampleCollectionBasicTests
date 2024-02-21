package com.example.mylibrary.koin.presenter

import android.util.Log
import com.example.mylibrary.commons.BaseException
import com.example.mylibrary.commons.BasicAppException
import com.example.mylibrary.models.DataItemsResponseModel
import com.example.mylibrary.koin.repository.KoinRepository
import com.example.mylibrary.koin.repository.KoinRepositoryState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object KoinPresenterState {
    sealed class DataItems {
        data class Success(val items: DataItemsResponseModel) : DataItems()
        data class Error(val exception: BasicAppException) : DataItems()
    }
}

interface KoinPresenter {
    fun fetchTestKoinPresenter(): String
    suspend fun fetchFakeApi(): KoinPresenterState.DataItems

}

class KoinPresenterImpl(
    val repository: KoinRepository
) : BaseException(), KoinPresenter {
    override fun fetchTestKoinPresenter(): String =
        "${repository.getTestKoinRepository()} from KoinPresenter"

    override suspend fun fetchFakeApi(): KoinPresenterState.DataItems {
        return withContext(Dispatchers.Default) {
            val dataItems = repository.fetchFakeApi()
            fetchItems(dataItems)
        }
    }

    private fun fetchItems(
        dataItems: KoinRepositoryState.DataItems
    ) : KoinPresenterState.DataItems {
        return when (dataItems) {
            is KoinRepositoryState.DataItems.Success -> {
                Log.i("ALELOG interactor", dataItems.items.name)
                KoinPresenterState.DataItems.Success(dataItems.items)
            }
            is KoinRepositoryState.DataItems.Error -> {
                KoinPresenterState.DataItems.Error(dataItems.exception)
            }
        }
    }
}
