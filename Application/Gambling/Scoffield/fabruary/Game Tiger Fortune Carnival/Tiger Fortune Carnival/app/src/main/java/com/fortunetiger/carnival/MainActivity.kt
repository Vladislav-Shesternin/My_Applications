package com.fortunetiger.carnival

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.fortunetiger.carnival.databinding.ActivityMainBinding
import com.fortunetiger.carnival.util.Lottie
import com.fortunetiger.carnival.util.Once
import com.fortunetiger.carnival.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding : ActivityMainBinding

    lateinit var lottie: Lottie

    val adInterstitial  = AdInterstitial()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        lottie.showLoader()

        adInterstitial.loadAd(this)

    }

    override fun exit() {
        onceExit.once {
            adInterstitial.dispose()

            log("exit")
            coroutine.launch(Dispatchers.Main) {
                finishAndRemoveTask()
                delay(100)
                exitProcess(0)
            }
        }
    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lottie = Lottie(binding)
    }

}