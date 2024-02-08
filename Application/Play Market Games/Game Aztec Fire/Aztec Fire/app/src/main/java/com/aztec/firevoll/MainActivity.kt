package com.aztec.firevoll

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.aztec.firevoll.ads.AdInterstitial
import com.aztec.firevoll.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlin.system.exitProcess

class MainActivity : FragmentActivity(), AndroidFragmentApplication.Callbacks {

    private lateinit var binding: ActivityMainBinding

    val lottie by lazy { Lottie(binding) }

    val adInterstitial  = AdInterstitial()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adInterstitial.loadAd(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        exit()
    }

    override fun exit() {
        log("exit")

        adAppOpen?.dispose()
        adInterstitial.dispose()

        finishAndRemoveTask()
        exitProcess(0)
    }

}