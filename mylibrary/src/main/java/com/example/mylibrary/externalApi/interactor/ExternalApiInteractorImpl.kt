package com.example.mylibrary.externalApi.interactor

import com.example.mylibrary.commons.BaseException
import com.example.mylibrary.externalApi.repository.ExternalApiRepository
import com.example.mylibrary.externalApi.repository.ExternalApiRepositoryState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExternalApiInteractorImpl (
    private val repository: ExternalApiRepository
) : BaseException(), ExternalApiInteractor {

    override suspend fun getPosts(): ExternalApiInteractorState.GetPosts {
        return withContext(Dispatchers.Default) {
            val posts = repository.getPosts()
            fetchItems(posts)
        }
    }

    private fun fetchItems(
        posts: ExternalApiRepositoryState.GetPosts
    ) : ExternalApiInteractorState.GetPosts {
        return when (posts) {
            is ExternalApiRepositoryState.GetPosts.Success -> {
                ExternalApiInteractorState.GetPosts.Success(posts.posts)
            }
            is ExternalApiRepositoryState.GetPosts.Error -> {
                ExternalApiInteractorState.GetPosts.Error(posts.exception)
            }
            is ExternalApiRepositoryState.GetPosts.ErrorApi -> {
                ExternalApiInteractorState.GetPosts.ErrorApi(posts.error)
            }
        }
    }
}