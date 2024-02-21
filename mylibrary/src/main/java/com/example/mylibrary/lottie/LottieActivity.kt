package com.example.mylibrary.lottie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.example.mylibrary.R

class LottieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)

        val animationView: LottieAnimationView = findViewById(R.id.animation_view)
        animationView.setAnimation(R.raw.rabbit)
        animationView.repeatCount = LottieDrawable.INFINITE
        animationView.playAnimation()
    }
}