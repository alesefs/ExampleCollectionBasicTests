package com.example.mylibrary.fragmentdata

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R
import com.example.mylibrary.commons.BaseAppActivity
import com.example.mylibrary.commons.Constants
import com.example.mylibrary.fragmentdata.fragments.FragmentPage1
import com.example.mylibrary.fragmentdata.fragments.FragmentPage2
import com.example.mylibrary.models.MockDataModel
import kotlinx.android.synthetic.main.layout_button_custom.view.*
import timber.log.Timber

class FragmentDataActivity : BaseAppActivity() {

    companion object {
        fun getStartIntent(
            context: Context,
            data: MockDataModel
        ): Intent = Intent(context, FragmentDataActivity::class.java).apply {
            putExtra(Constants.EXTRA_DATA, data as Parcelable)
        }
    }

    private lateinit var data: MockDataModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var clMainMenu: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retriveIntentData()
        setContentView(R.layout.layout_fragment_data)

        setupViews()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putParcelable(Constants.EXTRA_DATA_RETRIVE, data)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        data = savedInstanceState.getParcelable(Constants.EXTRA_DATA_RETRIVE)!!
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun retriveIntentData() {
        data = intent.getParcelableExtra(Constants.EXTRA_DATA)!!
        //setTheme()
    }

    private fun setupViews() {
        loadFragment(FragmentPage1(data))
        var fragment: Fragment

        val btn1 = findViewById<ConstraintLayout>(R.id.button_fragm_1)
        btn1.txt_btn.text = "Page 1"
        btn1.setOnClickListener {
            fragment = FragmentPage1(data)
            loadFragment(fragment)

            Timber.tag("ALETimber").i("page 1")
            return@setOnClickListener
        }

        val btn2 = findViewById<ConstraintLayout>(R.id.button_fragm_2)
        btn2.txt_btn.text = "Page 2"
        btn2.setOnClickListener {
            fragment = FragmentPage2()

            val bundle = Bundle()
            bundle.putParcelable(Constants.EXTRA_DATA, data)
            fragment.arguments = bundle

            loadFragment(fragment)

            Timber.tag("ALETimber").i("page 2")
            return@setOnClickListener
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        with(transaction) {
            replace(R.id.frame_container, fragment)
            addToBackStack(null)
            commit()
        }
    }
}
