package com.veldan.boost.and.clean.simpleapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.android.gms.ads.AdRequest
import com.veldan.boost.and.clean.simpleapp.databinding.ActivityMainBinding
import com.veldan.boost.and.clean.simpleapp.util.adMob.Banner
import com.veldan.boost.and.clean.simpleapp.util.adMob.Interstitial
import com.veldan.boost.and.clean.simpleapp.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        lateinit var binding      : ActivityMainBinding
        @SuppressLint("StaticFieldLeak")
        lateinit var navController: NavController

        val coroutineMain = CoroutineScope(Dispatchers.Main)

        lateinit var banner      : Banner
        lateinit var interstitial: Interstitial
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        banner       = Banner(binding.adView, coroutineMain)
        interstitial = Interstitial(this, coroutineMain).apply { load() }

        setStartDestination(R.id.gameFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        exit()
    }

    override fun exit() {
        cancelCoroutinesAll(coroutine, coroutineMain)
        finishAndRemoveTask()
        exitProcess(0)
    }



    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController       = findNavController(R.id.nav_host_fragment)
    }

    private fun setStartDestination(
        @IdRes destinationId: Int,
        args: Bundle? = null
    ) {
        with(navController) {
            navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, args) }
        }
    }

}