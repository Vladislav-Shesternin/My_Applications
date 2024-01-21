package com.cosmo.plinko

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.cosmo.plinko.databinding.ActivityMainBinding
import com.cosmo.plinko.util.Lottie
import com.cosmo.plinko.util.Once
import com.cosmo.plinko.util.internetConnection
import com.cosmo.plinko.util.log
import com.cosmo.plinko.util.setVisible
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding : ActivityMainBinding

    lateinit var lottie: Lottie
    private lateinit var banner: AdView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        (application as? App)?.appOpenAdManager?.showAdIfAvailable(this@MainActivity)
        asyncCheckInternetConnection()

        lottie.showLoader()


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

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lottie = Lottie(binding)
        banner = binding.banner.apply { loadAd(AdRequest.Builder().build()) }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun asyncCheckInternetConnection() {
        coroutine.launch(Dispatchers.Main) {
            while (isActive) {
                delay(5_000)
                banner.setVisible(if (internetConnection()) View.VISIBLE else View.GONE)
            }
        }
    }

}