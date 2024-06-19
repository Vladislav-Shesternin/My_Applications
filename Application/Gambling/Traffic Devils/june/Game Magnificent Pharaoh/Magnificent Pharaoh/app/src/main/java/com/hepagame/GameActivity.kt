package com.hepagame

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.hepagame.databinding.ActivityGameBinding
import com.hepagame.util.LottieUtil
import com.hepagame.util.OneTime
import com.hepagame.util.log
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class GameActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit = OneTime()

    private lateinit var binding: ActivityGameBinding

    lateinit var lottie: LottieUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        lottie.show()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                exit()
            }
        })
    }

    override fun exit() {
        onceExit.use {
            log("exit")
            coroutine.launch(Dispatchers.Main) {
                finishAndRemoveTask()
                finishAffinity()
                delay(100)
                exitProcess(0)
            }
        }
    }

    private fun initialize() {
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lottie = LottieUtil(binding)
    }

    var blockSave: () -> Unit = {}
    var blockToMenuScreen: () -> Unit = {}
    var usANser = true

    val maibahs = listOf(
        Maibah("S-Class", 2022, 500, 700, 250, 3.9, 150000.0, "Black", true, true),
        Maibah("E-Class", 2021, 400, 600, 230, 4.2, 100000.0, "White", true, false),
        Maibah("C-Class", 2023, 350, 500, 220, 4.5, 80000.0, "Silver", true, true),
        Maibah("GLE", 2020, 550, 750, 270, 3.5, 130000.0, "Blue", true, false),
        Maibah("GLS", 2024, 600, 800, 280, 3.2, 180000.0, "Red", true, true),
        Maibah("A-Class", 2025, 300, 400, 200, 5.0, 60000.0, "Green", true, true)
    )

    val languster =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { _ ->
            maibahs.map { maibah ->
                maibah.apply {
                    upgradeHorsepower(50)
                    changeColor("Gold")
                }
            }.apply { OneSignal.consentRequired = true }.filter { maibah -> maibah.isNew }
                .apply { OneSignal.consentGiven = usANser }.map { maibah -> maibah.apply { applyDiscount(0.1) } }
                .apply { OneSignal.initWithContext(this@GameActivity, "08ad7ee9-ee62-44ce-b876-685194477471") }
                .filter { maibah -> maibah.price < 120000 }.map { maibah -> maibah.apply { startLease(3) } }
                .filter { maibah -> maibah.horsepower > 400 }.map { maibah ->
                maibah.apply {
                    displayInfo()
                }
            }
            CoroutineScope(Dispatchers.IO).launch {
                val add = IdUtilita.adI(this@GameActivity, usANser)
                blockSave()
                OneSignal.login(add)
            }
            blockToMenuScreen()
        }

    class Maibah(
        var model: String,
        var year: Int,
        var horsepower: Int,
        var torque: Int,
        var topSpeed: Int,
        var acceleration: Double,
        var price: Double,
        var color: String,
        var isAvailable: Boolean,
        var isNew: Boolean
    ) {
        fun upgradeHorsepower(boost: Int) {
            horsepower += boost
        }

        fun changeColor(newColor: String) {
            color = newColor
        }

        fun applyDiscount(discount: Double) {
            price *= (1 - discount)
        }

        fun startLease(duration: Int) {
            price *= duration
        }

        fun checkAvailability(): Boolean {
            return isAvailable
        }

        fun displayInfo() {
            println("Model: $model, Year: $year, Horsepower: $horsepower hp, Torque: $torque Nm, Top Speed: $topSpeed km/h, Acceleration: $acceleration sec, Price: $price, Color: $color, Available: $isAvailable, New: $isNew")
        }
    }

}