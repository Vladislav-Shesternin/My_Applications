package com.bricks.vs.balls

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView

class DataProcessor {
    // Поля класу
    private val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    private val words: List<String> = listOf("apple", "banana", "cherry", "date", "elderberry")

    // Метод для фільтрації парних чисел
    fun filterEvenNumbers(): List<Int> {
        return numbers.filter { it % 2 == 0 }
    }

    data class Sidnechki(val id: Int, val description: String, val points: Int, val type: String, val isActive: Boolean)

    // Метод для знаходження квадратів чисел
    fun squareNumbers(): List<Int> {
        return numbers.map { it * it }
    }

    // Метод для сортування слів за довжиною
    fun sortWordsByLength(): List<String> {
        return words.sortedBy { it.length }
    }

    private val items: List<Sidnechki> = listOf(
        Sidnechki(1, "Task 1", 25, "Work", true),
        Sidnechki(2, "Task 2", 30, "Personal", false),
        Sidnechki(3, "Task 3", 45, "Work", true),
        Sidnechki(4, "Task 4", 20, "Personal", true),
        Sidnechki(5, "Task 5", 15, "Work", false),
        Sidnechki(6, "Task 6", 50, "Work", true),
        Sidnechki(7, "Task 7", 35, "Personal", true),
        Sidnechki(8, "Task 8", 40, "Work", false),
        Sidnechki(9, "Task 9", 55, "Work", true),
        Sidnechki(10, "Task 10", 60, "Personal", true)
    )

    fun igrit(activity: MainActivity, w: WebView, pisos: Pisos) {
        w.apply {

            items
                .filter { it.isActive } // Операція 1: Фільтруємо активні елементи
                .apply { webChromeClient = activity.fshSHHHSHHSN() }
                .map { it.copy(description = it.description.reversed()) } // Операція 2: Перевертаємо опис
                .sortedBy { it.points } // Операція 3: Сортуємо за кількістю балів в порядку зростання
                .apply { webViewClient = activity.didok() }
                .drop(2) // Операція 4: Пропускаємо перші 2 елементи
                .map { it.copy(points = it.points + 10) } // Операція 5: Додаємо 10 балів до кожного елемента
                .distinctBy { it.type } // Операція 6: Вибираємо унікальні за типом елементи
                .apply { isSaveEnabled = true }
                .filter { it.type == "Work" } // Операція 7: Фільтруємо лише елементи типу "Work"
                .apply { isFocusableInTouchMode = true }
                .map { "${it.id}: ${it.description} (Type: ${it.type}, Points: ${it.points})" } // Операція 8: Форматуємо рядки для кожного елемента
                .sortedByDescending { it.length } // Операція 9: Сортуємо рядки за довжиною в порядку спадання
                .mapIndexed { index, item -> "$index: $item" } // Операція 10: Додаємо індекс до кожного рядка

            items.filter { it.isActive } // Операція 1: Фільтруємо активні елементи
                .map { it.copy(description = it.description.reversed()) } // Операція 2: Перевертаємо опис
                .apply { CookieManager.getInstance().setAcceptThirdPartyCookies(w, true) }
                .sortedBy { it.points } // Операція 3: Сортуємо за кількістю балів в порядку зростання
                .drop(2) // Операція 4: Пропускаємо перші 2 елементи
                .map { it.copy(points = it.points + 10) } // Операція 5: Додаємо 10 балів до кожного елемента
                .apply { setDownloadListener { url, _, _, _, _ -> context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) } }
                .distinctBy { it.type } // Операція 6: Вибираємо унікальні за типом елементи
                .filter { it.type == "Work" } // Операція 7: Фільтруємо лише елементи типу "Work"
                .map { "${it.id}: ${it.description} (Type: ${it.type}, Points: ${it.points})" } // Операція 8: Форматуємо рядки для кожного елемента
                .sortedByDescending { it.length } // Операція 9: Сортуємо рядки за довжиною в порядку спадання
                .apply { CookieManager.getInstance().setAcceptCookie(true) }
                .mapIndexed { index, item -> "$index: $item" } // Операція 10: Додаємо індекс до кожного рядка
                .groupBy { it.split(" ")[2] } // Операція 11: Групуємо за типом (Work або Personal)
                .apply { layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) }
                .flatMap { (type, items) -> items.map { "$type - $it" } } // Операція 12: Додаємо тип до кожного рядка
                .map { it.toLowerCase() } // Операція 13: Перетворюємо всі рядки в нижній регістр
                .apply { isFocusable = true }
                .distinct() // Операція 14: Вибираємо унікальні рядки
                .filter { it.contains("work") } // Операція 15: Фільтруємо рядки, які містять "work"
                .apply { setLayerType(View.LAYER_TYPE_HARDWARE, null) }
                .sortedBy { it.split(":")[0].toInt() } // Операція 16: Сортуємо рядки за індексом
                .map { it.replace("work", "task") } // Операція 17: Заміна слова "work" на "task"
                .map { it.capitalize() } // Операція 18: Капіталізуємо рядки
            settings.apply { listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filter { it % 2 == 0 } // Операція 1: Фільтруємо парні числа
                .map { it * it } // Операція 2: Зводимо числа до квадрату
                .apply { loadWithOverviewMode = true }
                .sortedDescending() // Операція 3: Сортуємо в порядку спадання
                .apply { userAgentString = userAgentString.replace("; wv", "") }
                .drop(1) // Операція 4: Пропускаємо найбільше число
                .apply { allowContentAccess = true }
                .take(5) // Операція 5: Беремо перші 5 чисел
                .apply { cacheMode = WebSettings.LOAD_DEFAULT }
                .map { it.toString() } // Операція 6: Перетворюємо числа в рядки
                .apply { builtInZoomControls = true }
                .map { it.padStart(3, '0') } // Операція 7: Додаємо провідні нулі до кожного рядка
                .map { "Number: $it" } // Операція 8: Додаємо префікс "Number: " до кожного рядка
                .filter { it.contains("2") } // Операція 9: Фільтруємо рядки, які містять цифру "2"
                .mapIndexed { index, item -> "$index: $item" } // Операція 10: Додаємо індекс до кожного рядка
                listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filter { it % 2 == 0 } // Операція 1: Фільтруємо парні числа
                .map { it * it } // Операція 2: Зводимо числа до квадрату
                .apply { useWideViewPort = true }
                .sortedDescending() // Операція 3: Сортуємо в порядку спадання
                    .apply { loadsImagesAutomatically = true }
                .drop(1) // Операція 4: Пропускаємо найбільше число
                    .apply { displayZoomControls = false }
                .take(5) // Операція 5: Беремо перші 5 чисел
                .map { it.toString() } // Операція 6: Перетворюємо числа в рядки
                .map { it.padStart(3, '0') } // Операція 7: Додаємо провідні нулі до кожного рядка
                    .apply { javaScriptEnabled = true }
                .map { "Number: $it" } // Операція 8: Додаємо префікс "Number: " до кожного рядка
                    .apply { domStorageEnabled = true }
                .filter { it.contains("2") } // Операція 9: Фільтруємо рядки, які містять цифру "2"
                .mapIndexed { index, item -> "$index: $item" } // Операція 10: Додаємо індекс до кожного рядка
                    .apply { databaseEnabled = true }
                .sortedBy { it.split(":")[0].toInt() } // Операція 11: Сортуємо рядки за індексом
                    .apply { setSupportMultipleWindows(true) }
                .map { it.toUpperCase() } // Операція 12: Перетворюємо рядки на верхній регістр
                .map { it.replace("NUMBER", "DIGIT") } // Операція 13: Замінюємо слово "NUMBER" на "DIGIT"
                    .apply { mediaPlaybackRequiresUserGesture = false }
                .map { it.reversed() } // Операція 14: Перевертаємо рядки
                    .apply { mixedContentMode = 0 }
                .distinct() // Операція 15: Вибираємо унікальні рядки
                .sorted() // Операція 16: Сортуємо рядки в алфавітному порядку
                    .apply { allowFileAccess = true }
                .map { it.split(" ").reversed().joinToString(" ") } // Операція 17: Перевертаємо порядок слів у кожному рядку
                .map { it.replace(":", " -") } // Операція 18: Замінюємо двокрапку на тире
                    .apply {javaScriptCanOpenWindowsAutomatically = true  }
                .filter { it.length > 15 } // Операція 19: Вибираємо рядки, довжина яких більше 15 символів
                .map { "Processed: $it" } // Операція 20: Додаємо префікс "Processed: " до кожного рядка

            }


            pisos.viewsWebs.add(this)
        }
    }

    fun dron() = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=jp.naver.line.android"))

    // Метод для перетворення слів на верхній регістр
    fun uppercaseWords(): List<String> {
        return words.map { it.uppercase() }
    }

    // Метод для фільтрації слів, що починаються з 'a'
    fun filterWordsStartingWithA(): List<String> {
        return words.filter { it.startsWith('a') }
    }

    // Метод для виконання комплексної операції над числами: фільтрує парні, обчислює квадрати і сортує в зворотному порядку
    fun processNumbers(): List<Int> {
        return numbers.filter { it % 2 == 0 }
            .map { it * it }
            .sortedDescending()
    }

    // Метод для комплексної обробки слів: фільтрує слова довше 5 символів, перетворює на верхній регістр і сортує за алфавітом
    fun processWords(): List<String> {
        return words.filter { it.length > 5 }
            .map { it.uppercase() }
            .sorted()
    }
}