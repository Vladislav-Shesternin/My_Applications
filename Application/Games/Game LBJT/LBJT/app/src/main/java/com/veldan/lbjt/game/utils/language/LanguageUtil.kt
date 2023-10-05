package com.veldan.lbjt.game.utils.language


import android.content.Context
import com.veldan.lbjt.MainActivity
import com.veldan.lbjt.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Locale

class LanguageUtil(activity: MainActivity) {

    private val configurationContextUK: Context by lazy { with(activity) { resources.configuration.run {
        setLocale(Locale(Language.UK.language))
        createConfigurationContext(this)
    } } }
    private val configurationContextEN: Context by lazy { with(activity) { resources.configuration.run {
        setLocale(Locale(Language.EN.language))
        createConfigurationContext(this)
    } } }


    var languageFlow = MutableStateFlow(getLanguageByLocale(Locale.getDefault()))

    fun getStringResource(
        resourceId: Int,
        language: Language = languageFlow.value,
    ): String = getConfigurationContextByLanguage(language).getString(resourceId)

    private fun getConfigurationContextByLanguage(language: Language): Context = when (language) {
        Language.UK -> configurationContextUK
        Language.EN -> configurationContextEN
    }

    private fun getLanguageByLocale(locale: Locale): Language {
        return when (locale.language) {
            "uk" -> Language.UK
            "en" -> Language.EN
            else -> Language.EN
        }

    }

}