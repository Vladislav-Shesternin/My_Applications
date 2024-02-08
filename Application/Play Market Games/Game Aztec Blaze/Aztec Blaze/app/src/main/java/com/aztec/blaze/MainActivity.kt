package com.aztec.blaze

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aztec.blaze.databinding.ActivityMainBinding
import com.aztec.blaze.util.AdInterstitial
import com.aztec.blaze.util.Lottie
import com.aztec.blaze.util.cancelCoroutinesAll
import com.aztec.blaze.util.log
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
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
        lottie.showLoader()

        adInterstitial.loadAd(this)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

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