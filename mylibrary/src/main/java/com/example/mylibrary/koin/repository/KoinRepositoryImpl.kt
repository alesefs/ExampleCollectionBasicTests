package com.example.mylibrary.koin.repository

import android.util.Log
import com.example.mylibrary.commons.BaseException
import com.example.mylibrary.commons.BasicAppException
import com.example.mylibrary.commons.ExceptionMock
import com.example.mylibrary.models.DataItemsResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object KoinRepositoryState {
    sealed class DataItems {
        data class Success(val items: DataItemsResponseModel) : DataItems()
        data class Error(val exception: BasicAppException) : DataItems()
    }
}

interface KoinRepository {
    fun fetchTestKoinRepository(): String
    fun getTestKoinRepository(): String
    suspend fun fetchFakeApi(): KoinRepositoryState.DataItems
}

class KoinRepositoryImpl() : BaseException(), KoinRepository {

    private fun fakeApiCall(): DataItemsResponseModel = DataItemsResponseModel(
        name = "alessandro emanuel",
        age = 32,
        mail = "aefs@gmail.com",
        phone = "81966663333",
        favouriteColor = "orange red"
    )

    override fun fetchTestKoinRepository(): String = "test koin"
    override fun getTestKoinRepository(): String = "get API"
    override suspend fun fetchFakeApi(): KoinRepositoryState.DataItems {
        val response = fakeApiCall()

        return withContext(Dispatchers.IO) {
            try {
                Log.i("ALELOG repository", response.name)
                KoinRepositoryState.DataItems.Success(response)
            } catch (e: ExceptionMock) {
                KoinRepositoryState.DataItems.Error(analyseBackEndException(e))
            }
        }
    }
}
