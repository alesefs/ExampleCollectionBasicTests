package com.example.mylibrary.externalApi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R
import com.example.mylibrary.externalApi.viewmodel.ExternalApiViewModel
import com.example.mylibrary.externalApi.viewmodel.ExternalApiViewModelFactory
import com.example.mylibrary.externalApi.viewmodel.ExternalApiViewModelState

class ExternalApiActivity : AppCompatActivity() {

//    lateinit var textView: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: ExternalApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_external_api)
        setViews()

//        textView = findViewById(R.id.textViewExternalApi)
//        getData()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun setViews() {
        val factory = ExternalApiViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[ExternalApiViewModel::class.java]

        recyclerView = findViewById(R.id.rvExternalApi)

        initObservables()
    }

    private fun initObservables() {
        viewModel.viewState.observe(this) {
            when (it) {
                is ExternalApiViewModelState.GetPosts.Success -> {
                    with(recyclerView) {
                        layoutManager = LinearLayoutManager(
                            this@ExternalApiActivity,
                            RecyclerView.VERTICAL,
                            false
                        )
                        setHasFixedSize(true)
                        adapter = ExternalApiAdapter(it.posts)
                    }
                }
                is ExternalApiViewModelState.GetPosts.Error -> {
                    Toast.makeText(
                        this,
                        "${it.exception.code} - ${it.exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is ExternalApiViewModelState.GetPosts.ErrorApi -> {
                    Toast.makeText(
                        this,
                        "${it.error}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is ExternalApiViewModelState.GetPosts.ShowShimmer -> {
                    Log.i("Alelog", "show shimmer")
                }
                is ExternalApiViewModelState.GetPosts.StopShimmer -> {
                    Log.i("Alelog", "stop shimmer")
                }
            }
        }
    }

    /*fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://jsonplaceholder.typicode.com")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<List<PostsResponseModel>> {
            override fun onFailure(call: Call<List<PostsResponseModel>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<PostsResponseModel>>, response: Response<List<PostsResponseModel>>) {
                response.body()?.forEach {
//                    textView.text = textView.text.toString().plus(it.body)
                }
            }
        })
    }*/
}