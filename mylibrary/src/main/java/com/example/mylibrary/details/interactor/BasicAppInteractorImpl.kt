package com.example.mylibrary.details.interactor

import android.util.Log
import com.example.mylibrary.commons.BaseException
import com.example.mylibrary.details.repository.BasicAppRepository
import com.example.mylibrary.details.repository.BasicAppRepositoryState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BasicAppInteractorImpl(
    private val repository: BasicAppRepository
) : BaseException(), BasicAppInteractor {

    override suspend fun fetchData(): BasicAppInteractorState.DataItems {
        return withContext(Dispatchers.Default) {
            val dataItems = repository.fetchData()
            fetchItems(dataItems)
        }
    }

    private fun fetchItems(
        dataItems: BasicAppRepositoryState.DataItems
    ) : BasicAppInteractorState.DataItems {
        return when (dataItems) {
            is BasicAppRepositoryState.DataItems.Success -> {
                Log.i("ALELOG interactor", dataItems.items.name)
                BasicAppInteractorState.DataItems.Success(dataItems.items)
            }
            is BasicAppRepositoryState.DataItems.Error -> {
                BasicAppInteractorState.DataItems.Error(dataItems.exception)
            }
        }
    }
}