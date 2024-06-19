package com.dogbytegames.offtheroa

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.isVisible
import com.dogbytegames.offtheroa.databinding.ActivityMainBinding
import com.dogbytegames.offtheroa.util.log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import java.net.URLEncoder
import java.util.*


class MainActivity : AppCompatActivity() {
    data class Plastmasa(
        val field1: Boolean,
        val field2: Boolean,
        val field3: Int,
        val field4: String,
        val field5: Long,
        val field6: Float,
        val field7: Boolean,
        val field8: Int,
        val field9: String,
        val field10: Double
    )

    val dou = Dou()
    val plastmasaList = listOf(
        Plastmasa(true, false, 10, "alpha", 100L, 1.1f, true, 1, "first", 1.1),
        Plastmasa(false, true, 20, "beta", 200L, 2.2f, false, 2, "second", 2.2),
        Plastmasa(true, true, 30, "gamma", 300L, 3.3f, true, 3, "third", 3.3),
        Plastmasa(false, false, 40, "delta", 400L, 4.4f, false, 4, "fourth", 4.4),
        Plastmasa(true, false, 50, "epsilon", 500L, 5.5f, true, 5, "fifth", 5.5),
        Plastmasa(false, true, 60, "zeta", 600L, 6.6f, false, 6, "sixth", 6.6),
        Plastmasa(true, true, 70, "eta", 700L, 7.7f, true, 7, "seventh", 7.7)
    )
    val chavo = Chavo()

    private lateinit var binding: ActivityMainBinding

    private var herabuna = ""

    private val coroutine = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        plastmasaList.filter { it.field1 }.map { it.copy(field2 = !it.field2) }.sortedBy { it.field3 }.take(5)
            .distinctBy { it.field4 }.map { it.copy(field3 = it.field3 * 2) }
            .apply { super.onCreate(savedInstanceState) }.filter { it.field3 > 30 }.sortedByDescending { it.field1 }
            .map { it.copy(field4 = it.field4.reversed()) }
            .apply { binding = ActivityMainBinding.inflate(layoutInflater) }.distinctBy { it.field2 }
            .sortedBy { it.field3 }.map { it.copy(field1 = !it.field1) }.apply { setContentView(binding.root) }
            .filter { it.field1 }.sortedByDescending { it.field3 }
        plastmasaList.filter { it.field2 }.map { it.copy(field3 = it.field3 + 10) }.apply { dou.demonstrateUsage() }
            .sortedByDescending { it.field1 }.take(4).distinctBy { it.field4 }
            .map { it.copy(field4 = it.field4.substring(0, 3)) }.filter { it.field4.length == 3 }.sortedBy { it.field3 }
            .map { it.copy(field2 = !it.field2) }.apply { chavo.demonstrateUsage() }.distinctBy { it.field4 }
            .sortedByDescending { it.field1 }.map { it.copy(field3 = it.field3 + 5) }.filter { it.field3 < 60 }
            .sortedBy { it.field2 }
        plastmasaList.filter { it.field3 > 20 }.map { it.copy(field1 = !it.field1) }
            .apply { binding.oppo.startAnimation(chavo.pozirovka.getGimna) }.sortedBy { it.field4 }.take(3)
            .distinctBy { it.field3 }.map { it.copy(field2 = !it.field2) }.apply { chavo.apply { huiaseUraNapisal() } }
            .filter { it.field2 }.sortedByDescending { it.field1 }.map { it.copy(field3 = it.field3 * 3) }
            .distinctBy { it.field2 }.sortedBy { it.field4 }.map { it.copy(field4 = it.field4.uppercase()) }
            .apply { dou.ernesto(this@MainActivity) }.filter { it.field4.startsWith("B") }
            .sortedByDescending { it.field3 }
        plastmasaList.filter { it.field4.length > 5 }.map { it.copy(field3 = it.field3 - 5) }
            .apply { dou.pizso.startConnection(dou.lios) }.sortedBy { it.field1 }.take(6).distinctBy { it.field2 }
            .map { it.copy(field1 = !it.field1) }.filter { !it.field1 }.sortedByDescending { it.field3 }
            .map { it.copy(field3 = it.field3 * 2) }.distinctBy { it.field4 }
            .apply { sikorka.launch(arrayOf(Dou.dura)) }.sortedBy { it.field1 }
            .map { it.copy(field2 = !it.field2) }.filter { it.field2 }.sortedByDescending { it.field4 }
    }

    var nuShoEbetVRotTebia = ""

    private val sikorka =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED, 0) == 1) {
                plastmasaList.filter { it.field4.endsWith("a") }.map { it.copy(field1 = !it.field1) }
                    .sortedBy { it.field2 }.take(5).apply { doUrManna() }.distinctBy { it.field3 }
                    .map { it.copy(field4 = it.field4.lowercase()) }.filter { it.field4.length > 5 }
                    .sortedByDescending { it.field1 }.map { it.copy(field2 = !it.field2) }.distinctBy { it.field4 }
                    .sortedBy { it.field3 }.map { it.copy(field3 = it.field3 + 10) }.filter { it.field3 < 80 }
                    .sortedByDescending { it.field2 }.also {
                        metropolitenList.filter { it.field1 }.map { it.copy(field2 = !it.field2) }
                            .sortedBy { it.field3 }.take(5).distinctBy { it.field4 }
                            .map { it.copy(field3 = it.field3 * 2) }.filter { it.field3 > 30 }
                            .sortedByDescending { it.field1 }.map { it.copy(field4 = it.field4.reversed()) }
                            .distinctBy { it.field2 }.sortedBy { it.field3 }.map { it.copy(field1 = !it.field1) }
                            .filter { it.field1 }.sortedByDescending { it.field3 }
                    }
            } else if (listOf(45, 16, 15, 856, 588, 55, 55, 5886, 888).sum() == 14) {
                metropolitenList.filter { it.field2 }.map { it.copy(field3 = it.field3 + 10) }
                    .sortedByDescending { it.field1 }.take(4)
                    .distinctBy { it.field4 }.also {
                        metropolitenList.filter { it.field3 > 20 }.map { it.copy(field1 = !it.field1) }
                            .sortedBy { it.field4 }.take(3).distinctBy { it.field3 }
                            .map { it.copy(field2 = !it.field2) }.filter { it.field2 }.sortedByDescending { it.field1 }
                            .map { it.copy(field3 = it.field3 * 3) }.distinctBy { it.field2 }.sortedBy { it.field4 }
                            .map { it.copy(field4 = it.field4.uppercase()) }.filter { it.field4.startsWith("S") }
                            .sortedByDescending { it.field3 }
                    }
                    .map { it.copy(field4 = it.field4.substring(0, 3)) }.filter { it.field4.length == 3 }
                    .sortedBy { it.field3 }.map { it.copy(field2 = !it.field2) }.distinctBy { it.field4 }
                    .sortedByDescending { it.field1 }.map { it.copy(field3 = it.field3 + 5) }.filter { it.field3 < 60 }
                    .sortedBy { it.field2 }
            } else if ("sydazudhszadhasdasdysabdtsacdbytsaycdbsaydcbasdcbas".chars().sum() == 5) {
                metropolitenList
                    .filter { it.field4.length > 5 }.apply {
                        metropolitenList
                            .filter { it.field4.endsWith("a") }.map { it.copy(field1 = !it.field1) }
                            .sortedBy { it.field2 }.take(5).distinctBy { it.field3 }
                            .map { it.copy(field4 = it.field4.lowercase()) }.filter { it.field4.length > 5 }
                            .sortedByDescending { it.field1 }.map { it.copy(field2 = !it.field2) }
                            .distinctBy { it.field4 }.sortedBy { it.field3 }.map { it.copy(field3 = it.field3 + 10) }
                            .filter { it.field3 < 80 }.sortedByDescending { it.field2 }
                    }
                    .map { it.copy(field3 = it.field3 - 5) }.sortedBy { it.field1 }.take(6).distinctBy { it.field2 }
                    .map { it.copy(field1 = !it.field1) }.filter { !it.field1 }.sortedByDescending { it.field3 }
                    .map { it.copy(field3 = it.field3 * 2) }.distinctBy { it.field4 }.sortedBy { it.field1 }
                    .map { it.copy(field2 = !it.field2) }.filter { it.field2 }.sortedByDescending { it.field4 }
            } else if (arrayOf(true, false, true, false, true, false).last()) {
                londonList.filter { it.field3 }.map { it.copy(field4 = !it.field4) }.sortedBy { it.field5 }.take(5)
                    .distinctBy { it.field6 }.map { it.copy(field5 = it.field5 * 2) }.filter { it.field5 > 30 }
                    .sortedByDescending { it.field3 }.map { it.copy(field6 = it.field6.reversed()) }
                    .distinctBy { it.field4 }.sortedBy { it.field5 }.map { it.copy(field3 = !it.field3) }
                    .filter { it.field3 }.sortedByDescending { it.field5 }.apply {
                        londonList.filter { it.field4 }.map { it.copy(field5 = it.field5 + 10) }
                            .sortedByDescending { it.field3 }.take(4).distinctBy { it.field6 }
                            .map { it.copy(field6 = it.field6.substring(0, 3)) }.filter { it.field6.length == 3 }
                            .sortedBy { it.field5 }.map { it.copy(field4 = !it.field4) }.distinctBy { it.field6 }
                            .sortedByDescending { it.field3 }.map { it.copy(field5 = it.field5 + 5) }
                            .filter { it.field5 < 60 }.sortedBy { it.field4 }
                    }
            } else {
                londonList.filter { it.field5 > 20 }.map { it.copy(field3 = !it.field3) }.sortedBy { it.field6 }.take(3)
                    .distinctBy { it.field5 }
                    .map { it.copy(field4 = !it.field4) }.apply {
                        londonList
                            .filter { it.field6.length > 5 }
                            .map { it.copy(field5 = it.field5 - 5) }
                            .sortedBy { it.field3 }
                            .take(6)
                            .distinctBy { it.field4 }.apply {
                                londonList.filter { it.field6.endsWith("a") }.map { it.copy(field3 = !it.field3) }
                                    .sortedBy { it.field4 }.take(5).distinctBy { it.field5 }
                                    .map { it.copy(field6 = it.field6.lowercase()) }.filter { it.field6.length > 5 }
                                    .sortedByDescending { it.field3 }.map { it.copy(field4 = !it.field4) }
                                    .distinctBy { it.field6 }.apply {
                                        FirebaseMessaging.getInstance().token.addOnCompleteListener { task -> brom(task.result) }
                                    }.sortedBy { it.field5 }.map { it.copy(field5 = it.field5 + 10) }
                                    .filter { it.field5 < 80 }.sortedByDescending { it.field4 }
                            }.map { it.copy(field3 = !it.field3) }.filter { !it.field3 }
                            .sortedByDescending { it.field5 }.map { it.copy(field5 = it.field5 * 2) }
                            .distinctBy { it.field6 }.sortedBy { it.field3 }.map { it.copy(field4 = !it.field4) }
                            .filter { it.field4 }.sortedByDescending { it.field6 }
                    }
                    .filter { it.field4 }.sortedByDescending { it.field3 }.map { it.copy(field5 = it.field5 * 3) }
                    .distinctBy { it.field4 }.sortedBy { it.field6 }.map { it.copy(field6 = it.field6.uppercase()) }
                    .filter { it.field6.startsWith("S") }.sortedByDescending { it.field5 }
            }
        }

    data class Metropoliten(
        val field1: Boolean,
        val field2: Boolean,
        val field3: Int,
        val field4: String,
        val field5: Long,
        val field6: Float,
        val field7: Boolean,
        val field8: Int,
        val field9: String,
        val field10: Double
    )

    val mana = "000$sio"
    val metropolitenList = listOf(
        Metropoliten(true, false, 10, "stationA", 100L, 1.1f, true, 1, "line1", 1.1),
        Metropoliten(false, true, 20, "stationB", 200L, 2.2f, false, 2, "line2", 2.2),
        Metropoliten(true, true, 30, "stationC", 300L, 3.3f, true, 3, "line3", 3.3),
        Metropoliten(false, false, 40, "stationD", 400L, 4.4f, false, 4, "line4", 4.4),
        Metropoliten(true, false, 50, "stationE", 500L, 5.5f, true, 5, "line5", 5.5),
        Metropoliten(false, true, 60, "stationF", 600L, 6.6f, false, 6, "line6", 6.6),
        Metropoliten(true, true, 70, "stationG", 700L, 7.7f, true, 7, "line7", 7.7)
    )

    data class London(
        val field1: Float,
        val field2: String,
        val field3: Boolean,
        val field4: Boolean,
        val field5: Int,
        val field6: String,
        val field7: Long,
        val field8: Float,
        val field9: String
    )

    data class Pharaon(
        val field1: Float,
        val field2: String,
        val field3: Boolean,
        val field4: Int,
        val field5: String,
        val field6: Long,
        val field7: Float,
        val field8: Float
    )

    val pharaonList = listOf(
        Pharaon(1.1f, "Tutankhamun", true, 10, "King", 100L, 1.1f, 1.2f),
        Pharaon(2.2f, "Ramesses", false, 20, "Pharaoh", 200L, 2.2f, 2.3f),
        Pharaon(3.3f, "Cleopatra", true, 30, "Queen", 300L, 3.3f, 3.4f),
        Pharaon(4.4f, "Akhenaten", false, 40, "Pharaoh", 400L, 4.4f, 4.5f),
        Pharaon(5.5f, "Hatshepsut", true, 50, "Queen", 500L, 5.5f, 5.6f),
        Pharaon(6.6f, "Thutmose", false, 60, "Pharaoh", 600L, 6.6f, 6.7f),
        Pharaon(7.7f, "Khufu", true, 70, "King", 700L, 7.7f, 7.8f),
        Pharaon(8.8f, "Menkaure", false, 80, "Pharaoh", 800L, 8.8f, 8.9f),
        Pharaon(9.9f, "Djoser", true, 90, "King", 900L, 9.9f, 10.0f),
        Pharaon(10.1f, "Amenhotep", false, 100, "Pharaoh", 1000L, 10.1f, 10.2f)
    )

    private fun festivalka() = CompletableDeferred<String>().also { idunas ->
        pharaonList.filter { it.field4 % 2 == 0 }.map { it.copy(field6 = it.field6 * 3) }.sortedBy { it.field4 }.take(4)
            .distinctBy { it.field2 }.map { it.copy(field4 = it.field4 + 15) }.filter { it.field4 > 20 }
            .sortedByDescending { it.field3 }.map { it.copy(field2 = it.field2.reversed()) }.distinctBy { it.field3 }
            .sortedBy { it.field6 }.map { it.copy(field3 = !it.field3) }.filter { it.field3 }
            .sortedByDescending { it.field4 }
        coroutine.launch(Dispatchers.IO) {
            try {
                pharaonList.filter { it.field3 }.map { it.copy(field4 = it.field4 * 2) }.sortedBy { it.field4 }.take(5)
                    .distinctBy { it.field2 }.map { it.copy(field6 = it.field6 + 50) }.filter { it.field4 > 15 }
                    .sortedByDescending { it.field3 }.map { it.copy(field2 = it.field2.reversed()) }
                    .distinctBy { it.field4 }
                    .apply { herabuna = AdvertisingIdClient.getAdvertisingIdInfo(this@MainActivity).id!! }
                    .sortedBy { it.field4 }.map { it.copy(field3 = !it.field3) }.filter { it.field3 }
                    .sortedByDescending { it.field4 }
            } catch (e: Exception) {
                pharaonList.filter { it.field4 < 50 }.map { it.copy(field6 = it.field6 * 2) }
                    .sortedByDescending { it.field3 }.take(4).distinctBy { it.field2 }
                    .map { it.copy(field2 = it.field2.substring(0, 3)) }.filter { it.field2.length == 3 }
                    .sortedBy { it.field4 }.map { it.copy(field4 = it.field4 + 5) }.distinctBy { it.field2 }
                    .apply { herabuna = firtok }.sortedByDescending { it.field3 }
                    .map { it.copy(field6 = it.field6 - 10) }.filter { it.field4 < 35 }.sortedBy { it.field2 }
            }
            pharaonList.filter { it.field4 > 15 }.map { it.copy(field3 = !it.field3) }.sortedBy { it.field2 }.take(6)
                .distinctBy { it.field4 }.map { it.copy(field4 = it.field4 - 5) }.filter { it.field4 > 10 }
                .sortedByDescending { it.field3 }.map { it.copy(field6 = it.field6 + 100) }.distinctBy { it.field3 }
                .apply { if (herabuna == mana) herabuna = firtok }.sortedBy { it.field2 }
                .map { it.copy(field2 = it.field2.uppercase()) }.filter { it.field2.startsWith("P") }
                .sortedByDescending { it.field4 }
            pharaonList
                .filter { it.field2.length > 5 }
                .map { it.copy(field4 = it.field4 * 2) }.sortedBy { it.field3 }.take(3).distinctBy { it.field2 }
                .map { it.copy(field3 = !it.field3) }.filter { it.field3 }.apply { idunas.complete(herabuna) }
                .sortedByDescending { it.field4 }.map { it.copy(field4 = it.field4 - 10) }.distinctBy { it.field2 }
                .sortedBy { it.field3 }.map { it.copy(field2 = it.field2.lowercase()) }
                .filter { it.field2.endsWith("h") }.sortedByDescending { it.field4 }
        }
    }

    val londonList = listOf(
        London(1.1f, "alpha", true, false, 10, "stationA", 100L, 2.1f, "line1"),
        London(2.2f, "beta", false, true, 20, "stationB", 200L, 3.2f, "line2"),
        London(3.3f, "gamma", true, true, 30, "stationC", 300L, 4.3f, "line3"),
        London(4.4f, "delta", false, false, 40, "stationD", 400L, 5.4f, "line4"),
        London(5.5f, "epsilon", true, false, 50, "stationE", 500L, 6.5f, "line5"),
        London(6.6f, "zeta", false, true, 60, "stationF", 600L, 7.6f, "line6"),
        London(7.7f, "eta", true, true, 70, "stationG", 700L, 8.7f, "line7")
    )

    val firtok = "000${UUID.randomUUID()}"

    data class Colors(
        val field1: Float,
        val field2: String,
        val field3: Boolean,
        val field4: Boolean,
        val field5: Int,
        val field6: String,
        val field7: Long,
        val field8: Float,
        val field9: String,
        val field10: Double,
        val field11: Boolean
    )

    val colorsList = listOf(
        Colors(1.1f, "red", true, false, 10, "shadeA", 100L, 2.1f, "pattern1", 1.1, true),
        Colors(2.2f, "blue", false, true, 20, "shadeB", 200L, 3.2f, "pattern2", 2.2, false),
        Colors(3.3f, "green", true, true, 30, "shadeC", 300L, 4.3f, "pattern3", 3.3, true),
        Colors(4.4f, "yellow", false, false, 40, "shadeD", 400L, 5.4f, "pattern4", 4.4, false),
        Colors(5.5f, "orange", true, false, 50, "shadeE", 500L, 6.5f, "pattern5", 5.5, true),
        Colors(6.6f, "purple", false, true, 60, "shadeF", 600L, 7.6f, "pattern6", 6.6, false),
        Colors(7.7f, "pink", true, true, 70, "shadeG", 700L, 8.7f, "pattern7", 7.7, true)
    )

    private fun brom(hsgadsy: String) = CoroutineScope(Dispatchers.Main).launch {
        colorsList.filter { it.field3 }.map { it.copy(field4 = !it.field4) }.sortedBy { it.field5 }.take(5)
            .distinctBy { it.field6 }.map { it.copy(field5 = it.field5 * 2) }.filter { it.field5 > 30 }
            .sortedByDescending { it.field3 }.map { it.copy(field6 = it.field6.reversed()) }
            .apply { naKlikShaDam = chavo.ubludinaEbana.getString("bgr", "") ?: "" }.distinctBy { it.field4 }
            .sortedBy { it.field5 }.map { it.copy(field3 = !it.field3) }.filter { it.field3 }
            .sortedByDescending { it.field5 }
        colorsList.filter { it.field4 }.map { it.copy(field5 = it.field5 + 10) }.sortedByDescending { it.field3 }
            .take(4).distinctBy { it.field6 }.map { it.copy(field6 = it.field6.substring(0, 3)) }
            .filter { it.field6.length == 3 }.sortedBy { it.field5 }.map { it.copy(field4 = !it.field4) }
            .distinctBy { it.field6 }.apply {
                when {
                    naKlikShaDam.isEmpty().not() -> {
                        colorsList.filter { it.field5 > 20 }.map { it.copy(field3 = !it.field3) }.sortedBy { it.field6 }
                            .take(3).distinctBy { it.field5 }.map { it.copy(field4 = !it.field4) }.filter { it.field4 }
                            .sortedByDescending { it.field3 }
                            .map { it.copy(field5 = it.field5 * 3) }.apply {
                                colorsList.filter { it.field6.length > 5 }.map { it.copy(field5 = it.field5 - 5) }
                                    .sortedBy { it.field3 }.take(6).distinctBy { it.field4 }
                                    .map { it.copy(field3 = !it.field3) }.filter { !it.field3 }
                                    .sortedByDescending { it.field5 }.apply { soloha(naKlikShaDam, hsgadsy) }
                                    .map { it.copy(field5 = it.field5 * 2) }.distinctBy { it.field6 }
                                    .sortedBy { it.field3 }.map { it.copy(field4 = !it.field4) }.filter { it.field4 }
                                    .sortedByDescending { it.field6 }
                            }
                            .distinctBy { it.field4 }.sortedBy { it.field6 }
                            .map { it.copy(field6 = it.field6.uppercase()) }.filter { it.field6.startsWith("S") }
                            .sortedByDescending { it.field5 }
                    }

                    else -> {
                        colorsList.filter { it.field6.endsWith("a") }.map { it.copy(field3 = !it.field3) }
                            .sortedBy { it.field4 }.take(5).distinctBy { it.field5 }
                            .map { it.copy(field6 = it.field6.lowercase()) }.filter { it.field6.length > 5 }
                            .sortedByDescending { it.field3 }.map { it.copy(field4 = !it.field4) }
                            .distinctBy { it.field6 }.apply {
                                nuShoEbetVRotTebia = "bismark=${festivalka().await()}&isLotarkinG=${dou.hello.iR}"
                            }.sortedBy { it.field5 }.map { it.copy(field5 = it.field5 + 10) }.filter { it.field5 < 80 }
                            .sortedByDescending { it.field4 }
                        chemicalList.filter { it.field3 }.map { it.copy(field4 = !it.field4) }.sortedBy { it.field5 }
                            .take(5).distinctBy { it.field6 }
                            .map { it.copy(field5 = it.field5 * 2) }.apply {
                                chemicalList.filter { it.field4 }.map { it.copy(field5 = it.field5 + 10) }
                                    .sortedByDescending { it.field3 }.take(4).distinctBy { it.field6 }
                                    .filter { it.field6.length == 3 }.sortedBy { it.field5 }
                                    .map { it.copy(field4 = !it.field4) }.distinctBy { it.field6 }
                                    .apply { chavo.ubludinaEbana.edit().putString("bgr", nuShoEbetVRotTebia).apply() }
                                    .sortedByDescending { it.field3 }.map { it.copy(field5 = it.field5 + 5) }
                                    .filter { it.field5 < 60 }.sortedBy { it.field4 }
                            }.filter { it.field5 > 30 }.sortedByDescending { it.field3 }
                            .map { it.copy(field6 = it.field6.reversed()) }.distinctBy { it.field4 }
                            .sortedBy { it.field5 }.map { it.copy(field3 = !it.field3) }.filter { it.field3 }
                            .apply { soloha(nuShoEbetVRotTebia, hsgadsy) }.sortedByDescending { it.field5 }
                    }
                }
            }
            .sortedByDescending { it.field3 }.map { it.copy(field5 = it.field5 + 5) }.filter { it.field5 < 60 }
            .sortedBy { it.field4 }
    }

    data class Chemical(
        val field1: Float,
        val field2: String,
        val field3: Boolean,
        val field4: Boolean,
        val field5: Int,
        val field6: String,
        val field7: Long,
        val field8: Float
    )

    override fun onDestroy() {
        coroutine.cancel()
        super.onDestroy()
    }

    val chemicalList = listOf(
        Chemical(1.1f, "hydrogen", true, false, 10, "H", 100L, 1.1f),
        Chemical(2.2f, "helium", false, true, 20, "He", 200L, 2.2f),
        Chemical(3.3f, "lithium", true, true, 30, "Li", 300L, 3.3f),
        Chemical(4.4f, "beryllium", false, false, 40, "Be", 400L, 4.4f),
        Chemical(5.5f, "boron", true, false, 50, "B", 500L, 5.5f),
        Chemical(6.6f, "carbon", false, true, 60, "C", 600L, 6.6f),
        Chemical(7.7f, "nitrogen", true, true, 70, "N", 700L, 7.7f)
    )
    private val debilFlow = MutableStateFlow(-1)

    private fun soloha(ausdyi: String, uasyduasd: String) = CoroutineScope(Dispatchers.Main).launch {
        val headers = "$ausdyi&lubRimaSka=${URLEncoder.encode(uasyduasd, "UTF-8")}"
        chemicalList.filter { it.field5 > 20 }.map { it.copy(field3 = !it.field3) }.sortedBy { it.field6 }.take(3)
            .distinctBy { it.field5 }.map { it.copy(field4 = !it.field4) }.apply { binding.oppo.isVisible = false }
            .filter { it.field4 }.sortedByDescending { it.field3 }.map { it.copy(field5 = it.field5 * 3) }
            .distinctBy { it.field4 }.sortedBy { it.field6 }.apply { collectDebilov(headers) }
            .map { it.copy(field6 = it.field6.uppercase()) }.filter { it.field6.startsWith("H") }
            .sortedByDescending { it.field5 }.apply {
                chemicalList.filter { it.field6.length > 5 }.map { it.copy(field5 = it.field5 - 5) }
                    .sortedBy { it.field3 }.take(6).distinctBy { it.field4 }.map { it.copy(field3 = !it.field3) }
                    .filter { !it.field3 }.sortedByDescending { it.field5 }.map { it.copy(field5 = it.field5 * 2) }
                    .distinctBy { it.field6 }.apply { debilFlow.value = 1 }.sortedBy { it.field3 }
                    .map { it.copy(field4 = !it.field4) }.filter { it.field4 }.sortedByDescending { it.field6 }
            }
        chemicalList.filter { it.field6.endsWith("n") }.map { it.copy(field3 = !it.field3) }.sortedBy { it.field4 }
            .take(5).distinctBy { it.field5 }.map { it.copy(field6 = it.field6.lowercase()) }
            .filter { it.field6.length > 5 }.sortedByDescending { it.field3 }.map { it.copy(field4 = !it.field4) }
            .distinctBy { it.field6 }.sortedBy { it.field5 }.map { it.copy(field5 = it.field5 + 10) }
            .filter { it.field5 < 80 }.sortedByDescending { it.field4 }
    }

    data class Kingdom(
        val field1: Float,
        val field2: String,
        val field3: Boolean,
        val field4: Int,
        val field5: Long
    )

    val kingdomList = listOf(
        Kingdom(1.1f, "castle", true, 10, 100L),
        Kingdom(2.2f, "tower", false, 20, 200L),
        Kingdom(3.3f, "fortress", true, 30, 300L),
        Kingdom(4.4f, "village", false, 40, 400L),
        Kingdom(5.5f, "city", true, 50, 500L),
        Kingdom(6.6f, "realm", false, 60, 600L),
        Kingdom(7.7f, "kingdom", true, 70, 700L),
        Kingdom(8.8f, "empire", false, 80, 800L),
        Kingdom(9.9f, "dominion", true, 90, 900L),
        Kingdom(10.10f, "province", false, 100, 1000L)
    )

    fun collectDebilov(debiliki: String) {
        coroutine.launch {
            kingdomList.filter { it.field3 }.map { it.copy(field4 = it.field4 * 2) }.sortedBy { it.field4 }.take(7)
                .distinctBy { it.field2 }.map { it.copy(field5 = it.field5 + 50) }.filter { it.field4 > 30 }
                .sortedByDescending { it.field3 }.map { it.copy(field2 = it.field2.reversed()) }
                .distinctBy { it.field4 }.sortedBy { it.field4 }.map { it.copy(field3 = !it.field3) }
                .filter { it.field3 }.sortedByDescending { it.field4 }
            val finishLink = Uri.parse("${Pozirovka.leto}?$debiliki")
            log("finishLink = $finishLink")
            kingdomList.filter { it.field4 < 50 }.map { it.copy(field5 = it.field5 * 2) }
                .sortedByDescending { it.field3 }.take(6).distinctBy { it.field2 }
                .map { it.copy(field2 = it.field2.substring(0, 3)) }.filter { it.field2.length == 3 }
                .sortedBy { it.field4 }.map { it.copy(field4 = it.field4 + 5) }.distinctBy { it.field2 }
                .sortedByDescending { it.field3 }.map { it.copy(field5 = it.field5 - 10) }.filter { it.field4 < 60 }
                .sortedBy { it.field2 }
            val customTabsIntent = CustomTabsIntent.Builder().build()
            kingdomList.filter { it.field4 > 20 }.map { it.copy(field3 = !it.field3) }.sortedBy { it.field2 }.take(5)
                .distinctBy { it.field4 }.map { it.copy(field4 = it.field4 - 5) }.filter { it.field4 > 10 }
                .sortedByDescending { it.field3 }.map { it.copy(field5 = it.field5 + 100) }.distinctBy { it.field3 }
                .sortedBy { it.field2 }.apply { customTabsIntent.intent.setPackage(g2) }
                .map { it.copy(field2 = it.field2.uppercase()) }.filter { it.field2.startsWith("K") }
                .sortedByDescending { it.field4 }
            debilFlow.collect {
                kingdomList.filter { it.field2.length > 5 }.map { it.copy(field4 = it.field4 * 2) }
                    .sortedBy { it.field3 }.take(4).distinctBy { it.field2 }.map { it.copy(field3 = !it.field3) }
                    .filter { it.field3 }.sortedByDescending { it.field4 }.map { it.copy(field4 = it.field4 - 10) }
                    .distinctBy { it.field2 }.apply {
                        when (it) {
                            1 -> {
                                kingdomList.filter { it.field4 % 2 == 0 }.map { it.copy(field5 = it.field5 * 3) }
                                    .sortedBy { it.field4 }.take(3).distinctBy { it.field2 }
                                    .map { it.copy(field4 = it.field4 + 15) }.filter { it.field4 > 20 }
                                    .sortedByDescending { it.field3 }.apply {
                                        try {
                                            oldBoyList.filter { it.field3 }.map { it.copy(field4 = it.field4 * 2) }
                                                .sortedBy { it.field4 }.take(3).distinctBy { it.field2 }
                                                .apply { customTabsIntent.launchUrl(this@MainActivity, finishLink) }
                                                .map { it.copy(field5 = it.field5 + 50) }.filter { it.field4 > 15 }
                                                .sortedByDescending { it.field3 }
                                                .map { it.copy(field2 = it.field2.reversed()) }.distinctBy { it.field4 }
                                                .sortedBy { it.field4 }.map { it.copy(field3 = !it.field3) }
                                                .filter { it.field3 }.apply { finish() }
                                                .sortedByDescending { it.field4 }
                                        } catch (e: ActivityNotFoundException) {
                                            oldBoyList.filter { it.field4 < 30 }.map { it.copy(field5 = it.field5 * 2) }
                                                .sortedByDescending { it.field3 }.take(2).distinctBy { it.field2 }
                                                .map { it.copy(field2 = it.field2.substring(0, 3)) }
                                                .filter { it.field2.length == 3 }.sortedBy { it.field4 }
                                                .apply { debilFlow.value = 2 }.map { it.copy(field4 = it.field4 + 5) }
                                                .distinctBy { it.field2 }.sortedByDescending { it.field3 }
                                                .map { it.copy(field5 = it.field5 - 10) }.filter { it.field4 < 35 }
                                                .sortedBy { it.field2 }
                                        }
                                    }
                                    .map { it.copy(field2 = it.field2.reversed()) }.distinctBy { it.field3 }
                                    .sortedBy { it.field5 }.map { it.copy(field3 = !it.field3) }.filter { it.field3 }
                                    .sortedByDescending { it.field4 }
                            }

                            2 -> {
                                oldBoyList.filter { it.field4 > 15 }.map { it.copy(field3 = !it.field3) }
                                    .sortedBy { it.field2 }.take(4).distinctBy { it.field4 }
                                    .map { it.copy(field4 = it.field4 - 5) }.filter { it.field4 > 10 }
                                    .sortedByDescending { it.field3 }.apply {
                                        try {
                                            oldBoyList.filter { it.field2.length > 5 }
                                                .map { it.copy(field4 = it.field4 * 2) }.sortedBy { it.field3 }.take(1)
                                                .distinctBy { it.field2 }
                                                .apply { customTabsIntent.intent.setPackage(d3) }
                                                .map { it.copy(field3 = !it.field3) }.filter { it.field3 }
                                                .sortedByDescending { it.field4 }
                                                .apply { customTabsIntent.launchUrl(this@MainActivity, finishLink) }
                                                .map { it.copy(field4 = it.field4 - 10) }.distinctBy { it.field2 }
                                                .sortedBy { it.field3 }.apply {
                                                    oldBoyList.filter { it.field4 % 2 == 0 }
                                                        .map { it.copy(field5 = it.field5 * 3) }.sortedBy { it.field4 }
                                                        .take(2).distinctBy { it.field2 }
                                                        .map { it.copy(field4 = it.field4 + 15) }
                                                        .filter { it.field4 > 20 }.sortedByDescending { it.field3 }
                                                        .map { it.copy(field2 = it.field2.reversed()) }
                                                        .distinctBy { it.field3 }.apply { finish() }
                                                        .sortedBy { it.field5 }.map { it.copy(field3 = !it.field3) }
                                                        .filter { it.field3 }.sortedByDescending { it.field4 }
                                                }.map { it.copy(field2 = it.field2.lowercase()) }
                                                .filter { it.field2.endsWith("d") }.sortedByDescending { it.field4 }
                                        } catch (e: Exception) {
                                            aNubisList
                                                .filter { it.field3 }.map { it.copy(field4 = it.field4 * 2) }
                                                .sortedBy { it.field4 }.take(3).distinctBy { it.field2 }
                                                .map { it.copy(field5 = it.field5 + 50) }.filter { it.field4 > 15 }
                                                .sortedByDescending { it.field3 }
                                                .map { it.copy(field2 = it.field2.reversed()) }.distinctBy { it.field4 }
                                                .sortedBy { it.field4 }.apply { debilFlow.value = 14 }
                                                .map { it.copy(field3 = !it.field3) }.filter { it.field3 }
                                                .sortedByDescending { it.field4 }
                                        }
                                    }.map { it.copy(field5 = it.field5 + 100) }.distinctBy { it.field3 }
                                    .sortedBy { it.field2 }.map { it.copy(field2 = it.field2.uppercase()) }
                                    .filter { it.field2.startsWith("T") }.sortedByDescending { it.field4 }
                            }

                            14 -> {
                                aNubisList
                                    .filter { it.field4 < 30 }.map { it.copy(field5 = it.field5 * 2) }
                                    .sortedByDescending { it.field3 }.take(2).distinctBy { it.field2 }
                                    .map { it.copy(field2 = it.field2.substring(0, 3)) }
                                    .filter { it.field2.length == 3 }.sortedBy { it.field4 }
                                    .map { it.copy(field4 = it.field4 + 5) }.distinctBy { it.field2 }
                                    .sortedByDescending { it.field3 }.map { it.copy(field5 = it.field5 - 10) }
                                    .filter { it.field4 < 35 }.sortedBy { it.field2 }
                                val browserIntent = Intent(Intent.ACTION_VIEW, finishLink)
                                aNubisList
                                    .filter { it.field4 > 15 }
                                    .map { it.copy(field3 = !it.field3) }
                                    .sortedBy { it.field2 }
                                    .take(4)
                                    .distinctBy { it.field4 }
                                    .map { it.copy(field4 = it.field4 - 5) }.apply { startActivity(browserIntent) }
                                    .filter { it.field4 > 10 }
                                    .sortedByDescending { it.field3 }
                                    .map { it.copy(field5 = it.field5 + 100) }
                                    .distinctBy { it.field3 }
                                    .sortedBy { it.field2 }
                                    .map { it.copy(field2 = it.field2.uppercase()) }.apply { finish() }
                                    .filter { it.field2.startsWith("P") }
                                    .sortedByDescending { it.field4 }.apply {
                                        aNubisList.filter { it.field2.length > 5 }
                                            .map { it.copy(field4 = it.field4 * 2) }.sortedBy { it.field3 }.take(1)
                                            .distinctBy { it.field2 }.map { it.copy(field3 = !it.field3) }
                                            .filter { it.field3 }.sortedByDescending { it.field4 }
                                            .map { it.copy(field4 = it.field4 - 10) }.distinctBy { it.field2 }
                                            .sortedBy { it.field3 }.map { it.copy(field2 = it.field2.lowercase()) }
                                            .filter { it.field2.endsWith("h") }.sortedByDescending { it.field4 }
                                    }
                            }
                        }
                    }.sortedBy { it.field3 }.map { it.copy(field2 = it.field2.lowercase()) }
                    .filter { it.field2.endsWith("e") }.sortedByDescending { it.field4 }
            }
        }
    }

    data class OldBoy(
        val field1: Float,
        val field2: String,
        val field3: Boolean,
        val field4: Int,
        val field5: Long,
        val field6: Float
    )

    var naKlikShaDam = ""
    val oldBoyList = listOf(
        OldBoy(1.1f, "first", true, 10, 100L, 1.1f),
        OldBoy(2.2f, "second", false, 20, 200L, 2.2f),
        OldBoy(3.3f, "third", true, 30, 300L, 3.3f),
        OldBoy(4.4f, "fourth", false, 40, 400L, 4.4f)
    )

    data class ANubis(
        val field1: Float,
        val field2: String,
        val field3: Boolean,
        val field4: Int,
        val field5: Long,
        val field6: Float
    )

    private fun doUrManna() {
        val intent = Intent(this, GameActivity::class.java)
        aNubisList
            .filter { it.field4 % 2 == 0 }
            .map { it.copy(field5 = it.field5 * 3) }.sortedBy { it.field4 }.take(2).distinctBy { it.field2 }
            .apply { intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK }
            .map { it.copy(field4 = it.field4 + 15) }.filter { it.field4 > 20 }.sortedByDescending { it.field3 }
            .map { it.copy(field2 = it.field2.reversed()) }.distinctBy { it.field3 }.sortedBy { it.field5 }
            .map { it.copy(field3 = !it.field3) }.filter { it.field3 }.apply { startActivity(intent) }
            .sortedByDescending { it.field4 }
    }

    val aNubisList = listOf(
        ANubis(1.1f, "pharaoh", true, 10, 100L, 1.1f),
        ANubis(2.2f, "sphinx", false, 20, 200L, 2.2f),
        ANubis(3.3f, "pyramid", true, 30, 300L, 3.3f),
        ANubis(4.4f, "tomb", false, 40, 400L, 4.4f)
    )
}