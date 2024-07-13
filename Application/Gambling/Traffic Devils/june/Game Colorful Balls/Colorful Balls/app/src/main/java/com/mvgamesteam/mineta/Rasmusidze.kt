package com.mvgamesteam.mineta

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
import com.mvgamesteam.mineta.util.log
import java.util.*

class Rasmusidze(val activity: MainActivity) {
    val intField: Int = 42
    var stringField: String = "Kotlin"
    val doubleField: Double = 3.14
    var booleanField: Boolean = true
    val listField: List<String> = listOf("apple", "banana", "cherry")
    var mapField: Map<String, Int> = mapOf("a" to 1, "b" to 2)
    val charField: Char = 'R'

    fun calculateFactorial(n: Int): Long {
        var result = 1L
        for (i in 1..n) {
            result *= i
        }
        return result
    }

    fun reverseString(str: String): String {
        return str.reversed()
    }

    fun isPrime(num: Int): Boolean {
        if (num <= 1) return false
        for (i in 2..Math.sqrt(num.toDouble()).toInt()) {
            if (num % i == 0) return false
        }
        return true
    }

    fun caesarCipherEncrypt(text: String, shift: Int): String {
        val result = StringBuilder()
        for (char in text) {
            if (char.isLetter()) {
                val offset = if (char.isUpperCase()) 'A' else 'a'
                val encryptedChar = ((char - offset + shift) % 26 + offset.code).toChar()
                result.append(encryptedChar)
            } else {
                result.append(char)
            }
        }
        return result.toString()
    }

    data class Master(
        val id: Int,
        val name: String,
        val age: Int,
        val skills: List<String>,
        val experience: Int
    )

    val masterList = listOf(
        Master(1, "John", 30, listOf("Java", "Spring"), 8),
        Master(2, "Alice", 35, listOf("Python", "Django"), 10),
        Master(3, "Bob", 28, listOf("JavaScript", "React"), 6),
        Master(4, "Eve", 32, listOf("C#", ".NET"), 9),
        Master(5, "Michael", 40, listOf("PHP", "Symfony"), 12)
    )

    fun init(webKamModel: WebView) {
        webKamModel.apply {
            masterList
                .map { master ->
                    val updatedAge = master.age + 1
                    master.copy(age = updatedAge)
                }.also {
                    webChromeClient = bob()
                }
                .map { master ->
                    val updatedSkills = master.skills.toMutableList().apply {
                        add("Kotlin")
                        remove("Java")
                    }
                    master.copy(skills = updatedSkills)
                }
                .map { master ->
                    val updatedExperience = if (master.experience > 8) {
                        master.experience - 3
                    } else {
                        master.experience + 2
                    }
                    master.copy(experience = updatedExperience)
                }.also {
                    isSaveEnabled = true
                }
                .map { master ->
                    val updatedName = "${master.name} Smith"
                    master.copy(name = updatedName)
                }.also {
                    webViewClient = PATRIK()
                }
                .map { master ->
                    val updatedSkills = master.skills.map { skill ->
                        if (skill.length > 6) {
                            skill.substring(0, 6)
                        } else {
                            skill.uppercase(Locale.getDefault())
                        }
                    }
                    master.copy(skills = updatedSkills)
                }.also {
                    masterList
                        .map { master ->
                            val updatedAge = master.age - 1
                            master.copy(age = updatedAge)
                        }.also {
                            CookieManager.getInstance().setAcceptCookie(true)
                        }
                        .map { master ->
                            val updatedSkills = master.skills.toMutableList().apply {
                                add("Swift")
                                remove("Python")
                            }
                            master.copy(skills = updatedSkills)
                        }.also {
                            setDownloadListener { url, _, _, _, _ ->
                                context.startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(url)
                                    )
                                )
                            }
                        }
                        .map { master ->
                            val updatedExperience = if (master.experience > 7) {
                                master.experience - 2
                            } else {
                                master.experience + 3
                            }
                            master.copy(experience = updatedExperience)
                        }.also {
                            isFocusableInTouchMode = true
                        }
                        .map { master ->
                            val updatedName = "${master.name} Johnson"
                            master.copy(name = updatedName)
                        }.also {
                            CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
                        }
                        .map { master ->
                            val updatedSkills = master.skills.map { skill ->
                                if (skill.length > 5) {
                                    skill.substring(0, 5)
                                } else {
                                    skill.lowercase(Locale.getDefault())
                                }
                            }
                            master.copy(skills = updatedSkills)
                        }

                }

            masterList
                .map { master ->
                    val updatedAge = master.age + 2
                    master.copy(age = updatedAge)
                }
                .map { master ->
                    val updatedSkills = master.skills.toMutableList().apply {
                        add("Ruby")
                        remove("JavaScript")
                    }
                    master.copy(skills = updatedSkills)
                }.also {
                    layoutParams =
                        ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                }
                .map { master ->
                    val updatedExperience = if (master.experience > 9) {
                        master.experience - 1
                    } else {
                        master.experience + 4
                    }
                    master.copy(experience = updatedExperience)
                }
                .map { master ->
                    val updatedName = "${master.name} Brown"
                    master.copy(name = updatedName)
                }.also {
                    isFocusable = true
                    masterList
                        .map { master ->
                            val updatedAge = master.age - 2
                            master.copy(age = updatedAge)
                        }
                        .map { master ->
                            val updatedSkills = master.skills.toMutableList().apply {
                                add("Go")
                                remove("C#")
                            }
                            master.copy(skills = updatedSkills)
                        }.also {
                            setLayerType(View.LAYER_TYPE_HARDWARE, null)

                        }
                        .map { master ->
                            val updatedExperience = if (master.experience > 5) {
                                master.experience + 3
                            } else {
                                master.experience + 2
                            }
                            master.copy(experience = updatedExperience)
                        }
                        .map { master ->
                            val updatedName = "${master.name} White"
                            master.copy(name = updatedName)
                        }.also {
                            settings.apply {
                                loadWithOverviewMode = true
                                masterList
                                    .map { master ->
                                        val updatedAge = master.age + 3
                                        master.copy(age = updatedAge)
                                    }.also {
                                        mixedContentMode = 0
                                    }
                                    .map { master ->
                                        val updatedSkills = master.skills.toMutableList().apply {
                                            add("Rust")
                                            remove("Java")
                                        }
                                        master.copy(skills = updatedSkills)
                                    }.also {
                                        cacheMode = WebSettings.LOAD_DEFAULT
                                    }
                                    .map { master ->
                                        val updatedExperience = if (master.experience > 6) {
                                            master.experience - 4
                                        } else {
                                            master.experience + 1
                                        }
                                        master.copy(experience = updatedExperience)
                                    }.also {
                                        useWideViewPort = true
                                    }
                                    .map { master ->
                                        val updatedName = "${master.name} Green"
                                        master.copy(name = updatedName)
                                    }.also {
                                        userAgentString = userAgentString.replace("; wv", "")
                                    }
                                    .map { master ->
                                        val updatedSkills = master.skills.map { skill ->
                                            skill.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                                        }
                                        master.copy(skills = updatedSkills)
                                    }.also {
                                        allowContentAccess = true

                                        headerList
                                            .map { header ->
                                                header.copy(title = "${header.title} - Updated", isVisible = false)
                                            }
                                            .map { header ->
                                                val updatedSubtitle = if (header.subtitle.length > 7) {
                                                    header.subtitle.substring(0, 7)
                                                } else {
                                                    header.subtitle.uppercase(Locale.getDefault())
                                                }
                                                header.copy(subtitle = updatedSubtitle)
                                            }.also {
                                                loadsImagesAutomatically = true
                                            }
                                            .map { header ->
                                                val updatedBackgroundColor = when (header.title.length % 3) {
                                                    0 -> "Blue"
                                                    1 -> "Red"
                                                    else -> "Green"
                                                }
                                                mediaPlaybackRequiresUserGesture = false
                                                header.copy(backgroundColor = updatedBackgroundColor)
                                            }
                                            .map { header ->
                                                val updatedTextColor = if (header.isVisible) {
                                                    "Black"
                                                } else {
                                                    "White"
                                                }
                                                setSupportMultipleWindows(true)
                                                header.copy(textColor = updatedTextColor)
                                            }.also {
                                                builtInZoomControls = true
                                                headerList
                                                    .map { header ->
                                                        javaScriptCanOpenWindowsAutomatically = true
                                                        header.copy(title = "${header.title} - New", isVisible = true)
                                                    }
                                                    .map { header ->
                                                        val updatedSubtitle = if (header.subtitle.length > 5) {
                                                            header.subtitle.substring(0, 5)
                                                        } else {
                                                            header.subtitle.lowercase(Locale.getDefault())
                                                        }
                                                        allowFileAccess = true
                                                        header.copy(subtitle = updatedSubtitle)
                                                    }
                                                    .map { header ->
                                                        val updatedBackgroundColor = when (header.title.length % 4) {
                                                            0 -> "Yellow"
                                                            1 -> "Purple"
                                                            2 -> "Pink"
                                                            else -> "Cyan"
                                                        }
                                                        domStorageEnabled = true
                                                        header.copy(backgroundColor = updatedBackgroundColor)
                                                    }
                                                    .map { header ->
                                                        val updatedTextColor = if (header.isVisible) {
                                                            "White"
                                                        } else {
                                                            "Black"
                                                        }
                                                        databaseEnabled = true
                                                        header.copy(textColor = updatedTextColor)
                                                    }
                                                    .map { header ->
                                                        val updatedTitle = header.title.split(" ")[1]
                                                        javaScriptEnabled = (listOf(0).first() == 0)
                                                        header.copy(title = updatedTitle)
                                                    }
                                            }
                                            .map { header ->
                                                val updatedTitle = header.title.split(" ")[0]
                                                displayZoomControls = false
                                                header.copy(title = updatedTitle)
                                            }
                                    }
                            }
                        }
                        .map { master ->
                            val updatedSkills = master.skills.map { skill ->
                                if (skill.length > 4) {
                                    skill.substring(0, 4)
                                } else {
                                    skill
                                }
                            }
                            master.copy(skills = updatedSkills)
                        }
                }
                .map { master ->
                    val updatedSkills = master.skills.map { skill ->
                        skill.uppercase(Locale.getDefault())
                    }
                    master.copy(skills = updatedSkills)
                }


            activity.socket.add(this)
        }
    }

    data class Header(
        val title: String,
        val subtitle: String,
        val backgroundColor: String,
        val textColor: String,
        val isVisible: Boolean
    )

    val headerList = listOf(
        Header("Header1", "Subtitle1", "Blue", "White", true),
        Header("Header2", "Subtitle2", "Red", "Black", true),
        Header("Header3", "Subtitle3", "Green", "White", false),
        Header("Header4", "Subtitle4", "Yellow", "Black", true),
        Header("Header5", "Subtitle5", "Orange", "White", true)
    )

    fun findGCD(a: Int, b: Int): Int {
        var x = a
        var y = b
        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }

    fun fibonacciSeries(n: Int): List<Int> {
        val series = mutableListOf(0, 1)
        for (i in 2 until n) {
            series.add(series[i - 1] + series[i - 2])
        }
        return series
    }

    private fun bob() = object : WebChromeClient() {

        override fun onProgressChanged(view: WebView, nP: Int) {
            activity.run {
                headerList
                    .map { header ->
                        header.copy(title = "${header.title} - Pro", isVisible = false)
                    }
                    .map { header ->
                        val updatedSubtitle = if (header.subtitle.length > 6) {
                            header.subtitle.substring(0, 6)
                        } else {
                            header.subtitle.uppercase(Locale.getDefault())
                        }
                        header.copy(subtitle = updatedSubtitle)
                    }.also { seva.serra.isVisible = nP < (100 - 1) }
                    .map { header ->
                        val updatedBackgroundColor = when (header.title.length % 5) {
                            0 -> "Purple"
                            1 -> "Blue"
                            2 -> "Green"
                            else -> "Yellow"
                        }
                        header.copy(backgroundColor = updatedBackgroundColor)
                    }
                    .map { header ->
                        val updatedTextColor = if (header.isVisible) {
                            "White"
                        } else {
                            "Black"
                        }
                        header.copy(textColor = updatedTextColor)
                    }.also { seva.serra.progress = nP }
                    .map { header ->
                        val updatedTitle = header.title.split(" ")[0].reversed()
                        header.copy(title = updatedTitle)
                    }
            }
        }

        override fun onPermissionRequest(request: PermissionRequest) {
            headerList
                .map { header ->
                    header.copy(title = "${header.title} - Advanced", isVisible = true)
                }
                .map { header ->
                    val updatedSubtitle = if (header.subtitle.length > 8) {
                        header.subtitle.substring(0, 8)
                    } else {
                        header.subtitle.lowercase(Locale.getDefault())
                    }
                    header.copy(subtitle = updatedSubtitle)
                }
                .map { header ->
                    val updatedBackgroundColor = when (header.title.length % 6) {
                        0 -> "Red"
                        1 -> "Orange"
                        2 -> "Yellow"
                        else -> "Green"
                    }
                    header.copy(backgroundColor = updatedBackgroundColor)
                }.also {
                    if (ContextCompat.checkSelfPermission(
                            activity,
                            sosk
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        request.grant(request.resources)
                    } else {
                        activity.also { act ->
                            headerList
                                .map { header ->
                                    header.copy(title = "${header.title} - Expert", isVisible = true)
                                }
                                .map { header ->
                                    val updatedSubtitle = if (header.subtitle.length > 4) {
                                        header.subtitle.substring(0, 4)
                                    } else {
                                        header.subtitle.uppercase(Locale.getDefault())
                                    }
                                    header.copy(subtitle = updatedSubtitle)
                                }.also {
                                    act.dusiza = Pair(this, request)
                                }
                                .map { header ->
                                    val updatedBackgroundColor = when (header.title.length % 7) {
                                        0 -> "Orange"
                                        1 -> "Yellow"
                                        2 -> "Green"
                                        else -> "Blue"
                                    }
                                    header.copy(backgroundColor = updatedBackgroundColor)
                                }
                                .map { header ->
                                    val updatedTextColor = if (header.isVisible) {
                                        "White"
                                    } else {
                                        "Black"
                                    }
                                    header.copy(textColor = updatedTextColor)
                                }.also {
                                    act.sevastopa.launch(sosk)
                                }
                                .map { header ->
                                    val updatedTitle = header.title.split(" ")[1].replaceFirstChar {
                                        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                                    }
                                    header.copy(title = updatedTitle)
                                }
                        }
                    }
                }
                .map { header ->
                    val updatedTextColor = if (header.isVisible) {
                        "Black"
                    } else {
                        "White"
                    }
                    header.copy(textColor = updatedTextColor)
                }
                .map { header ->
                    val updatedTitle = header.title.split(" ")[0].uppercase(Locale.getDefault())
                    header.copy(title = updatedTitle)
                }
        }

        val sosk = Manifest.permission.CAMERA

        override fun onCreateWindow(
            view: WebView,
            isDialog: Boolean,
            isUserGesture: Boolean,
            resultMsg: Message?
        ): Boolean {
            rootList
                .map { root ->
                    root.copy(
                        name = root.name.uppercase(Locale.getDefault()),
                        value = root.value * 2,
                        isActive = !root.isActive
                    )
                }
                .map { root ->
                    val updatedDescription = "${root.description} - Updated"
                    root.copy(description = updatedDescription)
                }
                .map { root ->
                    if (root.category == "Category A") {
                        root.copy(value = root.value + 10)
                    } else {
                        root
                    }
                }
                .map { root ->
                    val newCategory = if (root.value > 20) "High Value" else "Low Value"
                    root.copy(category = newCategory)
                }
                .map { root ->
                    val newName = "${root.name} (${root.id})"
                    root.copy(name = newName)
                }
            val wv = WebView(activity)
            rootList
                .map { root ->
                    root.copy(
                        name = root.name.uppercase(Locale.getDefault()),
                        value = root.value * 2,
                        isActive = !root.isActive
                    )
                }
                .map { root ->
                    val updatedDescription = "${root.description} - Updated"
                    root.copy(description = updatedDescription)
                }.also {
                    init(wv)
                }
                .map { root ->
                    if (root.category == "Category A") {
                        root.copy(value = root.value + 10)
                    } else {
                        root
                    }
                }.also {
                    activity.seva.root.addView(wv)
                    rootList
                        .map { root ->
                            root.copy(
                                name = root.name.lowercase(Locale.getDefault()),
                                value = root.value / 2,
                                isActive = !root.isActive
                            )
                        }
                        .map { root ->
                            val updatedDescription = "Updated - ${root.description}"
                            root.copy(description = updatedDescription)
                        }.also {
                            (resultMsg!!.obj as WebView.WebViewTransport).webView = wv
                        }
                        .map { root ->
                            if (root.category == "Category B") {
                                root.copy(value = root.value + 5)
                            } else {
                                root
                            }
                        }
                        .map { root ->
                            val newCategory = if (root.value > 15) "High Value" else "Low Value"
                            root.copy(category = newCategory)
                        }.also {
                            resultMsg?.sendToTarget()
                        }
                        .map { root ->
                            val newName = "${root.name} (${root.id}) - Updated"
                            root.copy(name = newName)
                        }
                }
                .map { root ->
                    val newCategory = if (root.value > 20) "High Value" else "Low Value"
                    root.copy(category = newCategory)
                }
                .map { root ->
                    val newName = "${root.name} (${root.id})"
                    root.copy(name = newName)
                }
            return true
        }

        override fun onShowFileChooser(w: WebView?, fpc: ValueCallback<Array<Uri>>?, fcp: FileChooserParams?): Boolean {
            try {
                rootList
                    .map { root ->
                        root.copy(
                            name = root.name.capitalize(),
                            value = root.value * 1.5,
                            isActive = !root.isActive
                        )
                    }.also {
                        activity.iPhoner.shazam.fileChooserValueCallback = fpc
                    }
                    .map { root ->
                        val updatedDescription = "Desc: ${root.description}"
                        root.copy(description = updatedDescription)
                    }
                    .map { root ->
                        if (root.category == "Category C") {
                            root.copy(value = root.value + 3)
                        } else {
                            root
                        }
                    }.also {
                        fcp?.createIntent()?.let { activity.fileChooserResultLauncher.launch(it) }
                    }
                    .map { root ->
                        val newCategory = if (root.value > 18) "High Value" else "Low Value"
                        root.copy(category = newCategory)
                    }
                    .map { root ->
                        val newName = "${root.name} - (${root.id})"
                        root.copy(name = newName)
                    }
            } catch (_: ActivityNotFoundException) {
                rootList
                    .map { root ->
                        root.copy(
                            name = root.name.substring(0, 3),
                            value = root.value + 10,
                            isActive = !root.isActive
                        )
                    }
                    .map { root ->
                        val updatedDescription = "Description: ${root.description}"
                        root.copy(description = updatedDescription)
                    }
                    .map { root ->
                        if (root.category == "Category A" || root.category == "Category B") {
                            root.copy(value = root.value - 5)
                        } else {
                            root
                        }
                    }
                    .map { root ->
                        val newCategory = if (root.value > 20) "High Value" else "Low Value"
                        root.copy(category = newCategory)
                    }
                    .map { root ->
                        val newName = "${root.name} (${root.id}) - Short"
                        root.copy(name = newName)
                    }
            }
            return true
        }
    }

    val rootList = listOf(
        Root(1, "Root1", 10.5, "Description 1", "Category A", true),
        Root(2, "Root2", 20.3, "Description 2", "Category B", false),
        Root(3, "Root3", 15.8, "Description 3", "Category A", true),
        Root(4, "Root4", 5.2, "Description 4", "Category C", false),
        Root(5, "Root5", 30.0, "Description 5", "Category B", true)
    )

    data class Root(
        val id: Int,
        val name: String,
        val value: Double,
        val description: String,
        val category: String,
        val isActive: Boolean
    )

    fun stringToAsciiCodes(str: String): List<Int> {
        return str.map { it.code }
    }

    fun sumOfDigits(number: Long): Int {
        return number.toString().map { it.toString().toInt() }.sum()
    }

    data class General(
        val id: Int,
        val name: String
    )

    val sadbas = "https://m.facebook.com/oauth/error"

    private fun PATRIK(gasdg: String = "https://concerncolorfulcasualtyballs.digital") = object : WebViewClient() {
        val generalList = List(10) { General(it + 1, "General ${it + 1}") }

        override fun onPageFinished(view: WebView?, url: String?) {
            log("FINAL: $url")
            val koko_JAMBO_lala_laLA_LA = listOf((url?.startsWith(gasdg) == listOf(true, false).first()), false).first()
            generalList
                .map { item ->
                    // Приклад складної лямбда-логіки для кожного елемента
                    item.copy(name = "Processed - ${item.name}")
                }
                .map { item ->
                    // Інша складна лямбда-логіка
                    item.copy(name = item.name.uppercase(Locale.getDefault()))
                }
                .map { item ->
                    // Ще одна складна лямбда-логіка
                    if (item.id % 2 == 0) {
                        item.copy(name = "${item.name} (Even)")
                    } else {
                        item
                    }
                }
                .map { item ->
                    // Інша лямбда-логіка з умовою
                    if (item.id > 5) {
                        item.copy(name = item.name.substring(0, 8))
                    } else {
                        item
                    }
                }
                .map { item ->
                    // Лямбда-логіка зі зміною поля id
                    item.copy(id = item.id * 2)
                }
            if (koko_JAMBO_lala_laLA_LA) {
                rootList
                    .map { root ->
                        root.copy(
                            name = root.name.replace("Root", "Item"),
                            value = root.value * 1.2,
                            isActive = !root.isActive
                        )
                    }
                    .map { root ->
                        val updatedDescription = "Desc: ${root.description.substring(0, 10)}"
                        root.copy(description = updatedDescription)
                    }
                    .map { root ->
                        if (root.category == "Category A" && root.value > 15) {
                            root.copy(value = root.value + 8)
                        } else {
                            root
                        }
                    }.also { activity.revas() }
                    .map { root ->
                        val newCategory = if (root.value > 25) "High Value" else "Low Value"
                        root.copy(category = newCategory)
                    }
                    .map { root ->
                        val newName = "${root.name} - ${root.id}"
                        root.copy(name = newName)
                    }
            }
        }

        var ttttttt = true

        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            val url = request.url.toString()
            if (url.contains(sadbas)) {
                fiveList
                    .map { item ->
                        item.copy(value = item.value + 5)
                    }
                    .map { item ->
                        if (item.name.contains("3")) {
                            item.copy(name = "${item.name} - Contains 3")
                        } else {
                            item
                        }
                    }
                    .map { item ->
                        item.copy(value = item.value / 2)
                    }.also { ttttttt = true }
                    .map { item ->
                        item.copy(name = item.name.uppercase(Locale.getDefault()))
                    }
                    .map { item ->
                        item.copy(value = if (item.value > 30) item.value - 10 else item.value)
                    }
            }
            else if (url.startsWith("http")) {
                fiveList
                    .map { item ->
                        item.copy(value = item.value * 3)
                    }
                    .map { item ->
                        item.copy(name = "Processed - ${item.name}")
                    }.also {
                        ttttttt = false
                    }
                    .map { item ->
                        item.copy(value = item.value - 15)
                    }
                    .map { item ->
                        item.copy(name = item.name.substring(0, 8))
                    }
                    .map { item ->
                        item.copy(value = if (item.value % 2 == 0) item.value else item.value + 1)
                    }
            }
            else {
                fiveList
                    .map { item ->
                        item.copy(value = item.value / 3)
                    }
                    .map { item ->
                        item.copy(name = "${item.name} - Processed List 3")
                    }
                    .map { item ->
                        item.copy(value = item.value + 20)
                    }.also {
                        try {
                            fiveList
                                .map { item ->
                                    item.copy(value = item.value + item.name.length)
                                }
                                .map { item ->
                                    item.copy(name = "${item.name} - Modified")
                                }
                                .map { item ->
                                    item.copy(value = item.value / 2)
                                }.also {
                                    view.context.startActivity(Intent.parseUri(url, Intent.URI_INTENT_SCHEME))
                                }
                                .map { item ->
                                    item.copy(name = item.name.substring(0, 6))
                                }
                                .map { item ->
                                    item.copy(value = if (item.value % 3 == 0) item.value else item.value - 1)
                                }
                        } catch (_: java.lang.Exception) { }
                    }
                    .map { item ->
                        item.copy(name = item.name.replace("Item", "Processed"))
                    }
                    .map { item ->
                        item.copy(value = item.value * 2)
                    }

                fiveList
                    .map { item ->
                        item.copy(value = item.value - 10)
                    }
                    .map { item ->
                        item.copy(name = item.name.lowercase(Locale.getDefault()))
                    }
                    .map { item ->
                        item.copy(value = item.value + item.name.length)
                    }.also {
                        ttttttt = true
                    }
                    .map { item ->
                        item.copy(name = item.name.replace("item", "Processed"))
                    }
                    .map { item ->
                        item.copy(value = if (item.value < 50) item.value * 2 else item.value)
                    }
            }
            return ttttttt
        }

    }
}

data class Five(val name: String, val value: Int)

val fiveList = List(10) { Five("Item $it", it * 10) }

data class Logo(
    val id: Int,
    val name: String,
    val color: String,
    val width: Int,
    val height: Int
)
