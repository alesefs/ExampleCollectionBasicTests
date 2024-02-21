package com.example.mylibrary.details.viewmodel

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mylibrary.details.interactor.BasicAppInteractorImpl
import com.example.mylibrary.details.repository.BasicAppRepositoryImpl

@RestrictTo(RestrictTo.Scope.LIBRARY)
class BasicAppViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //val api = BasicAppApi() //retrofit
        val repository = BasicAppRepositoryImpl(/*api*/)
        val interactor = BasicAppInteractorImpl(repository)

        return BasicAppViewModelImpl(interactor) as T
    }
}
