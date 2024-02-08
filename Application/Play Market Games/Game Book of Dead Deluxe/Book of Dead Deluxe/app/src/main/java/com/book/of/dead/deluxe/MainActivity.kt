package com.book.of.dead.deluxe

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.book.of.dead.deluxe.ads.AdInterstitial
import com.book.of.dead.deluxe.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : FragmentActivity(), AndroidFragmentApplication.Callbacks {

    private lateinit var binding: ActivityMainBinding

    val lottie by lazy { Lottie(binding) }

    val adInterstitial = AdInterstitial()

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