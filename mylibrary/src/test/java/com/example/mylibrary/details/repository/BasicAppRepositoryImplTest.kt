package com.example.mylibrary.details.repository

import com.example.mylibrary.details.utils.TestUtils
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BasicAppRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = TestUtils.instantLiveDataAndCoroutinesRules

//    @Mock
//    private lateinit var api: BasicAppApi

    private lateinit var repository: BasicAppRepository

    @Before
    fun setUp() {
        repository = BasicAppRepositoryImpl()
    }

    @Test
    fun fetchItem_success_test() = runBlocking {
        val result = repository.fetchData()

        Assert.assertNotNull(result)
    }

    @Test
    fun fetchItem_error_test() = runBlocking {
        val result = repository.fetchData()

        Assert.assertNotNull(result)
    }

}