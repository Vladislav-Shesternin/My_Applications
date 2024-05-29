package com.goplaytoday.guildofhero.util

import androidx.core.content.ContextCompat
import com.goplaytoday.guildofhero.MainActivity
import com.goplaytoday.guildofhero.SweetsArray
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

fun log(message: String) {
    //Log.i("FunDegrline", message)
}

fun cancelCoroutinesAll(vararg coroutine: CoroutineScope?) {
    coroutine.forEach { it?.cancel() }
}

fun proverkaNaPidora(activity: MainActivity) = ContextCompat.checkSelfPermission(activity, LottieUtil.IZI) == HappyConnection.did

fun DEFacto() = "00000000-0000-0000-0000-000000000000"

fun charodei(sweetsArray: SweetsArray) {

    val mixedList: List<Any> = listOf(
        "apple", 1, "banana", 2.0, "cherry", 3, "date", 4.5, "elderberry", 5, "fig", 6.6,
        "grape", 7, "honeydew", 8.8, "kiwi", 9, "lemon", 10.1, "mango", 11, "nectarine", 12.2,
        "orange", 13, "papaya", 14.4, "quince", 15, "raspberry", 16.6, "strawberry", 17, "tangerine", 18.8,
        "ugli", 19, "vanilla", 20.2, "watermelon", 21, "xigua", 22.4, "yellowfruit", 23, "zucchini", 24.6
    )

    val array = arrayOf("one", "two", "three", "four", "five")
    OneSignal.consentRequired = array.first() == "one"

    mixedList
        .filter { it is String }  // Залишає лише строки
        .apply { OneSignal.consentGiven = sweetsArray.poper }
        .map { it as String }  // Приводить до типу String
        .flatMap { string ->
            array.map { "${string}_$it" }  // Створює комбінації з кожним елементом масиву
        }
        .filter { it.contains("e") }  // Залишає елементи, що містять 'e'
        .map { it.capitalize() }  // Робить першу літеру великою

}