package com.example.mylibrary.externalApi.repository

interface ExternalApiRepository {
    suspend fun getPosts(): ExternalApiRepositoryState.GetPosts
}