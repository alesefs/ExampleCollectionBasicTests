package com.example.mylibrary.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.airbnb.lottie.LottieAnimationView
import com.example.mylibrary.R

class AppOnBoardingAdapter(var pages: List<OnBoardingPagesModel>) : PagerAdapter() {
    override fun getCount(): Int  = pages.size

    override fun isViewFromObject(view: View, item: Any): Boolean = view == item as View

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.layout_page_onboarding, container, false)

        val onboardingLayout: ConstraintLayout = view.findViewById(R.id.page_onboarding)
        onboardingLayout.setBackgroundColor(ContextCompat.getColor(container.context, R.color.white))

        val imageView: ImageView = view.findViewById(R.id.iv_onboarding)
        imageView.setImageResource(pages[position].image)
        imageView.visibility = View.GONE

        val lottieAnimation: LottieAnimationView = view.findViewById(R.id.lottie_onboarding)
        lottieAnimation.setAnimation(pages[position].logoResource)
        lottieAnimation.playAnimation()

        val txTitle: TextView = view.findViewById(R.id.title_onboarding)
        txTitle.text = container.context.getString(pages[position].title)

        val txSubtitle: TextView = view.findViewById(R.id.subtitle_onboarding)
        txSubtitle.text = container.context.getString(pages[position].subTitle)

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, item: Any) {
        container.removeView(item as View)
    }

}
