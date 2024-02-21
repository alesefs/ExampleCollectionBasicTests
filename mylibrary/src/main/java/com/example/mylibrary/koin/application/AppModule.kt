package com.example.mylibrary.koin.application

import com.example.mylibrary.koin.presenter.KoinPresenter
import com.example.mylibrary.koin.presenter.KoinPresenterImpl
import com.example.mylibrary.koin.repository.KoinRepository
import com.example.mylibrary.koin.repository.KoinRepositoryImpl
import com.example.mylibrary.koin.viewmodel.KoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

    singleOf(::KoinRepositoryImpl) { bind<KoinRepository>()}
//    single<KoinRepository> { KoinRepositoryImpl() } //classic

    factoryOf(::KoinPresenterImpl) { bind<KoinPresenter>()}
//    factory<KoinPresenter> { KoinPresenterImpl(get()) } //classic

//    caso de interactor/useCase usa factoryOf
//    Ex.: factoryOf(::KoinInteractorImpl) { bind<KoinInteractor>()}

    viewModelOf(::KoinViewModel)
//    viewModel { KoinViewModel(get()) } //classic
}