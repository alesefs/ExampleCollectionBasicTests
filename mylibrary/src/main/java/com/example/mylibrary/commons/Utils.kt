package com.example.mylibrary.commons

import android.accessibilityservice.AccessibilityServiceInfo
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.mylibrary.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

object Utils {
    @JvmStatic
    fun addMask(value: String, mask: String): String {
        var formatado = ""
        var i = 0
        for (m in mask.toCharArray()) {
            if (m != '#') {
                formatado += m
                continue
            }
            formatado += try {
                value[i]
            } catch (e: StringIndexOutOfBoundsException) {
                break
            }
            i++
        }
        return formatado
    }

    @JvmStatic
    fun removeMask(value: String): String =
        value.replace(".", "").replace("-", "").replace("(", "")
            .replace(")", "").replace("/", "").replace(" ", "")
            .replace("*", "")

    @JvmStatic
    fun dateFormat(date: String?): String = if (date.isNullOrBlank()) {
        SimpleDateFormat(Constants.DATE_FORMAT_MASK, Locale.getDefault()).format(Date())
    } else {
        SimpleDateFormat(Constants.DATE_FORMAT_MASK, Locale.getDefault()).format(date)
    }

    private val PHONE_PATTERN_8: Pattern = Pattern.compile(
        "^[+]?[0-9]{10}$"
    )

    private val PHONE_PATTERN_9: Pattern = Pattern.compile(
        "^[+]?[0-9]{11}$"
    )

    @JvmStatic
    fun phoneFormat(phone: CharSequence, maxSizePhone: Int): Boolean =
        maxSizePhone == 11 && PHONE_PATTERN_9.matcher(phone).matches()

    enum class PhoneValidate(val status: String) {
        EMPTY("empty"),
        INCORRECT("incorrect"),
        VALID("valid")
    }

    @JvmStatic
    fun changeColorToImageView(
        key: String?,
        imageView: ImageView,
        context: Context
    ) {
        when (key) {
            Constants.SEG_KEY_ABC -> imageView.setColorFilter(
                ContextCompat.getColor(context, R.color.blue)
            )
            Constants.SEG_KEY_XYZ -> imageView.setColorFilter(
                ContextCompat.getColor(context, R.color.lt_gray)
            )
            Constants.SEG_KEY_KWY -> imageView.setColorFilter(
                ContextCompat.getColor(context, R.color.dk_gray)
            )
            else -> imageView.setColorFilter(
                ContextCompat.getColor(context, R.color.orangered)
            )
        }
    }

    @JvmStatic
    fun isAccessibilityEnable(context: Context): Boolean {
        val am = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        val isAccessibilityFlagEnable = am.isEnabled
        val spokenFeedbackActiveServiceList =
            am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_SPOKEN)
        val isScreenReaderWithSpokenFeedbackActive =
            spokenFeedbackActiveServiceList != null && spokenFeedbackActiveServiceList.size > 0
        val isAccessibilityActive =
            isAccessibilityFlagEnable && isScreenReaderWithSpokenFeedbackActive
        val isTouchExplorationActive = am.isTouchExplorationEnabled

        return isAccessibilityActive || isTouchExplorationActive
    }

    fun forceFocus(view: View) {
        view.postDelayed({
            view.isFocusable = true
            view.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
        }, 1000L)
    }

    fun forceAndRequestFocus(view: View) {
        view.postDelayed({
            view.isFocusableInTouchMode = true
            view.isFocusable = true
            view.requestFocus()
            view.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
        }, 10L)
    }

    fun cleanFocus(view: View) {
        view.performAccessibilityAction(
            AccessibilityNodeInfo.ACTION_CLEAR_ACCESSIBILITY_FOCUS, null
        )
    }
}