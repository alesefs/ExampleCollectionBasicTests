package com.example.mylibrary.details.viewmodel

import androidx.lifecycle.Observer
import com.example.mylibrary.details.interactor.BasicAppInteractor
import com.example.mylibrary.details.interactor.BasicAppInteractorState
import com.example.mylibrary.details.repository.BasicAppRepository
import com.example.mylibrary.details.repository.BasicAppRepositoryState
import com.example.mylibrary.details.utils.MockDataItemsResponseModel
import com.example.mylibrary.details.utils.MocksBase
import com.example.mylibrary.details.utils.TestUtils
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BasicAppViewModelImpltest {

    @get:Rule
    val instantTaskExecutorRule = TestUtils.instantLiveDataAndCoroutinesRules

    private lateinit var viewModel: BasicAppViewModelImpl

    @Mock
    private lateinit var state: Observer<BasicAppViewModelState.DataItems>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun fetchItem_success_test() = runBlocking {
        viewModel = BasicAppViewModelImpl(
            MocksBase.MockInteractorSuccess(MocksBase.MockRepositorySuccess())
        )

        viewModel.viewState.observeForever(state)

        viewModel.fetchData()

        Mockito.verify(state).onChanged(
            BasicAppViewModelState.DataItems.ShowShimmer
        )

        Mockito.verify(state).onChanged(
            BasicAppViewModelState.DataItems.StopShimmer
        )

        Mockito.verify(state).onChanged(
            BasicAppViewModelState.DataItems.Success(
                MockDataItemsResponseModel.mockDataModel()
            )
        )
    }

    @Test
    fun fetchItem_error_test() = runBlocking {
        viewModel = BasicAppViewModelImpl(
            MocksBase.MockInteractorError(MocksBase.MockRepositoryError())
        )

        viewModel.viewState.observeForever(state)

        viewModel.fetchData()

        Mockito.verify(state).onChanged(
            BasicAppViewModelState.DataItems.ShowShimmer
        )

        Mockito.verify(state).onChanged(
            BasicAppViewModelState.DataItems.StopShimmer
        )

        Mockito.verify(state).onChanged(
            BasicAppViewModelState.DataItems.Error(
                MockDataItemsResponseModel.mockErrorDefault()
            )
        )
    }

}
