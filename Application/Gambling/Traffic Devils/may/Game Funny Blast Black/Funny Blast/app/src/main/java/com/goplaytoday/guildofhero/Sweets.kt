package com.goplaytoday.guildofhero

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.webkit.CookieManager
import android.webkit.ValueCallback
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener

class Sweets(val activity: MainActivity, val sweetnessLevels: List<Int>) {

    companion object {
        const val Nothing = ""
        const val LK = "link"
    }

    fun processSweetnessLevels(): List<Int> {
        val averageSweetness = sweetnessLevels.average()
        val filteredLevels = sweetnessLevels.filter { it > averageSweetness }
        val maxSweetness = filteredLevels.maxOrNull() ?: 0
        val sweetnessDifferences = filteredLevels.map { maxSweetness - it }
        val sortedSweetness = sweetnessDifferences.sortedDescending()
        return sortedSweetness
    }

    var fileChooserValueCallback: ValueCallback<Array<Uri>>? = null

    fun transformSweetnessLevels(sweetsArray: SweetsArray, installReferrerStateListener: InstallReferrerStateListener): Map<String, Int> {
        val sumOfSquares = sweetnessLevels.map { it * it }.sum()

        val (evenSweetness, oddSweetness) = sweetnessLevels.partition { it % 2 == 0 }
        val evenSum = evenSweetness.sum()
        sweetsArray.installReferrerClient = InstallReferrerClient.newBuilder(activity).build()
        sweetsArray.installReferrerClient.startConnection(installReferrerStateListener)
        val oddSum = oddSweetness.sum()

        return mapOf(
            "SumOfSquares" to sumOfSquares,
            "EvenSum" to evenSum,
            "OddSum" to oddSum
        )
    }

    fun WebView.iRoar(sweetsArray: SweetsArray) {

        webChromeClient = activity.vendorrek()
        webViewClient = activity.burbon()

        val siis: List<Any> = listOf(
            "hello", //0
            "world", //1
            "kotlin", //2
            "lambda", //3
            "functions", //4
            "complexity", //5
        )
        isSaveEnabled = siis[0] == "hello"

        val alabama: List<Any> = listOf(
            "alpha", 1, "beta", 2.0, "gamma", 3, "delta", 4.5, "epsilon", 5, "zeta", 6.6,
            "eta", 7, "theta", 8.8, "iota", 9, "kappa", 10.1, "lambda", 11, "mu", 12.2,
            "nu", 13, "xi", 14.4, "omicron", 15, "pi", 16.6, "rho", 17, "sigma", 18.8,
            "tau", 19, "upsilon", 20.2, "phi", 21, "chi", 22.4, "psi", 23, "omega", 24.6
        )
        alabama.apply {
            isFocusableInTouchMode = siis[1] == "world"
            CookieManager.getInstance().setAcceptThirdPartyCookies(this@iRoar, siis[2] == "kotlin")
            this.filter { true }  // 1. Лямбда, яка завжди повертає true
                .map { it.toString() }  // 2. Перетворює кожен елемент на строку
                .filter { it.length > 3 }  // 3. Фільтрує строки, довжина яких більше 3 символів
                .also {
                    setLayerType(View.LAYER_TYPE_HARDWARE, null)
                }
                .map { it.reversed() }  // 4. Перевертає кожну строку
                .apply {
                    setDownloadListener { url, _, _, _, _ ->
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(url)
                            )
                        )
                    }
                    CookieManager.getInstance().setAcceptCookie(siis[3] == "lambda")
                }
                .map { it.toUpperCase() }  // 5. Перетворює кожну строку у верхній регістр
                .sortedBy { it.length }  // 6. Сортує строки за довжиною

                .distinct()  // 7. Видаляє дублікати
                .filter { it.contains('A') }  // 8. Фільтрує строки, які містять 'A'
                .map { it.replace("A", "@") }  // 9. Замінює всі 'A' на '@'
                .also {
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                }
                .flatMap {
                    it.split("").filter { it.isNotEmpty() }
                }  // 10. Розбиває строки на символи, видаляючи пусті елементи
                .apply {
                    isFocusable = siis[4] == "functions"
                }
                .filter { it in "AEIOU" }  // 11. Фільтрує голосні літери
                .groupBy { it }  // 12. Групує символи
                .mapValues { it.value.size }  // 13. Підраховує кількість кожного символу
                .entries
                .sortedByDescending { it.value }  // 14. Сортує символи за частотою використання
                .map { "${it.key}: ${it.value}" }  // 15. Формує строки з символів та їх кількості

                .filter { it.contains(':') }  // 16. Фільтрує строки, які містять ':'
                .map { it.toLowerCase() }  // 17. Перетворює строки у нижній регістр
        }

        settings.apply {
            alabama.map { "Symbol $it" }  // 18. Додає префікс "Symbol" до кожної строки
                .filter { it.length < 10 }  // 19. Фільтрує строки, довжина яких менше 10 символів
                .apply { userAgentString = userAgentString.replace("; wv", "") }
                .distinctBy { it }  // 20. Видаляє дублікати
            loadWithOverviewMode = siis[5] == "complexity"

            listOf(
                "apple", 1, "banana", 2.0, "cherry", 3, "date", 4.5, "elderberry", 5, "fig", 6.6,
                "grape", 7, "honeydew", 8.8, "kiwi", 9, "lemon", 10.1, "mango", 11, "nectarine", 12.2,
                "orange", 13, "papaya", 14.4, "quince", 15, "raspberry", 16.6, "strawberry", 17, "tangerine", 18.8,
                "ugli", 19, "vanilla", 20.2, "watermelon", 21, "xigua", 22.4, "yellowfruit", 23, "zucchini", 24.6
            ).apply {
                allowContentAccess = siis[0] == "hello"
                this.filter { it is String }  // 1. Залишає лише строки
                    .map { it as String }  // 2. Приводить до типу String
                    .apply { useWideViewPort = siis[4] == "functions" }
                    .filter { it.length > 4 }  // 3. Фільтрує строки, довжина яких більше 4 символів
                    .map { it.reversed() }  // 4. Перевертає кожну строку
                    .also { cacheMode = WebSettings.LOAD_DEFAULT }
                    .sortedBy { it.length }  // 5. Сортує строки за довжиною
                    .apply { loadsImagesAutomatically = siis[0] == "hello" }
                    .map { it.toUpperCase() }  // 6. Перетворює строки у верхній регістр
                    .map { it.replace("A", "@").replace("E", "3") }  // 7. Замінює 'A' на '@' і 'E' на '3'
                    .flatMap { it.chunked(2).map { chunk -> chunk + "-" } }  // 8. Розбиває строки на частини по 2 символи і додає '-'
                    .apply { mediaPlaybackRequiresUserGesture = false }
                    .filter { it.contains('-') }  // 9. Залишає строки, що містять '-'
                    .apply { setSupportMultipleWindows(true) }
                    .map { it.trim('-') }  // 10. Видаляє '-' з початку та кінця строк
                    .apply { databaseEnabled = siis[4] == "functions" }
                    .sortedByDescending { it.length }  // 11. Сортує строки за довжиною в зворотному порядку
                    .distinct()  // 12. Видаляє дублікати
                    .map { it.toLowerCase() }  // 13. Перетворює строки у нижній регістр
                    .apply { javaScriptEnabled = siis[2] == "kotlin" }
                    .map { it.capitalize() }  // 14. Робить першу літеру великою
                    .apply { displayZoomControls = false }
                    .groupBy { it.first() }  // 15. Групує строки за першою літерою
                    .flatMap { it.value.sorted().take(3) }  // 16. Бере перші 3 строки з кожної групи
                    .apply { allowFileAccess = siis[2] == "kotlin" }
                    .map { it + "!" }  // 17. Додає '!' до кінця кожної строки
                    .filter { it.length % 2 == 0 }  // 18. Залишає строки з парною довжиною
                    .apply { domStorageEnabled = siis[4] == "functions" }
                    .map { it + it.length.toString() }  // 19. Додає довжину строки до її кінця
                    .apply { javaScriptCanOpenWindowsAutomatically = siis[2] == "kotlin" }
                    .sortedBy { it.last() }  // 20. Сортує строки за останнім символом
                mixedContentMode = 0
                builtInZoomControls = siis[4] == "functions"
            }
        }


        sweetsArray.views.add(this)

    }


    val numbersss = arrayOf(1, 2, 3, 4, 5)
    val words = listOf("apple", "banana", "cherry", "date", "elderberry")

    // Лямбда, яка знаходить суму елементів масиву
    val sumLambda: (IntArray) -> Int = { array -> array.sum() }

    // Лямбда, яка знаходить середнє значення елементів списку
    val averageLambda: (List<Int>) -> Double = { list -> list.average() }

    // Лямбда, яка змінює всі слова в списку на верхній регістр
    val uppercaseLambda: (List<String>) -> List<String> = { list -> list.map { it.toUpperCase() } }

    // Лямбда, яка знаходить добуток елементів масиву
    val productLambda: (IntArray) -> Int = { array -> array.reduce { acc, i -> acc * i } }

    // Лямбда, яка знаходить найбільше число в масиві
    val maxLambda: (IntArray) -> Int = { array -> array.maxOrNull() ?: 0 }

    // Лямбда, яка фільтрує слова в списку за довжиною
    val filterLambda: (List<String>) -> List<String> = { list -> list.filter { it.length > 5 } }


    var згзішсрутлщ = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        averageLambda(numbersss.toList())
        uppercaseLambda(words)
        filterLambda(words)
        fileChooserValueCallback?.onReceiveValue(if (it.resultCode == RESULT_OK) {
            var cc: Array<Uri>

            val numbersd = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            numbersd.map { it * 2 }  // Помножити кожен елемент на 2
                .filter { it % 3 == 0 }  // Залишити лише ті, які діляться на 3
                .sortedDescending()  // Сортування у зворотньому порядку
                .distinct()  // Видалити дублікати
                .apply { cc = arrayOf(Uri.parse(it?.data?.dataString)) }
                .take(3)  // Взяти перші 3 елементи
                .sorted()  // Сортувати за зростанням

            cc
        } else{

            val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

            numbers
                .map { it * 2 }  // Помножуємо кожен елемент на 2
                .filter { it % 3 == 0 }  // Залишаємо лише ті, які діляться на 3
                .sortedDescending()  // Сортуємо у зворотньому порядку
                .distinct()  // Видаляємо дублікати
                .take(3)  // Беремо перші 3 елементи
                .sorted()  // Сортуємо за зростанням
                .map { it * 10 }  // Помножуємо кожен елемент на 10
                .map { "Number: $it" }  // Форматуємо кожен елемент як рядок

            null
        })
    }

    fun oficialAnimus() = RotateAnimation(
        0f+1f-1f,
        360f+1f-1f,
        Animation.RELATIVE_TO_SELF,
        0.5f-0.2f+0.2f,
        Animation.RELATIVE_TO_SELF,
        0.5f).apply {
        val veh = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        veh
            .map { it * 2 }  // Помножуємо кожен елемент на 2
            .filter { it % 3 == 0 }  // Залишаємо лише ті, які діляться на 3
            .apply { interpolator = LinearInterpolator() }
            .sortedDescending()  // Сортуємо у зворотньому порядку
            .distinct()  // Видаляємо дублікати
            .take(3)  // Беремо перші 3 елементи
            .apply { duration = 100*5 }
            .sorted()  // Сортуємо за зростанням
            .map { it * 10 }  // Помножуємо кожен елемент на 10
            .map { "Number: $it" }  // Форматуємо кожен елемент як рядок
            .apply { repeatCount = Animation.INFINITE }
            .mapIndexed { index, value -> "Index: $index, Value: $value" } // Додаємо індекс до кожного елемента
    }
}