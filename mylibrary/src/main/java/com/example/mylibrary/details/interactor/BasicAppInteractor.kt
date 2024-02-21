package com.example.mylibrary.details.interactor

interface BasicAppInteractor {
    suspend fun fetchData() : BasicAppInteractorState.DataItems
}
