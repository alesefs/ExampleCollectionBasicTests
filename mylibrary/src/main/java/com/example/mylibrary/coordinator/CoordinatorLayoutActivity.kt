package com.example.mylibrary.coordinator

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.mylibrary.R
import com.example.mylibrary.commons.BaseAppActivity
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.layout_activity_coordinator.view.*


class CoordinatorLayoutActivity : BaseAppActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_coordinator)

        val colapseToolbar: CollapsingToolbarLayout = findViewById(R.id.toolbar_layout)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        colapseToolbar.title = getString(R.string.title_1)//title
        toolbar.toolbar.setNavigationOnClickListener {
            onBackPressed()
            finish()
        }

        floatButtonSetUp()
    }

    private fun floatButtonSetUp() {
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val snack: Snackbar = Snackbar.make(view, getString(R.string.texto_spannable), Snackbar.LENGTH_SHORT)
            val snackBarView: View = snack.view
//            snackBarView.translationX = 20f
            snackBarView.background = ContextCompat.getDrawable(applicationContext, R.drawable.container_snackbar)
            with(snack) {
//                view.background =
//                    ContextCompat.getDrawable(applicationContext, R.drawable.container_snackbar)
                setBackgroundTint(ContextCompat.getColor(context, R.color.blue))
                setAction("Action", null)
                //animationMode = ANIMATION_MODE_FADE//ANIMATION_MODE_SLIDE (default)
                show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_CANCELED)
    }
}