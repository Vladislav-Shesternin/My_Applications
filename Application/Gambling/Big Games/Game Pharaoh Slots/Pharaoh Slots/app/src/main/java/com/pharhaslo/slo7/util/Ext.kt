package com.pharhaslo.slo7.util

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.pharhaslo.slo7.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

private var toastMessage : Toast? = null
val mainScope = CoroutineScope(Job() + Dispatchers.IO)


fun Activity.toast(text : String?){
        toastMessage?.cancel()
        toastMessage = Toast.makeText(this, text, Toast.LENGTH_SHORT)
        toastMessage?.show()
}

fun FirebaseAnalytics.logFragment(fragment : Fragment){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, fragment.javaClass.simpleName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, fragment.javaClass.simpleName)
        logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
}

fun FirebaseAnalytics.logEvent(eventName: String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, eventName)
        logEvent(eventName, bundle)
}

fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> {
        return this?.let {
                val regex = if (ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr)
                regex.findAll(this).map { it.range.first }.toList()
        } ?: emptyList()
}














