package com.example.mylibrary.commons.network

import com.example.mylibrary.externalApi.repository.models.PostsResponseModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils(val path: String) {
    /*companion object {
        *//** Retorna uma Instância do Client Retrofit para Requisições
         * @param path Caminho Principal da API
         *//*
        fun getRetrofitInstance(path : String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }*/

    private val api: Endpoint = Retrofit.Builder()
            .baseUrl(path)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoint::class.java)

    suspend fun getPosts(): Response<List<PostsResponseModel>> = api.getPosts()
}