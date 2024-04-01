package com.riseofgiza.velsolution

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.riseofgiza.velsolution.databinding.ActivityMainBinding
import com.riseofgiza.velsolution.util.AdInterstitial
import com.riseofgiza.velsolution.util.Lottie
import com.riseofgiza.velsolution.util.cancelCoroutinesAll
import com.riseofgiza.velsolution.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        lateinit var binding: ActivityMainBinding
        val lottie by lazy { Lottie(binding) }
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)

    val adInterstitial = AdInterstitial()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        adInterstitial.loadAd(this)
        lottie.showLoader()
    }

    override fun onDestroy() {
        super.onDestroy()
        exit()
    }

    override fun exit() {
        log("exit")

        adAppOpen?.dispose()
        adInterstitial.dispose()

        cancelCoroutinesAll(coroutine)
        finishAndRemoveTask()
        exitProcess(0)
    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}