package com.example.mylibrary.details.repository

interface BasicAppRepository {
    suspend fun fetchData(): BasicAppRepositoryState.DataItems
}
