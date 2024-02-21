package com.example.mylibrary.routers

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.example.mylibrary.commons.Constants
import com.example.mylibrary.coordinator.CoordinatorLayoutActivity
import com.example.mylibrary.databinding.DataBindingActivity
import com.example.mylibrary.details.AppDetailsDataActivity
import com.example.mylibrary.externalApi.ExternalApiActivity
import com.example.mylibrary.fragmentdata.FragmentDataActivity
import com.example.mylibrary.koin.KoinActivity
import com.example.mylibrary.lottie.LottieActivity
import com.example.mylibrary.menu.AppMainMenuActivity
import com.example.mylibrary.models.MockDataModel
import com.example.mylibrary.onboarding.AppOnBoardingActivity
import com.example.mylibrary.openwebview.WebviewActivity
import com.example.mylibrary.viewbinding.ViewBindingActivity
import timber.log.Timber


object AppRouter {

    fun goToOnboarding(context: Context, dataModel: MockDataModel) {
        val intent = AppOnBoardingActivity.getStartIntent(context, dataModel)
        context.startActivity(intent)
    }

    fun goToMainMenu(context: Context, dataModel: MockDataModel) {
        val intent = AppMainMenuActivity.getStartIntent(context, dataModel)
        context.startActivity(intent)
    }

    fun goToDetailsData(context: Context, dataModel: MockDataModel) {
        val intent = AppDetailsDataActivity.getStartIntent(context, dataModel)
        context.startActivity(intent)
    }

    fun goToWebview(context: Context) {
        val intent = Intent(context, WebviewActivity::class.java)
        context.startActivity(intent)
    }

    fun share(activity: Activity, uri: Uri) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = Constants.IMAGE_TYPE
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        activity.startActivity(Intent.createChooser(intent, "compartilhar"))
    }

    fun goToFragment(context: Context, dataModel: MockDataModel) {
        val intent = FragmentDataActivity.getStartIntent(context, dataModel)
        context.startActivity(intent)
    }

    fun goToCoordinatorLayout(context: Context) {
        val intent = Intent(context, CoordinatorLayoutActivity::class.java)
        context.startActivity(intent)
    }

    fun goToActivityBindingLayout(context: Context) {
        val intent = Intent(context, ViewBindingActivity::class.java)
        context.startActivity(intent)
    }

    fun goToKoinExample(context: Context) {
        val intent = Intent(context, KoinActivity::class.java)
        context.startActivity(intent)
    }

    fun deeplink(context: Context, deeplink: String, clearTask: Boolean, dataModel: MockDataModel?) {
        try {
            val appType = "deeplink"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("$appType://$deeplink")
            if (clearTask) {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            if (dataModel != null) {
                /*
                * aqui entra details e outros que tem dataModel
                */
                when {
                    deeplink.equals(BasicAppDeeplinks.DETAILS.deeplinks, ignoreCase = true) -> {
                        goToDetailsData(context, dataModel)
                    }
                    else -> {
                        Log.i("alelog", "vai dar erro com dataModel")
                        Timber.tag("alelog").e("vai dar erro com dataModel")
                    }
                }
            } else {
                /*
                * aqui entra webview e outros que nao tem dataModel
                */
                when {
                    deeplink.equals(BasicAppDeeplinks.WEBVIEW.deeplinks, ignoreCase = true) -> {
                        goToWebview(context)
                    }
                    else -> {
                        Log.i("alelog", "vai dar erro sem dataModel")
                        Timber.tag("alelog").e("vai dar erro sem dataModel")
                    }
                }
            }
        } catch (e: ActivityNotFoundException) {
            Timber.tag("AleLog").e(e)
        }
    }

    fun goToBaseActivity(context: Context, dataModel: MockDataModel, deeplink: String? = null) {
        //val intent = BaseAppActivity.getStartIntent(context, dataModel, deeplink)
        //context.startActivity(intent)
    }

    fun goToDataBindingExample(context: Context) {
        val intent = Intent(context, DataBindingActivity::class.java)
        context.startActivity(intent)
    }

    fun goToLottieAnimation(context: Context) {
        val intent = Intent(context, LottieActivity::class.java)
        context.startActivity(intent)
    }

    fun goToExternalApi(context: Context) {
        val intent = Intent(context, ExternalApiActivity::class.java)
        context.startActivity(intent)
    }
}
