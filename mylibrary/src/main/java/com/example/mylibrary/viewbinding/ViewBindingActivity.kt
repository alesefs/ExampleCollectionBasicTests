package com.example.mylibrary.viewbinding

import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.mylibrary.R
import com.example.mylibrary.databinding.ActivityViewBindingBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


class ViewBindingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBindingBinding //nome do layout com cameoCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingBinding.inflate(layoutInflater) //initializing the binding class
        setContentView(binding.root) // we now set the contentview as the binding.root

        binding.welcome.text = "Hallo and welcome"
        binding.sendData.setOnClickListener { //troca o send_data por cameoCase
            //para aparecer no top
            val snackbar = Snackbar.make(it, getString(R.string.texto_spannable), Snackbar.LENGTH_LONG)
            val layoutParams = LinearLayout.LayoutParams(snackbar.view.layoutParams)
            layoutParams.gravity = Gravity.TOP
            snackbar.view.setPadding(0, 10, 0, 0)
            snackbar.view.layoutParams = layoutParams
            snackbar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
            snackbar.show()
        }
    }
}