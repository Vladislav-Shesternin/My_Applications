package com.logic.exchangewizard.game.screens

import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.logic.exchangewizard.MainActivity
import com.logic.exchangewizard.R
import com.logic.exchangewizard.game.util.Screen


var kakoutoText = "Добро пожаловать в мир ExchangeWizard - удобного приложения для быстрого и точного конвертирования валюты. Независимо от того, где вы находитесь и какую валюту вы используете, наше приложение поможет вам легко пересчитать сумму в нужной валюте.\u2028  Особенности ExchangeWizard:\u2028  \uD83D\uDCB1 Обширный список валют: Наше приложение поддерживает более 170 валют мира, что позволяет вам переводить средства в любой стране без лишних хлопот.\u2028  \uD83D\uDD04 Актуальные курсы валют: Мы обновляем курсы валют в режиме реального времени, чтобы вы могли быть уверены в точности перевода.\u2028  \uD83D\uDCC8 История конвертаций: ExchangeWizard сохраняет историю ваших переводов, что позволяет быстро вернуться к предыдущим операциям и отслеживать изменения курсов.\u2028  \uD83C\uDF10 Офлайн режим: Вам не нужен доступ к интернету для осуществления простых конвертаций. Вы можете пользоваться приложением в любой ситуации.\u2028  \uD83D\uDCBC Избранное: Добавляйте популярные валюты в избранное для быстрого доступа и удобства использования.\u2028  ExchangeWizard - это ваш надежный помощник в мире валютных переводов. Независимо от того, путешествуете ли вы, покупаете товары в другой стране или ведете международные финансовые операции, наше приложение обеспечит вам быстрый и точный пересчет валюты. Присоединяйтесь к миллионам довольных пользователей и упростите свои финансовые операции с ExchangeWizard - вашим лучшим другом в мире конвертации валюты! \uD83C\uDF0D\uD83D\uDCB1\u2028\n"

class QvestPistoletosScreen(override val activity: MainActivity): Screen(activity) {

    private val textLbl = TextView(activity)
    private val scroll  = ScrollView(activity)
    private val bonBtn  = Button(activity)



    override fun ViewGroup.addActorsOnStage() {
        addScroll()
        addDoneBtn()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun ViewGroup.addScroll() {
        addView(scroll)
        scroll.apply {
            setBounds(36f, 33f, 557f, 1145f)
            addView(textLbl)
        }

        textLbl.apply {
            text    = kakoutoText
            gravity = Gravity.START and Gravity.TOP
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 20f
        }
    }

    private fun ViewGroup.addDoneBtn() {
        addView(bonBtn)

        bonBtn.apply {
            setBackgroundResource(R.drawable.selector_btn)
            setBounds(27f, 1208f, 575f, 106f)
            setOnClickListener { game.navigationManager.back() }
        }
    }

}