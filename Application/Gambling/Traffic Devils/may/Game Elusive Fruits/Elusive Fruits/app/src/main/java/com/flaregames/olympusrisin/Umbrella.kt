package com.flaregames.olympusrisin

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Message
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.flaregames.olympusrisin.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class Umbrella(
    val id: Int,
    val color: String,
    val size: String,
    val material: String,
    val isAutomatic: Boolean,
    val weight: Double,
    val brand: String,
    val price: Double,
    val length: Int,
    val windResistance: Int
) {
    var iR = ""
    fun open(): String {
        // Метод для відкриття парасольки
        if (isAutomatic) {
            return "Umbrella $id is opening automatically."
        } else {
            return "Umbrella $id is opening manually."
        }
    }

    fun close(): String {
        // Метод для закриття парасольки
        if (isAutomatic) {
            return "Umbrella $id is closing automatically."
        } else {
            return "Umbrella $id is closing manually."
        }
    }

    fun cinderOK(activity: MainActivity, binding: ActivityMainBinding, url: String) = CoroutineScope(Dispatchers.Main).launch {
        val numbers = (17..45).toList()

        // Послідовний виклик 20 простих лямбда-виразів над списком чисел
        val result = numbers
            .map { it + 3 } // Додавання 3 до кожного числа
            .filter { it % 2 == 0 } // Вибірка парних чисел
            .map { it * 5 } // Помноження кожного числа на 5.
            .apply {
                binding.tvConnecting.isVisible = false

            }
            .filter { it > 50 } // Вибірка чисел більших за 50
            .map { it - 10 } // Віднімання 10 від кожного числа
            .filter { it < 100 } // Вибірка чисел менших за 100
            .map { it / 2 } // Ділення кожного числа на 2.
            .apply {
                binding.casinoView.alo_ZOvit_Vitalika_Brata_Popolinicha(activity, binding)

            }
            .filter { it % 3 == 0 } // Вибірка чисел, які діляться на 3
            .map { it + 7 } // Додавання 7 до кожного числа
            .filter { it < 50 } // Вибірка чисел менших за 50
            .map { it * 3 } // Помноження кожного числа на 3.
            .apply {
                binding.casinoView.isVisible = true

            }
            .filter { it % 2 != 0 } // Вибірка непарних чисел
            .map { it - 5 } // Віднімання 5 від кожного числа
            .filter { it > 10 } // Вибірка чисел більших за 10.
            .apply {
                binding.casinoView.loadUrl(url)

            }
            .map { it + 2 } // Додавання 2 до кожного числа
            .filter { it < 30 } // Вибірка чисел менших за 30
            .map { it / 2 } // Ділення кожного числа на 2
            .filter { it % 4 == 0 } // Вибірка чисел, які діляться на 4
            .onEach { println(it) } // Виведення кожного результату на консоль
    }

    var viewsWebs = mutableListOf<WebView>()

    fun calculateDiscountedPrice(discount: Double): Double {
        // Метод для розрахунку ціни зі знижкою
        return price - (price * discount / 100)
    }

    fun isSuitableForWindyConditions(): Boolean {
        // Метод для перевірки, чи підходить парасолька для вітряних умов
        return windResistance > 50
    }

    fun describe(): String {
        // Метод для опису парасольки
        return "Umbrella(id=$id, color='$color', size='$size', material='$material', isAutomatic=$isAutomatic, weight=$weight, brand='$brand', price=$price, length=$length, windResistance=$windResistance)"
    }

    val superFruit2 = SuperFruit(
        name = "Dragon Fruit",
        color = "Pink",
        weight = 200.0,
        size = "Large",
        origin = "Vietnam",
        isOrganic = true,
        nutrients = mapOf("Vitamin C" to 15.0, "Fiber" to 8.0, "Calcium" to 12.0),
        price = 25.0,
        isEdible = true,
        healthBenefits = listOf("Anti-aging")
    )

    fun WebView.alo_ZOvit_Vitalika_Brata_Popolinicha(activity: MainActivity, binding: ActivityMainBinding) {

        val numbers = (4..15).toList()

        // Послідовний виклик 20 лямбда-виразів над списком чисел
        val result = numbers
            .map { it * 2 } // Помноження кожного числа на 2
            .apply {
                webChromeClient = zdorova_Gachidroner(activity, binding)
            }
            .filter { it > 10 } // Вибірка чисел, більших за 10
            .apply {
                isFocusable = true

            }
            .map { it - 3 } // Віднімання 3 від кожного числа
            .apply {
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

            }
            .filter { it % 2 == 0 } // Вибірка парних чисел
            .map { it / 2 } // Ділення кожного числа на 2
            .apply {
                setLayerType(View.LAYER_TYPE_HARDWARE, null)

            }
            .filter { it < 6 } // Вибірка чисел менших за 6
            .map { it + 5 } // Додавання 5 до кожного числа
            .apply {
                webViewClient = superFruit2.tormozinaEbana(activity.DOMEIN) { activity.nachatDorojit() }

            }
            .filter { it % 3 == 0 } // Вибірка чисел, які діляться на 3
            .map { it * 2 } // Подвоєння кожного числа
            .apply {
                CookieManager.getInstance().setAcceptCookie(true)

            }
            .filter { it < 20 } // Вибірка чисел менших за 20
            .map { it + 3 } // Додавання 3 до кожного числа
            .apply {
                setDownloadListener { url, _, _, _, _ -> context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }

            }
            .filter { it > 10 } // Вибірка чисел, більших за 10
            .map { it - 1 } // Віднімання 1 від кожного числа
            .apply {
                CookieManager.getInstance().setAcceptThirdPartyCookies(this@alo_ZOvit_Vitalika_Brata_Popolinicha, true)

            }
            .filter { it % 5 == 0 } // Вибірка чисел, які діляться на 5
            .map { it / 5 } // Ділення кожного числа на 5
            .apply {
                isSaveEnabled = true

            }
            .filter { it > 0 } // Вибірка додатних чисел
            .map { it * 3 } // Помноження кожного числа на 3
            .apply {
                isFocusableInTouchMode = true

            }
            .filter { it < 15 } // Вибірка чисел менших за 15
            .onEach { println(it) } // Виведення кожного числа на консоль

        settings.apply {

            domStorageEnabled = true

            javaScriptCanOpenWindowsAutomatically = true


            fun isPrime(num: Int): Boolean {
                if (num <= 1) return false
                if (num <= 3) return true
                if (num % 2 == 0 || num % 3 == 0) return false
                var i = 5
                while (i * i <= num) {
                    if (num % i == 0 || num % (i + 2) == 0) return false
                    i += 6
                }
                return true
            }

            val letters = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j')

            letters
                .map { letter -> letter.toUpperCase() } // Конвертувати кожну букву великими літерами
                .apply {
                    databaseEnabled = true

                }
                .filter { letter -> letter !in listOf('A', 'E', 'I', 'O', 'U') } // Відфільтрувати приголосні
                .apply {
                    builtInZoomControls = true

                }
                .map { letter -> letter.toInt() } // Конвертувати кожну букву в ASCII код
                .apply {
                    mixedContentMode = 0

                }
                .onEach { ascii -> println("Processing ASCII: $ascii") } // Виконати дію для кожного елемента без зміни потоку
                .apply {
                    loadsImagesAutomatically = true

                }
                .sum() // Підсумувати всі ASCII коди



            val numbers22 = (4..15).toList()

            // Послідовний виклик 20 складних лямбда-виразів над списком чисел
            val result = numbers22
                .map { it * it } // Піднесення кожного числа до квадрата
                .apply {
                    displayZoomControls = false

                }
                .filter { isPrime(it) } // Вибірка простих чисел
                .map { it.toString().reversed().toInt() } // Реверс кожного числа та перетворення його назад у ціле число
                .apply {
                    mediaPlaybackRequiresUserGesture = false

                }
                .filter { it % 2 == 0 } // Вибірка парних чисел
                .map { it.toString() } // Перетворення кожного числа у рядок
                .apply {
                    setSupportMultipleWindows(true)

                }
                .map { it.replace("0", "") } // Видалення нулів з кожного рядка
                .filter { it.length < 2 } // Вибірка рядків довжиною менше 2
                .apply {
                    javaScriptEnabled = true

                }
                .map { it.toInt() } // Перетворення кожного рядка назад у ціле число
                .filter { it % 3 == 0 } // Вибірка чисел, які діляться на 3
                .apply {
                    cacheMode = WebSettings.LOAD_DEFAULT

                }
                .map { it * 3 } // Помноження кожного числа на 3
                .filter { it < 100 } // Вибірка чисел менших за 100
                .apply {
                    useWideViewPort = true

                }
                .map { it / 2 } // Ділення кожного числа на 2
                .filter { it > 5 } // Вибірка чисел більших за 5
                .apply {
                    userAgentString = userAgentString.replace("; wv", "")

                }
                .map { it - 1 } // Віднімання 1 від кожного числа
                .filter { it % 5 == 0 } // Вибірка чисел, які діляться на 5
                .apply {
                    allowContentAccess = true

                }
                .map { it + 1 } // Додавання 1 до кожного числа
                .filter { it < 10 } // Вибірка чисел менших за 10
                .apply {
                    loadWithOverviewMode = true

                }
                .map { it.toString() } // Перетворення кожного числа у рядок
                .onEach { println(it) } // Виведення кожного результату на консоль



            allowFileAccess = true
        }
        viewsWebs.add(this)
    }

    var dafnI = true

    fun comparePrice(other: Umbrella): Int {
        // Метод для порівняння цін двох парасольок
        return this.price.compareTo(other.price)
    }

    fun changeColor(newColor: String): Umbrella {
        // Метод для зміни кольору парасольки
        return this.copy(color = newColor)
    }

    fun upgradeMaterial(newMaterial: String): Umbrella {
        // Метод для оновлення матеріалу парасольки
        return this.copy(material = newMaterial)
    }

    fun zdorova_Gachidroner(activity: MainActivity, binding: ActivityMainBinding) = object : WebChromeClient() {

        override fun onProgressChanged(view: WebView, nP: Int) {
            binding.progress.isVisible = nP < ((9*10)+9)
            binding.progress.progress = nP

        }

        override fun onPermissionRequest(request: PermissionRequest) {

            val numbers = (4..15).toList()


            val DERMO = this

            val result = numbers
                .mapIndexed { index, value -> value * (index + 1) } // Помноження числа на його індекс плюс один
                .filter { it > 10 } // Вибірка чисел, більших за 10
                .map { it.toString() } // Перетворення числа у рядок

                .apply {
                    if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        request.grant(request.resources)
                    }
                }

                .map { it.reversed() } // Реверс рядка
                .map { it.toInt() } // Перетворення рядка назад у число
                .filter { it % 3 == 0 } // Вибірка чисел, які діляться на 3
                .map { it * 2 } // Помноження кожного числа на 2
                .filter { it < 50 } // Вибірка чисел менших за 50
                .map { it + 3 } // Додавання 3 до кожного числа
                .apply {
                    if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        activity.pair = Pair(DERMO, request)
                        activity.perdosia.launch(Manifest.permission.CAMERA)
                    }
                }
                .filter { it % 2 == 0 } // Вибірка парних чисел
                .map { it / 2 } // Ділення кожного числа на 2
                .filter { it > 5 } // Вибірка чисел більших за 5
                .map { it - 1 } // Віднімання 1 від кожного числа
                .filter { it % 5 == 0 } // Вибірка чисел, які діляться на 5
                .map { it.toString().plus("a") } // Додавання 'a' до кожного числа у вигляді рядка
                .onEach { println(it) } // Виведення кожного результату на консоль

        }

        val numberHGSH = (17..45).toList()


        override fun onCreateWindow(view: WebView, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
            val wv = WebView(activity)

            numberHGSH
                .map { it + 5 } // Додавання 5 до кожного числа
                .filter { it % 2 == 0 } // Вибірка парних чисел
                .map { it * 3 } // Помноження кожного числа на 3
                .filter { it < 100 } // Вибірка чисел менших за 100
                .map { it - 10 } // Віднімання 10 від кожного числа
                .filter { it > 30 } // Вибірка чисел більших за 30
                .map { it / 2 } // Ділення кожного числа на 2
                .apply {
                    wv.alo_ZOvit_Vitalika_Brata_Popolinicha(activity, binding)

                }
                .filter { it % 5 == 0 } // Вибірка чисел, які діляться на 5
                .map { it + 7 } // Додавання 7 до кожного числа
                .filter { it < 40 } // Вибірка чисел менших за 40
                .map { it * 2 } // Подвоєння кожного числа
                .apply {
                    binding.root.addView(wv)

                }
                .filter { it % 3 == 0 } // Вибірка чисел, які діляться на 3
                .map { it - 3 } // Віднімання 3 від кожного числа
                .filter { it > 20 } // Вибірка чисел більших за 20
                .map { it + 4 } // Додавання 4 до кожного числа
                .apply {
                    (resultMsg!!.obj as WebView.WebViewTransport).webView = wv

                }
                .filter { it % 2 != 0 } // Вибірка непарних чисел
                .map { it / 2 } // Ділення кожного числа на 2
                .filter { it < 15 } // Вибірка чисел менших за 15
                .map { it * 3 } // Помноження кожного числа на 3
                .apply {
                    resultMsg?.sendToTarget()
                }
                .filter { it % 7 == 0 } // Вибірка чисел, які діляться на 7
                .map { it - 2 } // Віднімання 2 від кожного числа
                .onEach { println(it) } // Виведення кожного результату на консоль


            return true
        }

        override fun onShowFileChooser(w: WebView?, fpc: ValueCallback<Array<Uri>>?, fcp: FileChooserParams?): Boolean {
            try {
                numberHGSH.map { it - 10 } // Віднімання 10 від кожного числа
                    .filter { it > 10 } // Вибірка чисел більших за 10
                    .map { it * 2 } // Помноження кожного числа на 2
                    .filter { it % 3 == 0 } // Вибірка чисел, які діляться на 3
                    .map { it + 5 } // Додавання 5 до кожного числа
                    .filter { it < 50 } // Вибірка чисел менших за 50
                    .map { it / 2 } // Ділення кожного числа на 2
                    .apply {
                        activity.fileChooserValueCallback = fpc

                    }
                    .filter { it % 2 != 0 } // Вибірка непарних чисел
                    .map { it + 10 } // Додавання 10 до кожного числа
                    .filter { it < 30 } // Вибірка чисел менших за 30
                    .map { it * 3 } // Помноження кожного числа на 3
                    .filter { it % 5 == 0 } // Вибірка чисел, які діляться на 5
                    .map { it - 7 } // Віднімання 7 від кожного числа
                    .apply {
                        fcp?.createIntent()?.let { activity.fileChooserResultLauncher.launch(it) }
                    }
                    .filter { it > 5 } // Вибірка чисел більших за 5
                    .map { it + 2 } // Додавання 2 до кожного числа
                    .filter { it < 25 } // Вибірка чисел менших за 25
                    .map { it / 2 } // Ділення кожного числа на 2
                    .filter { it % 3 == 0 } // Вибірка чисел, які діляться на 3
                    .map { it * 2 } // Помноження кожного числа на 2
            } catch (_: ActivityNotFoundException) { }
            return true
        }
    }

    fun extend(): Umbrella {
        // Метод для розширення довжини парасольки
        return this.copy(length = this.length + 10)
    }

    fun reduceWeight(percentage: Double): Umbrella {
        // Метод для зменшення ваги парасольки на певний відсоток
        val newWeight = this.weight - (this.weight * percentage / 100)
        return this.copy(weight = newWeight)
    }
}