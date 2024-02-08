package com.gen.bettermeditatio

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.gen.bettermeditatio.ads.AdInterstitial
import com.gen.bettermeditatio.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlin.system.exitProcess

class MainActivity : FragmentActivity(), AndroidFragmentApplication.Callbacks {

    private lateinit var binding: ActivityMainBinding

    val lottie by lazy { Lottie(binding) }

    private lateinit var banner: AdView
    val adInterstitial  = AdInterstitial()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        banner = binding.banner.apply { loadAd(AdRequest.Builder().build()) }
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