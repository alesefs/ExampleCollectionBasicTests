package com.example.mylibrary.externalApi.repository.models

import com.google.gson.annotations.SerializedName

data class PostsResponseModel(
    @SerializedName("userId")
    var userId : Int,
    @SerializedName("id")
    var id : Int,
    @SerializedName("title")
    var title : String,
    @SerializedName("body")
    var body : String
)
