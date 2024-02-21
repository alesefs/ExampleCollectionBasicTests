package com.example.mylibrary.details

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R
import com.example.mylibrary.commons.BaseAppActivity
import com.example.mylibrary.commons.Constants
import com.example.mylibrary.commons.Utils
import com.example.mylibrary.models.DataItemsResponseModel
import com.example.mylibrary.details.viewmodel.BasicAppViewModelFactory
import com.example.mylibrary.details.viewmodel.BasicAppViewModelImpl
import com.example.mylibrary.details.viewmodel.BasicAppViewModelState
import com.example.mylibrary.models.MockDataModel
import com.example.mylibrary.routers.AppRouter
import timber.log.Timber

class AppDetailsDataActivity : BaseAppActivity() {

    companion object {
        fun getStartIntent(
            context: Context,
            data: MockDataModel
        ): Intent = Intent(context, AppDetailsDataActivity::class.java).apply {
            putExtra(Constants.EXTRA_DATA, data as Parcelable)
        }
    }

    private lateinit var data: MockDataModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: BasicAppViewModelImpl
    private lateinit var name: String
    private var age: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retriveIntentData()
        setContentView(R.layout.layout_details_data)

        setViews()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putParcelable(Constants.EXTRA_DATA_RETRIVE, data)
        Log.i("alelog save data", "$data")
//        outState.putString(Constants.EXTRA_NAME_RETRIVE, name)
//        outState.putInt(Constants.EXTRA_AGE_RETRIVE, age)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        data = savedInstanceState.getParcelable(Constants.EXTRA_DATA_RETRIVE)!!
//        name = savedInstanceState.getString(Constants.EXTRA_NAME_RETRIVE)!!
//        age = savedInstanceState.getInt(Constants.EXTRA_AGE_RETRIVE)

        when {
            savedInstanceState.containsKey(Constants.EXTRA_DATA_RETRIVE) -> {
                data = savedInstanceState.get(Constants.EXTRA_DATA_RETRIVE) as MockDataModel
            }
            /*savedInstanceState.containsKey(Constants.EXTRA_NAME_RETRIVE) -> {
                name = savedInstanceState.get(Constants.EXTRA_NAME_RETRIVE) as String
            }
            savedInstanceState.containsKey(Constants.EXTRA_AGE_RETRIVE) -> {
                age = savedInstanceState.get(Constants.EXTRA_AGE_RETRIVE) as Int
            }*/
        }

        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun retriveIntentData() {
//        data = intent.getParcelableExtra(Constants.EXTRA_DATA)!!
        intent.extras?.let { bundle ->
            if (bundle.containsKey(Constants.EXTRA_DATA)) {
                data = bundle.get(Constants.EXTRA_DATA) as MockDataModel
            }
        }

        val config = resources.configuration
        when (config.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                setTheme()
            } // Night mode is not active, we're using the light theme
            Configuration.UI_MODE_NIGHT_YES -> {
                setThemeDark()
            } // Night mode is active, we're using dark theme
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun setViews() {
        val factory = BasicAppViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(BasicAppViewModelImpl::class.java)

        recyclerView = findViewById(R.id.rv_details)

        initObservables()

        setSpannableText()
    }

    private fun initObservables() {
        viewModel.viewState.observe(this) {
            when (it) {
                is BasicAppViewModelState.DataItems.Success -> {
                    with(recyclerView) {
                        layoutManager = LinearLayoutManager(
                            this@AppDetailsDataActivity,
                            RecyclerView.VERTICAL,
                            false
                        )
                        setHasFixedSize(true)
                        adapter = DetailsAdapter(detailsItems(it.items))
                    }

                    name = it.items.name
                    age = it.items.age
                    Log.i("ALELog", it.items.name)
                    Timber.tag("ALETimber").i(it.items.name)
                }
                is BasicAppViewModelState.DataItems.Error -> {
                    Toast.makeText(
                        this,
                        "${it.exception.code} - ${it.exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is BasicAppViewModelState.DataItems.ShowShimmer -> {Timber.tag("ALE").i("showShimmer")}
                is BasicAppViewModelState.DataItems.StopShimmer -> {Timber.tag("ALE").i("stopShimmer")}
            }
        }

        viewModel.fetchData()
    }

    private fun detailsItems(
        items: DataItemsResponseModel
    ) : List<DetailsItemsModel> = listOfNotNull(
        DetailsItemsModel(hashMapOf(Pair("nome", items.name))),
        DetailsItemsModel(hashMapOf(Pair("idade", items.age.toString()))),
        DetailsItemsModel(hashMapOf(Pair("e-mail", items.mail))),
        DetailsItemsModel(hashMapOf(Pair("telefone", items.phone))),
            //items.phone?.let { Utils.addMask(it, Constants.PHONE_MASK) }))),
        DetailsItemsModel(hashMapOf(Pair("cor favorita", items.favouriteColor)))
    )

    private fun setSpannableText() {
        val spannableText: TextView = findViewById(R.id.spannable_details)
        val wordSpannable: Spannable = SpannableString(getString(R.string.texto_spannable))

        if (Utils.isAccessibilityEnable(this)) {
            spannableText.setOnClickListener {
                backToMainMenu()
            }
        }

        wordSpannable.setSpan(
            getLinkClickableSpan(),
            0,
            28,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        wordSpannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.blue)),
            0,
            28,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        wordSpannable.setSpan(
            UnderlineSpan(),
            0,
            28,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableText.movementMethod = LinkMovementMethod.getInstance()
        spannableText.text = wordSpannable
    }

    private fun getLinkClickableSpan(): ClickableSpan {
        return object : ClickableSpan() {
            override fun onClick(view: View) {
                backToMainMenu()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }
    }

    private fun backToMainMenu() {
        data.name = name
        data.age = age
        Log.i("ALELog", "$data")
        AppRouter.goToMainMenu(this@AppDetailsDataActivity, data)
    }
}
