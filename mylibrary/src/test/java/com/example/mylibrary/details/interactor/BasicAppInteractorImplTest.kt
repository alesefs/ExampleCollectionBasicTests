package com.example.mylibrary.details.interactor

import com.example.mylibrary.details.repository.BasicAppRepository
import com.example.mylibrary.details.repository.BasicAppRepositoryState
import com.example.mylibrary.details.utils.MockDataItemsResponseModel
import com.example.mylibrary.details.utils.MocksBase
import com.example.mylibrary.details.utils.TestUtils
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class BasicAppInteractorImplTest {

    @get:Rule
    val instantTaskExecutorRule = TestUtils.instantLiveDataAndCoroutinesRules

    @Mock
    private lateinit var repository: BasicAppRepository

    private lateinit var interactor: BasicAppInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        interactor = BasicAppInteractorImpl(repository)
    }

    @Test
    fun fetchData_success_test() = runBlocking {
        val response =
            BasicAppRepositoryState.DataItems.Success(MockDataItemsResponseModel.mockDataModel())

        whenever(repository.fetchData()) doReturn response

        val result = interactor.fetchData()

        val mocked = MocksBase.MockInteractorSuccess(MocksBase.MockRepositorySuccess()).fetchData()

        Assert.assertEquals(result, mocked)
    }

    @Test
    fun fetchData_error_test() = runBlocking {
        val response =
            BasicAppRepositoryState.DataItems.Error(MockDataItemsResponseModel.mockErrorDefault())

        whenever(repository.fetchData()) doReturn response

        val result = interactor.fetchData()

        val mocked = MocksBase.MockInteractorError(MocksBase.MockRepositoryError()).fetchData()

        Assert.assertEquals(result, mocked)
    }
}