package com.hellhot.competition

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.hellhot.competition.ads.AdInterstitial
import com.hellhot.competition.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainActivity : FragmentActivity(), AndroidFragmentApplication.Callbacks {

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

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
        onceExit.once {
            log("exit")
            coroutine.launch(Dispatchers.Main) {
                finishAndRemoveTask()
                delay(100)
                exitProcess(0)
            }
        }
    }

}