package com.veldan.lbjt.game.utils

import android.content.res.Configuration
import com.veldan.lbjt.MainActivity
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Locale

class LanguageUtil(private val activity: MainActivity) {

    companion object {
        var localeFlow = MutableStateFlow(Locale.getDefault())
    }

    fun getStringResource(
        resourceId: Int,
        locale: Locale = localeFlow.value,
    ): String = with(activity) { Configuration(resources.configuration).run {
        setLocale(locale)
        createConfigurationContext(this).getString(resourceId)
    } }

}