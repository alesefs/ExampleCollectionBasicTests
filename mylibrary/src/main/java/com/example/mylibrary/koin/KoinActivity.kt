package com.example.mylibrary.koin

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.mylibrary.R
import com.example.mylibrary.commons.BaseAppActivity
import com.example.mylibrary.details.viewmodel.BasicAppViewModelState
import com.example.mylibrary.koin.presenter.KoinPresenter
import com.example.mylibrary.koin.viewmodel.KoinViewModel
import com.example.mylibrary.koin.viewmodel.KoinViewModelState
import kotlinx.android.synthetic.main.activity_koin.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class KoinActivity : BaseAppActivity() {

    private val presenter: KoinPresenter by inject()
    val viewModel: KoinViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koin)

        title = "KoinActivity"
        textView.text = "${presenter.fetchTestKoinPresenter()} \n ${viewModel.fetchTestViewModel()}"

        viewModel.viewState.observe(this) {
            when (it) {
                is KoinViewModelState.DataItems.Success -> {
                    textView2.text = it.items.name
                }
                is KoinViewModelState.DataItems.Error -> {
                    textView2.text = it.exception.message
                }
            }
        }

        helloButton.setOnClickListener {
            textView.visibility = View.VISIBLE
            textView2.visibility = View.VISIBLE
        }
    }
}
