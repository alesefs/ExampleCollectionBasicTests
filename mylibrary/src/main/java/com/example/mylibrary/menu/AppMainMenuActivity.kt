package com.example.mylibrary.menu

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R
import com.example.mylibrary.commons.BaseAppActivity
import com.example.mylibrary.commons.ConfigApp
import com.example.mylibrary.commons.Constants
import com.example.mylibrary.models.MockDataModel
import com.example.mylibrary.routers.AppRouter
import com.example.mylibrary.routers.BasicAppAplication
import com.example.mylibrary.routers.BasicAppDeeplinks
import timber.log.Timber


class AppMainMenuActivity : BaseAppActivity() {

    companion object {
        fun getStartIntent(
            context: Context,
            dataModel: MockDataModel,
        ): Intent = Intent(context, AppMainMenuActivity::class.java).apply {
            putExtra(Constants.EXTRA_DATA, dataModel as Parcelable)
        }
    }

    private var data: MockDataModel? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var clMainMenu: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retriveIntentData()
        setContentView(R.layout.layout_main_menu)

        setViews()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putParcelable(Constants.EXTRA_DATA_RETRIVE, data)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState.containsKey(Constants.EXTRA_DATA_RETRIVE)) {
            data = savedInstanceState.getParcelable(Constants.EXTRA_DATA_RETRIVE)!!
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        intent.extras?.let { bundle ->
            if (bundle.containsKey(Constants.EXTRA_DATA_RETRIVE)) {
                data = bundle.get(Constants.EXTRA_DATA_RETRIVE) as MockDataModel
            }
        }
    }

    private fun retriveIntentData() {
//        data = intent.getParcelableExtra(Constants.EXTRA_DATA)!!
//        deeplink = intent.getStringExtra(Constants.EXTRA_DEEPLINK)!!

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

    private fun mockData() = MockDataModel(
        name = "cosworth asiatech",
        age = 70,
        mail = "asiatechonologies@cosworth.com",
        phone = "11923434543",
        favouriteColor = "silver",
        isFirstLogin = false
    )

    private fun setViews() {
        clMainMenu = findViewById(R.id.cl_main_menu)

        if (data == null) {
            data = mockData()
        }

        val titleMainMenu = findViewById<TextView>(R.id.title_main_menu)
        titleMainMenu.text = getString(R.string.title_onboarding, data?.name)

        val subtitleMainMenu = findViewById<TextView>(R.id.subtitle_main_menu)
        subtitleMainMenu.text = getString(R.string.subtitle_onboarding, data?.age.toString())

        ViewCompat.setAccessibilityDelegate(titleMainMenu, object : AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
                info.setTraversalAfter(subtitleMainMenu)
                super.onInitializeAccessibilityNodeInfo(host, info)
            }
        })

        recyclerView = findViewById(R.id.rv_main_menu)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(
                this@AppMainMenuActivity,
                RecyclerView.VERTICAL,
                false
            )
            setHasFixedSize(true)
            adapter = MainMenuAdapter(
                listButton()
            ) { item ->
                when {
                    item.title.equals(
                        getString(R.string.button_1), ignoreCase = true
                    ) -> {
                        ConfigApp.appListener?.onOpenSpaHelp()
                    }
                    item.title.equals(
                        getString(R.string.button_2), ignoreCase = true
                    ) -> {
                        data?.let {
                            BasicAppAplication.Builder(
                                this@AppMainMenuActivity,
                                dataModel = it
                            ).build().openOnboarding()
                        }
                    }
                    item.title.equals(
                        getString(R.string.button_3), ignoreCase = true
                    ) -> {
                        data?.let {
                            BasicAppAplication.Builder(
                                this@AppMainMenuActivity,
                                dataModel = it
                            ).build().openFragment()
                        }
                    }
                    item.title.equals(
                        getString(R.string.button_4), ignoreCase = true
                    ) -> {
                        data?.let {
                            BasicAppAplication.Builder(
                                this@AppMainMenuActivity,
                                dataModel = it
                            ).build().openWebview()
                        }
                        /*data?.let {
                            BasicAppAplication.Builder(
                                this@AppMainMenuActivity,
                                dataModel = it
                            ).build().openByDeeplinkFake(BasicAppDeeplinks.WEBVIEW.deeplinks)
                        }*/
                    }
                    item.title.equals(
                        getString(R.string.button_5), ignoreCase = true
                    ) -> {
                        data?.let {
                            BasicAppAplication.Builder(
                                this@AppMainMenuActivity,
                                dataModel = it
                            ).build().openDetailsData()
                        }
                        /*data?.let {
                            BasicAppAplication.Builder(
                                this@AppMainMenuActivity,
                                dataModel = it
                            ).build().openByDeeplinkFake(BasicAppDeeplinks.DETAILS.deeplinks)
                        }*/
                    }
                    item.title.equals(
                        getString(R.string.button_6), ignoreCase = true
                    ) -> {
                        //TODO usar o share()
                        /*try {
                            clMainMenu.isDrawingCacheEnabled = true
                            clMainMenu.buildDrawingCache()
                            val bitmap = createBitmapFromView(this.context, clMainMenu)//clMainMenu.drawingCache//clMainMenu.getBitmap()
                            val path = bitmap.save(contentResolver)
                            val uri = Uri.parse(path)

                            AppRouter.share(this@AppMainMenuActivity, uri)
                        } catch (e: IllegalStateException) {
                            Timber.tag("ALETimber").e(e)
                        }*/
                    }
                    item.title.equals(
                        getString(R.string.button_7), ignoreCase = true
                    ) -> {
                        data?.let {
                            BasicAppAplication.Builder(
                                this@AppMainMenuActivity,
                                dataModel = it
                            ).build().openCoordinatorLayout()
                        }
                    }
                    item.title.equals(
                        getString(R.string.button_8), ignoreCase = true
                    ) -> {
                        data?.let {
                            BasicAppAplication.Builder(
                                this@AppMainMenuActivity,
                                dataModel = it
                            ).build().openActivityBindingLayout()
                        }
                    }
                    item.title.equals(
                        getString(R.string.button_9), ignoreCase = true
                    ) -> {
                        data?.let {
                            BasicAppAplication.Builder(
                                this@AppMainMenuActivity,
                                dataModel = it
                            ).build().openKoinExample()
                        }
                    }
                    item.title.equals(
                        getString(R.string.button_10), ignoreCase = true
                    ) -> {
                        data?.let {
                            BasicAppAplication.Builder(
                                this@AppMainMenuActivity,
                                dataModel = it
                            ).build().openDataBinding()
                        }
                    }
                    item.title.equals(
                        getString(R.string.button_11), ignoreCase = true
                    ) -> {
                        data?.let {
                            BasicAppAplication.Builder(
                                this@AppMainMenuActivity,
                                dataModel = it
                            ).build().openLottieAnimation()
                        }
                    }
                    item.title.equals(
                        getString(R.string.button_12), ignoreCase = true
                    ) -> {
                        data?.let {
                            BasicAppAplication.Builder(
                                this@AppMainMenuActivity,
                                dataModel = it
                            ).build().openExternalApi()
                        }
                    }
                }
            }
        }
    }

    private fun createBitmapFromView(ctx: Context, view: View): Bitmap {

        view.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        val dm = ctx.resources.displayMetrics
        view.measure(
            View.MeasureSpec.makeMeasureSpec(dm.widthPixels, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(dm.heightPixels, View.MeasureSpec.EXACTLY)
        )
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        val bitmap = Bitmap.createBitmap(
            view.measuredWidth,
            view.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.layout(view.left, view.top, view.right, view.bottom)
        view.draw(canvas)
        return bitmap
    }

    private fun listButton() : List<MainMenuButtonsModel> = listOfNotNull(
        MainMenuButtonsModel(
            getString(R.string.button_1),
            R.drawable.ic_launcher_foreground_1,
            getString(R.string.button_1),
            Constants.SEG_KEY_ABC
        ),
        MainMenuButtonsModel(
            getString(R.string.button_2),
            R.drawable.ic_launcher_foreground_2,
            getString(R.string.button_2),
            Constants.SEG_KEY_XYZ
        ),
        MainMenuButtonsModel(
            getString(R.string.button_3),
            R.drawable.ic_launcher_foreground_3,
            getString(R.string.button_3),
            Constants.SEG_KEY_KWY
        ),
        MainMenuButtonsModel(
            getString(R.string.button_4),
            R.drawable.ic_launcher_foreground_1,
            getString(R.string.button_4),
            Constants.SEG_KEY_ABC
        ),
        MainMenuButtonsModel(
            getString(R.string.button_5),
            R.drawable.ic_launcher_foreground_2,
            getString(R.string.button_5),
        ),
        MainMenuButtonsModel(
            getString(R.string.button_6),
            R.drawable.ic_launcher_foreground_3,
            getString(R.string.button_6),
            Constants.SEG_KEY_XYZ
        ),
        MainMenuButtonsModel(
            getString(R.string.button_7),
            R.drawable.ic_launcher_foreground_1,
            getString(R.string.button_7),
            Constants.SEG_KEY_ABC
        ),
        MainMenuButtonsModel(
            getString(R.string.button_8),
            R.drawable.ic_launcher_foreground_3,
            getString(R.string.button_8)
        ),
        MainMenuButtonsModel(
            getString(R.string.button_9),
            R.drawable.ic_launcher_foreground_1,
            getString(R.string.button_9),
            Constants.SEG_KEY_ABC
        ),
        MainMenuButtonsModel(
            getString(R.string.button_10),
            R.drawable.ic_launcher_foreground_2,
            getString(R.string.button_10),
            Constants.SEG_KEY_XYZ
        ),
        MainMenuButtonsModel(
            getString(R.string.button_11),
            R.drawable.ic_launcher_foreground_3,
            getString(R.string.button_11),
            Constants.SEG_KEY_KWY
        ),
        MainMenuButtonsModel(
            getString(R.string.button_12),
            R.drawable.ic_launcher_foreground_1,
            getString(R.string.button_12),
            Constants.SEG_KEY_ABC
        ),
    )
}