package com.example.mylibrary.details.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.RuleChain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

object TestUtils {
    val instantLiveDataAndCoroutinesRules: RuleChain
        get() = RuleChain
            .outerRule(InstantCoroutinesDispatcherRule())
            .around(InstantTaskExecutorRule())

    class InstantCoroutinesDispatcherRule : TestWatcher() {
        @ExperimentalCoroutinesApi
        override fun starting(description: Description) {
            super.starting(description)
            Dispatchers.setMain(Dispatchers.Unconfined)
        }

        @ExperimentalCoroutinesApi
        override fun finished(description: Description) {
            super.finished(description)
            Dispatchers.resetMain()
        }
    }

}
