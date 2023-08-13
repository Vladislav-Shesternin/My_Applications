package com.sheluderes.cryptotradehub.game.screens

import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.sheluderes.cryptotradehub.MainActivity
import com.sheluderes.cryptotradehub.R
import com.sheluderes.cryptotradehub.game.util.Screen


var peredayPrivetSestre = "Добро пожаловать в мир CryptoTradeHub - удобного приложения для быстрого и точного конвертирования валюты. Независимо от того, где вы находитесь и какую валюту вы используете, наше приложение поможет вам легко пересчитать сумму в нужной валюте.\u2028  Особенности CryptoTradeHub:\u2028  \uD83D\uDCB1 Обширный список валют: Наше приложение поддерживает более 170 валют мира, что позволяет вам переводить средства в любой стране без лишних хлопот.\u2028  \uD83D\uDD04 Актуальные курсы валют: Мы обновляем курсы валют в режиме реального времени, чтобы вы могли быть уверены в точности перевода.\u2028  \uD83D\uDCC8 История конвертаций: CryptoTradeHub сохраняет историю ваших переводов, что позволяет быстро вернуться к предыдущим операциям и отслеживать изменения курсов.\u2028  \uD83C\uDF10 Офлайн режим: Вам не нужен доступ к интернету для осуществления простых конвертаций. Вы можете пользоваться приложением в любой ситуации.\u2028  \uD83D\uDCBC Избранное: Добавляйте популярные валюты в избранное для быстрого доступа и удобства использования.\u2028  CryptoTradeHub - это ваш надежный помощник в мире валютных переводов. Независимо от того, путешествуете ли вы, покупаете товары в другой стране или ведете международные финансовые операции, наше приложение обеспечит вам быстрый и точный пересчет валюты. Присоединяйтесь к миллионам довольных пользователей и упростите свои финансовые операции с CryptoTradeHub - вашим лучшим другом в мире конвертации валюты! \uD83C\uDF0D\uD83D\uDCB1\u2028\n"

class ChuvstvuyScoroPovishenieYxBlaScreen(override val activity: MainActivity): Screen(activity) {

    private val textLbl = TextView(activity)
    private val scroll  = ScrollView(activity)
    private val bonBtn  = ImageView(activity)



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
            setBounds(60f, 72f, 576f, 1010f)
            addView(textLbl)
        }

        textLbl.apply {
            text    = peredayPrivetSestre
            gravity = Gravity.START and Gravity.TOP
            setTextColor(ContextCompat.getColor(activity, R.color.black))
//            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 20f
        }
    }

    private fun ViewGroup.addDoneBtn() {
        addView(bonBtn)

        bonBtn.apply {
            setImageResource(R.drawable.lapa)
            setBounds(60f, 1307f, 603f, 93f)
            setOnClickListener { game.navigationManager.back() }
        }
    }

}