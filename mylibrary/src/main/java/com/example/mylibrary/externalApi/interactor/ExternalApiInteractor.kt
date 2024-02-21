package com.example.mylibrary.externalApi.interactor

interface ExternalApiInteractor {
    suspend fun getPosts(): ExternalApiInteractorState.GetPosts
}