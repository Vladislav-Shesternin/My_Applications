package com.veldan.fantasticslots

import android.app.Activity
import android.content.res.Resources
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.veldan.fantasticslots.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

lateinit var activityContext  : Activity            private set
lateinit var activityResources: Resources           private set
lateinit var navController    : NavController       private set
lateinit var binding          : ActivityMainBinding private set

class AndroidLauncher : FragmentActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        private lateinit var coroutineLoader: CoroutineScope



        fun showLoader() {
            coroutineLoader = CoroutineScope(Dispatchers.Main)
            coroutineLoader.launch { binding.lottie.apply {
                isVisible = true
                playAnimation()
            } }
        }

        fun hideLoader() {
            coroutineLoader.launch { binding.lottie.apply {
                isVisible = false
                cancelAnimation()
                cancel()
            } }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_FantasticSlots)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityContext   = this
        activityResources = resources
        navController     = findNavController(R.id.nav_host_fragment)
    }

    override fun exit() {
        finish()
        exitProcess(0)
    }

}