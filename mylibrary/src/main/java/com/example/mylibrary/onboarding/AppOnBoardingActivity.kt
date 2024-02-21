package com.example.mylibrary.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.mylibrary.R
import com.example.mylibrary.commons.BaseAppActivity
import com.example.mylibrary.commons.Constants
import com.example.mylibrary.models.MockDataModel

class AppOnBoardingActivity : BaseAppActivity() {

    companion object {
        fun getStartIntent(
            context: Context,
            data: MockDataModel
        ): Intent = Intent(context, AppOnBoardingActivity::class.java).apply {
            putExtra(Constants.EXTRA_DATA, data as Parcelable)
        }
    }

    private lateinit var data: MockDataModel
    private lateinit var adapterOnboarding: AppOnBoardingAdapter
    private lateinit var viewPager: ViewPager

    private lateinit var btVoltar: TextView
    private lateinit var btProximo: TextView
    private lateinit var sliderDots: LinearLayout

    private var dotsCount = 0
    private var dotsMask = 5
    private var totalList: MutableList<ImageView> = mutableListOf()
    private lateinit var dot: ImageView

    private var currentPage = 0
    private var nextPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retriveIntentData()
        setContentView(R.layout.layout_onboarding)

        val textOB = findViewById<TextView>(R.id.text_onboarding)
        textOB.text = getString(R.string.title_onboarding, data.name)

        initViewPager()
        initSliderIndicator()
        viewPageListener()
        bottomHandleIndicator(nextPage)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putParcelable(Constants.EXTRA_DATA_RETRIVE, data)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        data = savedInstanceState.getParcelable(Constants.EXTRA_DATA_RETRIVE)!!
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun retriveIntentData() {
        data = intent.getParcelableExtra(Constants.EXTRA_DATA)!!
    }

    private fun initViewPager() {
        fetchPagesItems()

        adapterOnboarding = AppOnBoardingAdapter(fetchPagesItems())
        viewPager = findViewById(R.id.viewpager_onboarding)
        viewPager.adapter = adapterOnboarding
    }

    private fun fetchPagesItems(): List<OnBoardingPagesModel> = listOfNotNull(
        OnBoardingPagesModel(R.string.title_1, R.string.subtitle_1, R.drawable.ic_launcher_foreground_1, R.raw.biker_animation),
        OnBoardingPagesModel(R.string.title_2, R.string.subtitle_2, R.drawable.ic_launcher_foreground_2, R.raw.character_animation),
        OnBoardingPagesModel(R.string.title_3, R.string.subtitle_3, R.drawable.ic_launcher_foreground_3, R.raw.rabbit),
        OnBoardingPagesModel(R.string.title_1, R.string.subtitle_1, R.drawable.ic_launcher_foreground_1, R.raw.biker_animation),
        OnBoardingPagesModel(R.string.title_2, R.string.subtitle_2, R.drawable.ic_launcher_foreground_2, R.raw.character_animation),
        OnBoardingPagesModel(R.string.title_3, R.string.subtitle_3, R.drawable.ic_launcher_foreground_3, R.raw.rabbit),
        OnBoardingPagesModel(R.string.title_1, R.string.subtitle_1, R.drawable.ic_launcher_foreground_1, R.raw.biker_animation),
        OnBoardingPagesModel(R.string.title_2, R.string.subtitle_2, R.drawable.ic_launcher_foreground_2, R.raw.character_animation),
        OnBoardingPagesModel(R.string.title_3, R.string.subtitle_3, R.drawable.ic_launcher_foreground_3, R.raw.rabbit),
        OnBoardingPagesModel(R.string.title_1, R.string.subtitle_1, R.drawable.ic_launcher_foreground_1, R.raw.biker_animation),
        OnBoardingPagesModel(R.string.title_2, R.string.subtitle_2, R.drawable.ic_launcher_foreground_2, R.raw.character_animation),
        OnBoardingPagesModel(R.string.title_3, R.string.subtitle_3, R.drawable.ic_launcher_foreground_3, R.raw.rabbit)
    )

    private fun initSliderIndicator() {
        btVoltar = findViewById(R.id.bt_voltar)
        btVoltar.bringToFront()
        btProximo = findViewById(R.id.bt_proximo)
        btProximo.bringToFront()
        sliderDots = findViewById(R.id.dots_slider)

        dotsCount = adapterOnboarding.count
        dotsMask = if (dotsCount > dotsMask) dotsMask else dotsCount

        for (i in 0 until dotsCount) {
            dot = ImageView(this)
            dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_inactive))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            totalList.add(dot)

            sliderDots.addView(totalList[i], params)
        }

        totalList.forEachIndexed { index, element ->
            if (index > dotsMask - 1) {
                element.visibility = View.GONE
                element.setImageDrawable(
                    ContextCompat.getDrawable(this@AppOnBoardingActivity, R.drawable.dot_deactive)
                )
            } else {
                element.visibility = View.VISIBLE
                element.setImageDrawable(
                    ContextCompat.getDrawable(this@AppOnBoardingActivity, R.drawable.dot_inactive)
                )
            }

            if (dotsCount > dotsMask) {
                if (index == dotsMask - 1) {
                    element.apply {
                        scaleX = .65f
                        scaleY = .65f
                    }
                }
            }

        }

        totalList[0].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_active))
    }

    private fun viewPageListener() {
        adapterOnboarding.notifyDataSetChanged()

        if (viewPager.adapter != null) {
            viewPager.adapter!!.notifyDataSetChanged()
        }

        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
                //TODO("Not yet implemented")
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                nextPage = position
            }

            override fun onPageSelected(position: Int) {
                currentPage = position

                for (i in 0 until dotsCount) {
                    totalList[i].setImageDrawable(
                        ContextCompat.getDrawable(
                            this@AppOnBoardingActivity,
                            R.drawable.dot_inactive
                        )
                    )
                }

                if (dotsCount > dotsMask) {
                    totalList.forEachIndexed { index, element ->
                        if (position >= dotsMask - 2 && position < totalList.size - 2) {
                            if (index < position - 2 || index > position + 2) {
                                element.visibility = View.GONE
                                element.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        this@AppOnBoardingActivity,
                                        R.drawable.dot_deactive
                                    )
                                )
                            } else {
                                element.visibility = View.VISIBLE
                                element.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        this@AppOnBoardingActivity,
                                        R.drawable.dot_inactive
                                    )
                                )
                            }
                        } else if (position >= totalList.size - 2) {
                            if (index < totalList.size - 5) {
                                element.visibility = View.GONE
                                element.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        this@AppOnBoardingActivity,
                                        R.drawable.dot_deactive
                                    )
                                )
                            } else {
                                element.visibility = View.VISIBLE
                                element.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        this@AppOnBoardingActivity,
                                        R.drawable.dot_inactive
                                    )
                                )
                            }
                        } else {
                            if (index > dotsMask - 1) {
                                element.visibility = View.GONE
                                element.setImageDrawable(
                                    ContextCompat.getDrawable(this@AppOnBoardingActivity, R.drawable.dot_deactive)
                                )
                            } else {
                                element.visibility = View.VISIBLE
                                element.setImageDrawable(
                                    ContextCompat.getDrawable(this@AppOnBoardingActivity, R.drawable.dot_inactive)
                                )
                            }
                        }


                        if (position >= dotsMask - 3 && position < totalList.size - 2) {
                            if (index == position + 2 || index == position - 2) {
                                element.animate().apply {
                                    duration = 200
                                    scaleX(.35f)
                                    scaleY(.35f)
                                }.apply { withEndAction {
                                    scaleX(0.65f)
                                    scaleY(0.65f)
                                }}
                            } else {
                                element.apply {
                                    scaleX = 1.0f
                                    scaleY = 1.0f
                                }
                            }
                        } else if (position == 1) {
                            if (index == position + 3 || index == position - 1) {
                                element.animate().apply {
                                    duration = 200
                                    scaleX(.35f)
                                    scaleY(.35f)
                                }.apply { withEndAction {
                                    scaleX(0.65f)
                                    scaleY(0.65f)
                                }}
                            } else {
                                element.apply {
                                    scaleX = 1.0f
                                    scaleY = 1.0f
                                }
                            }
                        } else if (position == 0) {
                            if (index == position + 4) {
                                element.animate().apply {
                                    duration = 200
                                    scaleX(.35f)
                                    scaleY(.35f)
                                }.apply { withEndAction {
                                    scaleX(0.65f)
                                    scaleY(0.65f)
                                }}
                            } else {
                                element.apply {
                                    scaleX = 1.0f
                                    scaleY = 1.0f
                                }
                            }
                        } else if (position == totalList.size - 2) {
                            if (index == position + 1 || index == position - 3) {
                                element.animate().apply {
                                    duration = 200
                                    scaleX(.35f)
                                    scaleY(.35f)
                                }.apply { withEndAction {
                                    scaleX(0.65f)
                                    scaleY(0.65f)
                                }}
                            }
                        } else {
                            if (index == position) {
                                element.apply {
                                    scaleX = 1.0f
                                    scaleY = 1.0f
                                }
                            }
                        }

                        /*if (currentPage > nextPage) {
                            element.animate().apply {
                                duration = 200
                                translationXBy(-48f)
                                withEndAction {
                                    translationXBy(48f)
                                }
                            }
                        } else {
                            element.animate().apply {
                                duration = 200
                                translationXBy(48f)
                                withEndAction {
                                    translationXBy(-48f)
                                }
                            }
                        }*/
                    }
                }

//              if (currentPage > nextPage) {}

                totalList[position].setImageDrawable(
                    ContextCompat.getDrawable(
                        this@AppOnBoardingActivity,
                        R.drawable.dot_active
                    )
                )

                bottomHandleIndicator(position)
           }
        })
    }

    private fun bottomHandleIndicator(position: Int) {
        btVoltar.setOnClickListener {
            if (position > 0) {
                viewPager.currentItem = position - 1
                viewPageListener()
            } else {
                viewPager.currentItem = 0
                Toast.makeText(this, "voltou ao começo", Toast.LENGTH_SHORT).show()
            }
        }

        btProximo.setOnClickListener {
            if (position < totalList.size - 1) {
                viewPager.currentItem = position + 1
                viewPageListener()
            } else {
                Toast.makeText(this, "chegou ao final", Toast.LENGTH_SHORT).show()
            }


        }
    }
}
