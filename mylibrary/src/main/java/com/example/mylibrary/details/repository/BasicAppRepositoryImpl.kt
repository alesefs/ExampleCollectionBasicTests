package com.example.mylibrary.details.repository

import android.util.Log
import com.example.mylibrary.commons.BaseException
import com.example.mylibrary.commons.ExceptionMock
import com.example.mylibrary.models.DataItemsResponseModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BasicAppRepositoryImpl(/*private val api: BasicApi*/) : BaseException(), BasicAppRepository {

    private fun fakeApiCallStringJson(): DataItemsResponseModel {
        val json = "{\n" +
                "        \"name\": \"alessandro emanuel\",\n" +
                "        \"age\": 32,\n" +
                "        \"mail\": \"aefs@gmail.com\",\n" +
                "        \"phone\": \"81966663333\",\n" +
                "        \"favourite_color\": \"orange red\"\n" +
                "    }"

        return Gson().fromJson(json, DataItemsResponseModel::class.java)
    }

    override suspend fun fetchData(): BasicAppRepositoryState.DataItems {
        val response = fakeApiCallStringJson()

        return withContext(Dispatchers.IO) {
            try {
                //depois chamo retrofit
                //response = api.fetch()...
                Log.i("ALELOG repository", response.name)
                BasicAppRepositoryState.DataItems.Success(response)
            } catch (e: ExceptionMock) {
                BasicAppRepositoryState.DataItems.Error(analyseBackEndException(e))
            }
        }
    }
}