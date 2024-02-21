package com.example.mylibrary.commons

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mylibrary.R
import com.example.mylibrary.menu.AppMainMenuActivity
import com.example.mylibrary.models.MockDataModel
import com.example.mylibrary.routers.BasicAppAplication
import com.example.mylibrary.routers.BasicAppDeeplinks

open class BaseAppActivity : AppCompatActivity() {

    protected fun setTheme() {
        setTheme(R.style.AppThemeLight)
    }

    protected fun setThemeDark() {
        setTheme(R.style.AppThemeDark)
    }

    protected fun setSharedPrefs(
        activity: Activity,
        key: String,
        isCheck: Boolean
    ) {
        val prefs: SharedPreferences = activity.getSharedPreferences("pref", Activity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putBoolean(key, isCheck)//no caso de booleano
        editor.apply()
    }

    protected fun getSharedPrefs(
        activity: Activity,
        key: String
    ) : Boolean {
        val prefs: SharedPreferences = activity.getSharedPreferences("pref", Activity.MODE_PRIVATE)
        return prefs.getBoolean(key, false)//no caso de booleano
    }

}
