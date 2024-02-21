package com.example.mylibrary.details.utils

import com.example.mylibrary.details.interactor.BasicAppInteractor
import com.example.mylibrary.details.interactor.BasicAppInteractorState
import com.example.mylibrary.details.repository.BasicAppRepository
import com.example.mylibrary.details.repository.BasicAppRepositoryState

class MocksBase {
    class MockInteractorSuccess(private val mockRepositorySuccess: MockRepositorySuccess) :
        BasicAppInteractor {
        override suspend fun fetchData(): BasicAppInteractorState.DataItems =
            BasicAppInteractorState.DataItems.Success(MockDataItemsResponseModel.mockDataModel())
    }

    class MockRepositorySuccess : BasicAppRepository {
        override suspend fun fetchData(): BasicAppRepositoryState.DataItems =
            BasicAppRepositoryState.DataItems.Success(MockDataItemsResponseModel.mockDataModel())
    }

    class MockInteractorError(private val mockRepositoryError: MockRepositoryError) :
        BasicAppInteractor {
        override suspend fun fetchData(): BasicAppInteractorState.DataItems =
            BasicAppInteractorState.DataItems.Error(MockDataItemsResponseModel.mockErrorDefault())
    }

    class MockRepositoryError : BasicAppRepository {
        override suspend fun fetchData(): BasicAppRepositoryState.DataItems =
            BasicAppRepositoryState.DataItems.Error(MockDataItemsResponseModel.mockErrorDefault())
    }
}