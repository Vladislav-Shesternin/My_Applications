package com.nikelodeon.coursecalculator.game.screens

import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.nikelodeon.coursecalculator.MainActivity
import com.nikelodeon.coursecalculator.R
import com.nikelodeon.coursecalculator.game.manager.GameDataStoreManager
import com.nikelodeon.coursecalculator.game.util.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


var bigText = "Course Calculator: быстрый и удобный калькулятор.\u2028Full Store Description\u2028  Добро пожаловать в мир Course Calculator - инновационного калькулятора, который поможет вам быстро и точно решать разнообразные математические задачи. Наше приложение создано для того, чтобы облегчить вашу жизнь и сделать математику доступной и понятной для всех.\u2028  Особенности Course Calculator:\u2028\uD83D\uDD22 Простота использования: Course Calculator предоставляет интуитивный интерфейс и удобные функции для выполнения основных математических операций. Вы сможете легко складывать, вычитать, умножать и делить числа, а также выполнять более сложные математические операции.\u2028  ➗ Калькулятор валюты: Наше приложение позволяет вам конвертировать различные валюты по актуальным курсам. Вы сможете легко пересчитывать суммы в разных валютах без лишних усилий.\u2028  \uD83D\uDCC8 Функции для научных расчетов: Course Calculator обладает расширенными возможностями для проведения научных расчетов. Вы сможете использовать тригонометрические функции, расчеты степеней и другие математические операции.\u2028  \uD83D\uDCA1 Интерактивные функции: Наш калькулятор предоставляет возможность создания пользовательских формул и сохранения предыдущих результатов для повторного использования. Вы сможете быстро вернуться к предыдущим расчетам и продолжить работу с уже имеющимися данными.\u2028  Course Calculator - это ваш верный помощник в мире математики и расчетов. Независимо от уровня сложности задач, наше приложение обеспечит вам точные и быстрые ответы. Упростите свою повседневную жизнь с Course Calculator - вашим надежным партнером в математических расчетах! \uD83E\uDDEE\uD83D\uDE80\n"

class TextScreen(override val activity: MainActivity): Screen(activity) {

    private val textLbl = TextView(activity)
    private val scroll  = ScrollView(activity)
    private val doneBtn = Button(activity)



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
            setBounds(34f, 32f, 569f, 1153f)
            addView(textLbl)
        }

        textLbl.apply {
            text    = bigText
            gravity = Gravity.START and Gravity.TOP
            setTextColor(ContextCompat.getColor(activity, R.color.white))
            typeface = Typeface.createFromAsset(activity.assets, "Montserrat-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM) else textSize = 20f
        }
    }

    private fun ViewGroup.addDoneBtn() {
        addView(doneBtn)

        doneBtn.apply {
            setBackgroundResource(R.drawable.selector_btn)
            setBounds(34f, 1210f, 569f, 88f)
            setOnClickListener { game.navigationManager.back() }
        }
    }

}