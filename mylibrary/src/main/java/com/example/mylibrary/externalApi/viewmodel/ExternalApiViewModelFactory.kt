package com.example.mylibrary.externalApi.viewmodel

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mylibrary.externalApi.interactor.ExternalApiInteractorImpl
import com.example.mylibrary.externalApi.repository.ExternalApiRepositoryImpl

@RestrictTo(RestrictTo.Scope.LIBRARY)
class ExternalApiViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = ExternalApiRepositoryImpl()
        val interactor = ExternalApiInteractorImpl(repository)

        return ExternalApiViewModel(interactor) as T
    }
}