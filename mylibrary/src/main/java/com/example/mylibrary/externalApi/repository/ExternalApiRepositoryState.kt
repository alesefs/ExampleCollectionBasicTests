package com.example.mylibrary.externalApi.repository

import com.example.mylibrary.commons.BasicAppException
import com.example.mylibrary.externalApi.repository.models.PostModel
import okhttp3.ResponseBody

object ExternalApiRepositoryState {
    sealed class GetPosts {
        data class Success(val posts: List<PostModel>) : GetPosts()
        data class Error(val exception: BasicAppException) : GetPosts()
        data class ErrorApi(val error: ResponseBody?) : GetPosts()
    }
}