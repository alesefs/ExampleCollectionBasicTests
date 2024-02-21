package com.example.mylibrary.commons.network

import com.example.mylibrary.commons.Constants
import com.example.mylibrary.externalApi.repository.models.PostsResponseModel
import retrofit2.Response
import retrofit2.http.*

interface Endpoint {
//    @GET("posts")
//    fun getPosts() : Call<List<PostsResponseModel>>

    @GET(Constants.Path.GET_POSTS_HEADER)
    suspend fun getPosts() : Response<List<PostsResponseModel>>

    @GET(Constants.Path.GET_POSTS_HEADER)
    @Headers("user-key: 9900a9720d31dfd5fdb4352700c")
    suspend fun getPosts2(
        @Path("path") path: String,
        @Query("query") query: String,
        @Body body: String
    ) : Response<List<PostsResponseModel>>
}