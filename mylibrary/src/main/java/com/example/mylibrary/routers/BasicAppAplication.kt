package com.example.mylibrary.routers

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.mylibrary.commons.AppOpenExternalListener
import com.example.mylibrary.commons.ConfigApp
import com.example.mylibrary.models.MockDataModel

open class BasicAppAplication private constructor() {
    data class Builder(
        private val context: Context,
        private val dataModel: MockDataModel
    ) {
        fun build() = apply {
            BasicAppAplication()
        }

        fun openOnboarding() {
            AppRouter.goToOnboarding(context, dataModel)
        }

        fun openMainMenu() {
            AppRouter.goToMainMenu(context, dataModel)
        }

        fun openDetailsData() {
            AppRouter.goToDetailsData(context, dataModel)
        }

        fun openWebview() {
            AppRouter.goToWebview(context)
        }

        fun openSPA(listener: AppOpenExternalListener) = apply {
            ConfigApp.appListener = listener
        }

        fun openFragment() {
            AppRouter.goToFragment(context, dataModel)
        }

        fun openCoordinatorLayout() {
            AppRouter.goToCoordinatorLayout(context)
        }

        fun openActivityBindingLayout() {
            AppRouter.goToActivityBindingLayout(context)
        }

        fun openKoinExample() {
            AppRouter.goToKoinExample(context)
        }

        fun openDeeplink(url: String, clearTask: Boolean, dataModel: MockDataModel? = null) {
            Log.i("alelog", "$url $clearTask")
            AppRouter.deeplink(context, deeplink = url, clearTask = clearTask, dataModel)
        }

        fun openByDeeplinkFake(deeplink: String) {
            when {
                deeplink.equals(BasicAppDeeplinks.DETAILS.deeplinks, ignoreCase = true) -> {
                    openDeeplink(deeplink, true, dataModel)
                }
                deeplink.equals(BasicAppDeeplinks.WEBVIEW.deeplinks, ignoreCase = true) -> {
                    openDeeplink(deeplink, true)
                }
                else -> {
                    Toast.makeText(context, "existe esse deeplink", Toast.LENGTH_SHORT).show()
                    Log.i("alelog", "existe esse deeplink")
                }
            }
        }

        fun openDataBinding() {
            AppRouter.goToDataBindingExample(context)
        }

        fun openLottieAnimation() {
            AppRouter.goToLottieAnimation(context)
        }

        fun openExternalApi() {
            AppRouter.goToExternalApi(context)
        }
    }
}