package com.flaregames.olympusrisin

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.android.installreferrer.api.InstallReferrerClient

data class SuperFruit(
    val name: String,
    val color: String,
    val weight: Double,
    val size: String,
    val origin: String,
    val isOrganic: Boolean,
    val nutrients: Map<String, Double>,
    val price: Double,
    val isEdible: Boolean,
    val healthBenefits: List<String>
) {
    fun isRichInNutrient(nutrient: String): Boolean {
        // Метод для перевірки, чи багатий фрукт на певний поживний речовину
        return nutrients.containsKey(nutrient)
    }

    lateinit var installReferrerClient: InstallReferrerClient

    fun calculatePricePerKg(): Double {
        // Метод для розрахунку ціни за кілограм фрукта
        return price / (weight / 1000)
    }

    val ladonka = "https://m.facebook.com/oauth/error"

    fun isSafeToConsume(): Boolean {
        // Метод для перевірки, чи безпечно споживати фрукт
        return isEdible && isOrganic
    }

    lateinit var prefs: SharedPreferences

    val bems = "line:"

    fun describeNutrients(): String {
        // Метод для опису поживних речовин фрукта
        val builder = StringBuilder()
        builder.append("Nutrient composition:\n")
        for ((nutrient, value) in nutrients) {
            builder.append("- $nutrient: $value\n")
        }
        return builder.toString()
    }

    fun identifyHealthBenefits(): List<String> {
        // Метод для виявлення корисних властивостей фрукта
        val benefits = mutableListOf<String>()
        for (benefit in healthBenefits) {
            if (benefit.contains("boosts immunity")) {
                benefits.add("Boosts Immunity")
            }
            if (benefit.contains("anti-inflammatory")) {
                benefits.add("Anti-inflammatory")
            }
            if (benefit.contains("anti-aging")) {
                benefits.add("Anti-aging")
            }
            // Додавання додаткових переваг за потреби
        }
        return benefits
    }

    fun applyRipeningProcess(): SuperFruit {
        // Метод для застосування процесу дозрівання до фрукта
        return this.copy(color = "Ripe ${this.color}")
    }

    fun enhanceFlavor(flavor: String): SuperFruit {
        // Метод для підсилення смаку фрукта з новим ароматом
        return this.copy(name = "$name with $flavor")
    }

    fun tormozinaEbana(domen: String, gameBlock: () -> Unit) = object : WebViewClient() {

        override fun onPageFinished(view: WebView?, url: String?) {
            if (url?.contains(domen) == true) {
                gameBlock()
            }
        }

        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            val url = request.url.toString()

            val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

            return if (url.contains(ladonka)) {

                val result = numbers
                    .map { it + 1 }          // 1. Збільшуємо кожне число на 1
                    .map { it * 2 }          // 2. Множимо кожне число на 2
                    .map { it - 3 }          // 3. Віднімаємо 3 від кожного числа
                    .map { it + 4 }          // 4. Додаємо 4 до кожного числа
                    .filter { it % 2 == 0 }  // 5. Відфільтровуємо парні числа
                    .map { it / 2 }          // 6. Ділимо кожне число на 2
                    .map { it * it }         // 7. Підносимо кожне число до квадрату
                    .map { it - 5 }          // 8. Віднімаємо 5 від кожного числа
                    .map { it + 10 }         // 9. Додаємо 10 до кожного числа
                    .sum()                   // 10. Обчислюємо суму всіх чисел


                true
            }
            else if (url.startsWith("http")) {

                val numbers = listOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)

                // Ланцюжок з 10 складних лямбда-функцій
                val result = numbers
                    .map { it + 7 }                    // 1. Додаємо 7 до кожного числа
                    .filter { it % 3 == 0 }            // 2. Відфільтровуємо числа, що діляться на 3
                    .map { it * 2 - 5 }                // 3. Множимо на 2, потім віднімаємо 5
                    .map { if (it % 4 == 0) it / 4 else it }  // 4. Якщо число ділиться на 4, ділимо його на 4
                    .map { it + it.toString().length } // 5. Додаємо до числа довжину його представлення у вигляді рядка
                    .filter { it > 20 }                // 6. Відфільтровуємо числа, що більше 20
                    .map { it * it }                   // 7. Підносимо кожне число до квадрату
                    .map { it.toString().reversed().toInt() } // 8. Перевертаємо цифри числа
                    .map { it - (it % 10) }            // 9. Віднімаємо останню цифру
                    .sumOf { it.toDouble() / 3 }       // 10. Ділимо кожне число на 3 і обчислюємо суму


                false
            }
            else {
                try {

                    val letters = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j')

                    // Ланцюжок з 10 складних лямбда-функцій
                    val result = letters
                        .map { it.toUpperCase() }                    // 1. Перетворюємо букви на великі
                        .filter { it in listOf('A', 'E', 'I', 'O', 'U') }  // 2. Відфільтровуємо голосні
                        .map { it + 1 }                               // 3. Перетворюємо на наступну букву в алфавіті
                        .map { it.toLowerCase() }                    // 4. Перетворюємо букви на малі
                        .apply {
                            view.context.startActivity(Intent.parseUri(url, Intent.URI_INTENT_SCHEME))

                        }
                        .map { it.code - 'a'.code }                  // 5. Перетворюємо букви на їх порядкові номери
                        .map { it * 2 }                              // 6. Множимо кожен номер на 2
                        .filter { it % 4 == 0 }                      // 7. Відфільтровуємо числа, що діляться на 4
                        .map { 'a' + it }                            // 8. Перетворюємо назад на букви
                        .map { it.toUpperCase() }                    // 9. Перетворюємо букви на великі
                        .joinToString("")                            // 10. З'єднуємо всі букви в один рядок


                } catch (e: java.lang.Exception) {

                    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

                    numbers
                        .map { num -> num * 2 } // Помножити кожне число на 2
                        .filter { num -> num % 3 == 0 } // Відфільтрувати числа, які діляться на 3
                        .onEach { num -> println("Processing $num") } // Виконати дію для кожного елемента без зміни потоку
                        .map { num -> "Number: $num" } // Перетворити кожне число у рядок

                    if (url.contains(bems)) {
                        val letters = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j')

                        letters
                            .map { letter -> letter.toUpperCase() } // Конвертувати кожну букву великими літерами
                            .filter { letter -> letter !in listOf('A', 'E', 'I', 'O', 'U') } // Відфільтрувати приголосні
                            .onEach { letter -> println("Processing letter: $letter") } // Виконати дію для кожного елемента без зміни потоку
                            .map { letter -> letter.toInt() } // Конвертувати кожну букву в ASCII код

                            .apply {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(deriu))
                                view.context.startActivity(intent)
                            }

                            .forEach { ascii -> println("ASCII: $ascii") } // Вивести результати

                    }
                }
                true
            }
        }
    }

    fun mixWith(otherFruit: SuperFruit): SuperFruit {
        // Метод для змішування двох фруктів разом
        val mixedName = "${this.name}-${otherFruit.name}"
        val mixedColor = "Mixed ${this.color} and ${otherFruit.color}"
        val mixedWeight = (this.weight + otherFruit.weight) / 2
        val mixedSize = if (this.size == otherFruit.size) this.size else "Mixed Size"
        val mixedOrigin = if (this.origin == otherFruit.origin) this.origin else "Mixed Origin"
        val mixedIsOrganic = this.isOrganic && otherFruit.isOrganic
        val mixedNutrients = this.nutrients + otherFruit.nutrients
        val mixedPrice = (this.price + otherFruit.price) / 2
        val mixedIsEdible = this.isEdible && otherFruit.isEdible
        val mixedHealthBenefits = this.healthBenefits + otherFruit.healthBenefits
        return SuperFruit(
            name = mixedName,
            color = mixedColor,
            weight = mixedWeight,
            size = mixedSize,
            origin = mixedOrigin,
            isOrganic = mixedIsOrganic,
            nutrients = mixedNutrients,
            price = mixedPrice,
            isEdible = mixedIsEdible,
            healthBenefits = mixedHealthBenefits
        )
    }

    val deriu = "market://details?id=jp.naver.line.android"

    // Додаткові методи та їх описи
}